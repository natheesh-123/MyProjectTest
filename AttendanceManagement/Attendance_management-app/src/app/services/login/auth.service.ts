import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { UserState } from '../../../store/user/user.state';
import { addMessage } from '../../../common/popupmessage';
import { saveUserData } from '../../../store/user/user.actions';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private tokenKey = 'authToken';
  // baseURL:string="https://localhost:7200/api";
  baseURL:string= (window.location.host.includes("localhost"))? "https://localhost:7200/api" : "https://172.17.7.114:7200/api";

  User:any;
  private userstore = inject(Store<{user:UserState}>)

  constructor(private http: HttpClient, private router: Router) {}

  login(id: number, password: string) {
    return this.http.get<{ token: string }>(`${this.baseURL}/login/validate/${id}?password=${password}`).subscribe({
      next: (response) => {
        localStorage.setItem(this.tokenKey, response.token);
        addMessage({type:"success", message:"Loged In"});          
        this.LogginSequence(id);
      },
      error: (error) => {
        if(error.error == "Wrong Password"){
          addMessage({type:"failure", message:"Wrong Password"});
        }
        else{
          this.http.get(`Usersregistration/${id}`).subscribe({
            next:() =>{ addMessage({type:"warning", message:"Approve User Pending"});},
            error:() => {addMessage({type:"failure", message:"User Not Found"});}
          });
        }
      },
    });
  }

  LogginSequence(id:number){
    var userData:any;
    var permissions:any[] = [];

    this.http.get(`${this.baseURL}/user/${id}`).subscribe(
      (res) => {
        userData = res;
        this.http.get(`${this.baseURL}/permission/${userData.role}`).subscribe(
          (res) => {
              permissions = Object.values(res).map((item:any)=>item.permissionName);

              this.User = {
                id:userData.id,
                name:userData.name,
                email:userData.email,
                password:userData.password,
                role:userData.role,
                permissions:permissions,
                profilepicture:userData.profilePicture,
                createdat:userData.createdAt,
              }
              this.userstore.dispatch(saveUserData(this.User));
              setTimeout(() => {
                this.router.navigate(["/dashboard"]);
              }, 500);
          },
          (error) => {addMessage({type:"failure", message:"Error Getting Data From Server"});}
        )
      },
      (error) => {addMessage({type:"failure", message:"Error Getting Data From Server"});}
    )  
  }


  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  isAuthenticated(): boolean {
    return !!this.getToken();
  }
}