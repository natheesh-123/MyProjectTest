import { Component, OnInit } from '@angular/core';
import { DbservicesService } from '../../services/db/dbservices.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { inject } from '@angular/core';
	import { Store } from '@ngrx/store';
	import { UserState } from '../../../store/user/user.state';
import { addMessage } from '../../../common/popupmessage';

@Component({
  selector: 'app-attendance-request-teacher',
  imports: [CommonModule],
  templateUrl: './attendance-request-teacher.component.html',
  styleUrl: './attendance-request-teacher.component.css'
})
export class AttendanceRequestTeacherComponent implements OnInit {

  private userstore = inject(Store<{user:UserState}>);
  	public user:any;
  attendanceRequests: any[] = [];

  constructor(private _route:Router,private attendanceReqTeacher: DbservicesService) { }

  ngOnInit(): void {
    this.userstore.select(state => state.user).subscribe(data => this.user=data);
      if(!this.user.permissions.includes("TeachersAttendanceRequest")){
        this._route.navigate(['/']);
      }
      else
        this.loadAttendanceRequests();
  }

  loadAttendanceRequests(): void {
    this.attendanceReqTeacher.getRecord('Attendancerequest/byrole?role=Teacher').subscribe(
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

    this.attendanceReqTeacher.postRecord('Attendance', updatedData).subscribe(
      (response: any) => {

        this.attendanceReqTeacher.deleteRecord(`Attendancerequest/${request.id}`).subscribe(
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

    this.attendanceReqTeacher.deleteRecord(`Attendancerequest/${request.id}`).subscribe(
      (deleteResponse: any) => {
        const updatedData = {
          userId: request.userId,
          date: request.date,
          status: 'Absent',
          remarks: null
        };

        this.attendanceReqTeacher.postRecord('Attendance', updatedData).subscribe(
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
