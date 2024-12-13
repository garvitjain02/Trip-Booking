import { NgFor } from '@angular/common';
import { Component } from '@angular/core';
import { RequestService } from '../../../service/request.service';

@Component({
  selector: 'app-hotel-requests',
  imports: [NgFor],
  templateUrl: './hotel-requests.component.html',
  styleUrl: './hotel-requests.component.css'
})
export class HotelRequestsComponent {

  hotelRequests: any[]=[];

  //in the post add api add statements to fetch and add vendor object using vendor username
  //create hotel entity model to save the requests after being approved
  //write code for approve button
  constructor(private requestService: RequestService ){
    this.requestService.getHotelRequest().subscribe({
      next:(data)=>{
        console.log(data);
        this.hotelRequests= data; //using data.content instead of just data as pagination is used in api so it returns an object and has to be converted to array
      },
      error:()=>{}
    })
  }

  // Approve hotel request
  approveRequest(requestId: number): void {
    //finding the approved object using the request id
    /*
    request => request.id === requestId:
This is an arrow function (a concise way to write a function) used as the condition for the .find() method.
For each request in the hotelRequests array, it checks if the id property of request matches the requestId provided to the approveRequest method.
request.id is the id of the current element being evaluated in the iteration.
requestId is the parameter passed to the approveRequest method, which represents the ID of the request that needs to be found.
    */
    let approvedRequest = this.hotelRequests.find(request => request.id === requestId);
    console.log(approvedRequest)
    let trialData = {
      name: approvedRequest.hotelName,
      address: approvedRequest.location, 
      rooms: approvedRequest.numberOfRooms,
      stars: 0, 
      owner: {
        id: approvedRequest.vendor.id, 
        role: "HOTEL_VENDOR" 
      },
      location: {
        city:"MUMBAI",
        state: "MAHARASHTRA",
        country: "INDIA",
        cityCenter: "CHURCHGATE"
      }
    }
    console.log(trialData);
    
    this.requestService.postHotelRequest(trialData).subscribe({  
      next:(data)=>{
        console.log("approved data:"+data);
        this.hotelRequests = this.hotelRequests.filter(request => request.id !== requestId);
        this.requestService.deleteHotelRequest(requestId).subscribe({
          next: (data) => {
            // Remove the approved request from the local list
            this.hotelRequests = this.hotelRequests.filter(request => request.id !== requestId);
          },
          error: (error) => {
            console.error('Error deleting request:', error);
          }
        });
      },
      error:()=>{
      }
    })
  }

  deleteRequest(requestId: number): void {
    this.requestService.deleteHotelRequest(requestId).subscribe({
      next: (data) => {
        // Remove the deleted request from the local list
        this.hotelRequests = this.hotelRequests.filter(request => request.id !== requestId);
      },
      error: (error) => {
        console.error('Error deleting request:', error);
      }
    });
  }
  

}
