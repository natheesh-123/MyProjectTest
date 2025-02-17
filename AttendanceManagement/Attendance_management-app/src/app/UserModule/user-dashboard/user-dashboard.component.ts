import { Component, ElementRef, inject, ViewChild } from '@angular/core';
import { Store } from '@ngrx/store';
import { UserState } from '../../../store/user/user.state';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { deleteUserData, saveUserData } from '../../../store/user/user.actions';
import { CommonModule } from '@angular/common';
import { DbservicesService } from '../../services/db/dbservices.service';
import { addMessage } from '../../../common/popupmessage';


@Component({
  selector: 'app-user-dashboard',
  imports: [RouterOutlet, CommonModule, RouterLink],
  templateUrl: './user-dashboard.component.html',
  styleUrl: './user-dashboard.component.css'
})
export class UserDashboardComponent {
  private userstore = inject(Store<{user:UserState}>)
  public user:any;
  public profileImageUrl:any;
  @ViewChild('sidebar') sidebar!: ElementRef;  
  @ViewChild('mainContent') mainContent!: ElementRef;  
  public isSidebarOpen: boolean=false;


  constructor(private _route:Router, private _http: DbservicesService){}
  
  ngOnInit(){
    this.userstore.select(state => state.user).subscribe(data => this.user=data);
    this.profileImageUrl =`${this._http.baseURL}/Image/Get/${this.user.profilepicture}`;

    window.addEventListener("beforeunload", this.handleBeforeUnload);
    if(!this.user.id){
      setTimeout(() => {
        this._route.navigate(['/']);
      }, 750);
    }

    document.addEventListener('click', this.handleClick);

  };
  
  ngOnDestroy(){
    addMessage({type:"warning", message:"You have Been Loged out"});  
    window.removeEventListener("beforeunload", this.handleBeforeUnload);
    document.removeEventListener('click', this.handleClick);
    this.userstore.dispatch(deleteUserData());
  }
  
  handleBeforeUnload(event: BeforeUnloadEvent){
    event.preventDefault();
      event.returnValue = 'Reloading the page will log you out, continue?';
    }

  handleClick(event: MouseEvent) {
    if (event.button === 0) { // Left mouse button
      const x = event.clientX;
      if(x > 250){
        document.getElementById("sidebar")?.classList.remove('sidebar-open');
      }
    }
  }
  
  LogoutSequence(){
    setTimeout(() => {
      this._route.navigate(['/']);     
    }, 500);
  }
  
  toggleSidebar() {
    var sidebar = document.getElementById("sidebar");
    if(sidebar?.classList.contains('sidebar-open')){
      document.getElementById("sidebar")?.classList.remove('sidebar-open');
      this.isSidebarOpen = false;
    }
    else{
      document.getElementById("sidebar")?.classList.add('sidebar-open');
      this.isSidebarOpen = true;
    }

}

}
