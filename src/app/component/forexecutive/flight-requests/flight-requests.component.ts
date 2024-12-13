import { NgFor } from '@angular/common';
import { Component } from '@angular/core';
import { RequestService } from '../../../service/request.service';

@Component({
  selector: 'app-flight-requests',
  imports: [NgFor],
  templateUrl: './flight-requests.component.html',
  styleUrl: './flight-requests.component.css'
})
export class FlightRequestsComponent {

  flightRequests:any[]=[]

  constructor(private requestService: RequestService){
    this.requestService.getFlightRequest().subscribe({
      next:(data)=>{
        this.flightRequests=data
      },
      error:()=>{}
    })
  }
  
    // Method to approve a flight request
    approveRequest(id: any): void {
      let approvedRequest = this.flightRequests.find(request => request.id === id);
      let newData={
          vendor: {
            id: approvedRequest.vendor.id,
            role:"FLIGHT_VENDOR"
          },
          requestDate: approvedRequest.requestData,
          flightNumber: approvedRequest.flightNumber,
          airline: approvedRequest.airline,
          origin: approvedRequest.origin,
          destination: approvedRequest.destination,
          departureTime: approvedRequest.departureTime,
          arrivalTime: approvedRequest.arrivalTime,
          duration: approvedRequest.duration,
          availableSeats: approvedRequest.availableSeats,
          price: approvedRequest.price
        }
        this.requestService.postFlightRequest(newData).subscribe({
          next:(data)=>{
            this.requestService.deleteFlightRequest(id).subscribe({
              next:(data)=>{
                this.flightRequests = this.flightRequests.filter(request => request.id !== id);
              },
              error:()=>{}
            })
          },
          error:()=>{}
        })
    }
  
    // Method to delete a flight request
    deleteRequest(id: any): void {
      this.requestService.deleteFlightRequest(id).subscribe({
        next:(data)=>{
          this.flightRequests = this.flightRequests.filter(request => request.id !== id);
        },
        error:()=>{}
      })
    }

}
