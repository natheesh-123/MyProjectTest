import { Component, OnInit } from '@angular/core';
import { DbservicesService } from '../../services/db/dbservices.service'; 
import { CommonModule } from '@angular/common';
import { inject } from '@angular/core';
	import { Store } from '@ngrx/store';
	import { UserState } from '../../../store/user/user.state';
import { addMessage } from '../../../common/popupmessage';
import { Router } from '@angular/router';

@Component({
  selector: 'app-attendance-request-student',
  imports: [CommonModule],
  templateUrl: './attendance-request-student.component.html',
  styleUrl: './attendance-request-student.component.css'
})
export class AttendanceRequestStudentComponent {

  private userstore = inject(Store<{user:UserState}>);
  public user:any;
  attendanceRequests: any[] = [];

  constructor(private _route:Router,private attendanceReqstudent: DbservicesService) { }

  ngOnInit(): void {
    this.userstore.select(state => state.user).subscribe(data => this.user=data);
      if(!this.user.permissions.includes("StudentAttendanceRequest") ){
        addMessage({type:"warning", message:"Access Denied"});
        this._route.navigate(['/']);
      }
      else
      this.loadAttendanceRequests();
  }

  loadAttendanceRequests(): void {
    this.attendanceReqstudent.getRecord('Attendancerequest/byrole?role=Student').subscribe(
      (data: any) => {
        this.attendanceRequests = data;
      },
      (error: any) => {
        addMessage({type:"failure", message:"Error Getting Data From Server"});
      }
    );
  }

  onSubmit(request: any): void {
    const updatedData = {
      userId: request.userId,
      date: request.date,
      status: 'present',
      remarks: null
    };


    this.attendanceReqstudent.postRecord('Attendance', updatedData).subscribe(
      (response: any) => {

        this.attendanceReqstudent.deleteRecord(`Attendancerequest/${request.id}`).subscribe(
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
    this.attendanceReqstudent.deleteRecord(`Attendancerequest/${request.id}`).subscribe(
      (deleteResponse: any) => {

        const updatedData = {
          userId: request.userId,
          date: request.date,
          status: 'Absent',
          remarks: null
        };

        this.attendanceReqstudent.postRecord('Attendance', updatedData).subscribe(
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
        addMessage({type:"failure", message:"Error Deleting Request"});
        
      }
    );


  }





}
