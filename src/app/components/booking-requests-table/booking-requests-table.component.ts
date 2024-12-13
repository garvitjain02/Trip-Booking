import { NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { BookingService } from '../../service/booking.service';

@Component({
  selector: 'app-booking-requests-table',
  imports: [NgIf, NgFor],
  templateUrl: './booking-requests-table.component.html',
  styleUrl: './booking-requests-table.component.css'
})
export class BookingRequestsTableComponent implements OnInit {
  pendingRequests: boolean = true;
  tempData: any[] = [];
  tempDataCopy: any[] = [];

  // tempData: any[] = [
  //   {
  //     customer: "John Doe",
  //     hotel: "Grand Plaza",
  //     checkInDate: "2024-12-01",
  //     checkOutDate: "2024-12-07",
  //     amount: 1500.00,
  //     approval: "Pending"
  //   },
  //   {
  //     customer: "Jane Smith",
  //     hotel: "Seaside Resort",
  //     checkInDate: "2024-12-03",
  //     checkOutDate: "2024-12-10",
  //     amount: 2000.00,
  //     approval: "Pending"
  //   },
  //   {
  //     customer: "Emily Brown",
  //     hotel: "Mountain View Inn",
  //     checkInDate: "2024-12-05",
  //     checkOutDate: "2024-12-12",
  //     amount: 1200.00,
  //     approval: "Pending"
  //   },
  //   {
  //     customer: "Michael Johnson",
  //     hotel: "City Center Hotel",
  //     checkInDate: "2024-12-10",
  //     checkOutDate: "2024-12-15",
  //     amount: 1800.00,
  //     approval: "Approved"
  //   },
  //   {
  //     customer: "Sarah Lee",
  //     hotel: "Beachfront Suites",
  //     checkInDate: "2024-12-15",
  //     checkOutDate: "2024-12-20",
  //     amount: 2200.00,
  //     approval: "Rejected"
  //   }
  // ];
   
  constructor(private bookingService: BookingService) {
    this.getData();
  }

  ngOnInit(): void {
  }

  getData() {
    this.bookingService.bookingRequests().subscribe({
      next : (data) => {
        this.tempData = data;
        // console.log(this.tempData);
        this.tempDataCopy = this.tempData;
        this.pending();
      },
      error : (err) => {
        console.log(err);
      }
    })
  }

  pending() {
    this.tempData = this.tempDataCopy;
    this.tempData = this.tempData.filter(t => t.approvalStatus === "PENDING");
    // console.log(this.tempData);
    this.pendingRequests = true;
  }

  approved() {
    this.tempData = this.tempDataCopy;
    this.tempData = this.tempData.filter(t => t.approvalStatus === "APPROVED");
    console.log(this.tempData);
    this.pendingRequests = false;
  }
  
  rejected() {
    this.tempData = this.tempDataCopy;
    this.tempData = this.tempData.filter(t => t.approvalStatus === "NOT_APPROVED");
    console.log(this.tempData);
    this.pendingRequests = false;
  }

  changeStatus(status: string, i: number) {
    // this.tempData[i].approvalStatus = status;
    // this.tempDataCopy[i].approvalStatus = status;
    // this.tempData = this.tempDataCopy;
    // this.pending();

    console.log(this.tempData[i]);
    this.bookingService.updateApprovalStatus(this.tempData[i].id, status).subscribe({
      next : (data) => {
        this.tempData[i] = data;
        this.getData();
      },
      error : (err) => {
        console.log(err);
      }
    })
  }
}
