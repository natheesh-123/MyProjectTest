import { Component } from '@angular/core';
import { DbservicesService } from '../../services/db/dbservices.service'; 
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { addMessage } from '../../../common/popupmessage';
import { inject } from '@angular/core';
	import { Store } from '@ngrx/store';
	import { UserState } from '../../../store/user/user.state';

@Component({
  selector: 'app-view-all-attendancerequest',
  imports: [CommonModule,FormsModule],
  templateUrl: './view-all-attendancerequest.component.html',
  styleUrl: './view-all-attendancerequest.component.css'
})
export class ViewAllAttendancerequestComponent {

  private userstore = inject(Store<{user:UserState}>);
  public user:any;
  allattendanceRequests: any[] = [];

  constructor(private _route:Router,private allAttendanceReq: DbservicesService) { }

  ngOnInit(): void {
    this.userstore.select(state => state.user).subscribe(data => this.user=data);
      if(!this.user.permissions.includes("AllAttendanceRequest") ){
        addMessage({type:"warning", message:"Access Denied"});
        this._route.navigate(['/']);
      }
      else
        this.loadAttendanceRequests();
  }

  loadAttendanceRequests(): void {
    this.allAttendanceReq.getRecord('Attendancerequest').subscribe(
      (data:any) => {
        this.allattendanceRequests = data;
      },
      (error:any) => {
        addMessage({type:"failure", message:"Error Getting Data From Server"});
      }
    );
  }

  onSubmit(request: any): void {
    const updatedData = {
      userId: request.userId,
      date: request.date,
      status: 'present',
      remarks:null 
    };
    this.allAttendanceReq.postRecord('Attendance', updatedData).subscribe(
      (response: any) => {

        this.allAttendanceReq.deleteRecord(`Attendancerequest/${request.id}`).subscribe(
          (deleteResponse: any) => {
            addMessage({type:"success", message:"Request Accepted"});
            this.loadAttendanceRequests();
          },
          (deleteError: any) => {
            addMessage({type:"failure", message:"Failed to Accept"});
          }
        );

      },
      (error: any) => {
        addMessage({type:"failure", message:"Error Adding Attendance"});
      }
    );

  }



  onReject(request: any): void {

    this.allAttendanceReq.deleteRecord(`Attendancerequest/${request.id}`).subscribe(
      (deleteResponse: any) => {
        const updatedData = {
          userId: request.userId,
          date: request.date,
          status: 'Absent',
          remarks:null 
        };
        this.allAttendanceReq.postRecord('Attendance', updatedData).subscribe(
          (response: any) => {
            addMessage({type:"warning", message:"Rejected"});
          }
          ,()=>{
            addMessage({type:"failure", message:"Error Adding Attendance"});
          }
       );
  

        this.loadAttendanceRequests();
      },
      (deleteError: any) => {
        console.error('Error deleting attendance request', deleteError);
      }
    );
    
   
  }

  

}
