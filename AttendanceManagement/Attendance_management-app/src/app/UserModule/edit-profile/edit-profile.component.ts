import { Component, inject } from '@angular/core';
import { Store } from '@ngrx/store';
import { UserState } from '../../../store/user/user.state';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { saveUserData } from '../../../store/user/user.actions';
import { CommonModule } from '@angular/common';
import { DbservicesService } from '../../services/db/dbservices.service';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { addMessage } from '../../../common/popupmessage';

@Component({
  selector: 'app-edit-profile',
    imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './edit-profile.component.html',
  styleUrl: './edit-profile.component.css'
})
export class EditProfileComponent {

  private userstore = inject(Store<{user:UserState}>)
      public user:any;
      public profileImageUrl:any;
      public canEdit:boolean = false;
      public editingPhoto:boolean = false;
      public editingDetails:boolean = false;
      selectedFile:any;
      detailsForm:any;
  
    constructor(private _route:Router, private _http: DbservicesService){}
      
      ngOnInit(){
        this.userstore.select(state => state.user).subscribe(data => this.user=data);
        
        if(!this.user.permissions.includes("ViewProfile"))
        {
          addMessage({type:"warning", message:"You Have no permission"});
          setTimeout(() => {
            this._route.navigate(['/']);        
          }, 1000);
        }
  
        if(this.user.permissions.includes("EditProfile")) 
          this.canEdit = true;
  
        this.detailsForm = new FormGroup({
              name:new FormControl("",[Validators.required]),
              email:new FormControl("", [Validators.required]),
              password:new FormControl("",[Validators.required])
            })
        
        this.profileImageUrl = `${this._http.baseURL}/Image/Get/${this.user.profilepicture}`;
      };
  
      onFileSelected(event:any){
        this.selectedFile = event.target.files[0];
        addMessage({type:"warning", message:"Now Upload The Image"});
      }
    
      onUpload() {
        if (!this.selectedFile) {
          addMessage({type:"warning", message:"Please Select an Image"});
          return;
        }
        
        const fileExtension = this.selectedFile.name.split('.').pop();
        if(fileExtension != "jpg"){
          addMessage({type:"warning", message:"Please Select an JPG file"});
          return;        
        }
    
        const formData = new FormData();
        formData.append('file', this.selectedFile, `${this.user.id}.${fileExtension}`);
        
        // Send the file to the server
        this._http.postRecord("Image/Upload", formData).subscribe(
          (response: any) => {
            var User = {
              ...this.user,
              profilepicture:`${this.user.id}.jpg`,
            }
            this.userstore.dispatch(saveUserData(User));
            localStorage.setItem('user', JSON.stringify(User));
            this.profileImageUrl = `${this._http.baseURL}/Image/Get/${this.user.profilepicture}`;
            this.editingPhoto=false;
            var updatedUser = {
              id: this.user.id,
              name: this.user.name,
              email: this.user.email,
              password: this.user.password,
              role: this.user.role,
              profilepicture: this.user.profilepicture,
              createdat:this.user.createdat
            }
            this._http.updateRecord(`User/${this.user.id}`, updatedUser).subscribe(
              (res)=>{
                addMessage({type:"success", message:"Uploaded the Image"});
                setTimeout(() => {
                  window.location.reload();
                }, 1000);
              },
              (error)=>{
                addMessage({type:"failure", message:"Upload Failed"});
              }
            );
          },
          (error: any) => {
            addMessage({type:"failure", message:"Upload Failed"});
          }
        );
      }
      
      changeToForm(){
        this.editingDetails = true;
        this.editingPhoto=false;
        this.showPassword = false;
  
        this.detailsForm.setValue({
          name: this.user.name,
          email: this.user.email,
          password: this.user.password,
        });
  
      }
  
      updateUser(){
        var userData:any = this.detailsForm.value;
        var updatedUser = {
          id: this.user.id,
          name: userData.name,
          email: userData.email,
          password: userData.password,
          role: this.user.role,
          profilepicture: this.user.profilepicture,
          createdat:this.user.createdat
      }
      this._http.updateRecord(`User/${this.user.id}`, updatedUser).subscribe(
        (res)=>{
          addMessage({type:"success", message:"Updated Successfully"});
          this.showPassword = false;
          var User = {
            id:this.user.id,
            name:userData.name,
            email:userData.email,
            password:userData.password,
            role:this.user.role,
            permissions:this.user.permissions,
            profilepicture:this.user.profilepicture,
            createdat:this.user.createdat
          }
          this.userstore.dispatch(saveUserData(User));
          localStorage.setItem('user', JSON.stringify(User));
          this.editingDetails = false;
        },
        (error)=>{
          addMessage({type:"failure", message:"Update Failes"});
        }
      )
      }

      validate(formcontrolname:any){
        if(formcontrolname == "id"){
          if(this.detailsForm.get("id").invalid){
            if(this.detailsForm.get("id").errors.pattern){
              addMessage({type:"warning", message:"Id is a 3 or 4 Digit Number"});
            }
            if(this.detailsForm.get("id").errors.required){
              addMessage({type:"warning", message:"id Field is Requied"});
            }
          }
        }
        else if(formcontrolname == "password"){
          if(this.detailsForm.get("password").invalid){
            if(this.detailsForm.get("password").errors.pattern){
              addMessage({
                type:"warning", 
                message:"Password should have Lower, Upper, Digits, Special Characters "});
            }
            if(this.detailsForm.get("password").errors.required){
              addMessage({type:"warning", message:"Password Field is Requied"});
            }
          }
        }
        else if(formcontrolname == "email"){
          if(this.detailsForm.get("email").invalid){
            if(this.detailsForm.get("email").errors.pattern){
              addMessage({type:"warning", message:"InValid Email"});
            }
            if(this.detailsForm.get("email").errors.required){
              addMessage({type:"warning", message:"Email is Requied"});
            }
          }
        }
        else if(formcontrolname == "name"){
          if(this.detailsForm.get("name").invalid){
            if(this.detailsForm.get("name").errors.pattern){
              addMessage({type:"warning", message:"Name should be 3 - 20 characters"});
            }
            if(this.detailsForm.get("name").errors.required){
              addMessage({type:"warning", message:"Name Field is Requied"});
            }
          }
        }
      else if(formcontrolname == "role"){
              if(this.detailsForm.get("role").invalid){
                if(this.detailsForm.get("role").errors.required){
                  addMessage({type:"warning", message:"Role Field is Requied"});
                }
              }
            }
        
      }


      showPassword: any;
togglePassword(){
    this.showPassword = !this.showPassword;
  }
}
