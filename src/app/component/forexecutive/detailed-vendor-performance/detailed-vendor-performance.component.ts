import { Component } from '@angular/core';
import { Chart } from 'chart.js';
import { PerformanceService } from '../../../service/performance.service';
import { NgFor, NgIf } from '@angular/common';
import { UsersService } from '../../../service/users.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-detailed-vendor-performance',
  imports: [NgFor, NgIf],
  templateUrl: './detailed-vendor-performance.component.html',
  styleUrl: './detailed-vendor-performance.component.css'
})
export class DetailedVendorPerformanceComponent {

  hotels:any[]=[];
  whatRole:any=false;
  vendor:any;

  showHotels:any=false;

  toggleShowHotels(): void {
    this.showHotels = !this.showHotels;
}

deleteVendor(){
  this.performanceService.deleteUsersByUsername(localStorage.getItem('vendorUsername')).subscribe({
    next:(data)=>{
      this.router.navigate(["/executive/performance"]);
    },
    error:()=>{}
  })
}


  constructor(private router: Router,private performanceService: PerformanceService, private usersService: UsersService){
    this.usersService.getByUsername(localStorage.getItem('vendorUsername')).subscribe({
      next:(data)=>{
        this.vendor=data;
        //console.log(this.vendor);
      },
      error:()=>{}
    })
    if(localStorage.getItem('role')=='HOTEL_VENDOR'){
      this.whatRole=true;
      this.performanceService.getHotelsByVendorUsername(localStorage.getItem('vendorUsername')).subscribe({
        next:(data)=>{
          this.hotels=data;
          console.log(this.hotels);
        },
        error:()=>{}
      })
    }
    else{
      this.whatRole=false;
      this.performanceService.getFlightsByVendorUsername(localStorage.getItem('vendorUsername')).subscribe({
        next:(data)=>{
          this.hotels=data;
          console.log(this.hotels);
        },
        error:()=>{}
      })
    }
  }

  logsData:any[]=[]
  showLogs(id:any){
    console.log(id)
    this.usersService.getLogsByUserId(id).subscribe({
      next:(data)=>{
        console.log(data)
        this.logsData = data;
      },
      error:()=>{}
    })
  }

  navigateToHotelDetails(id:any){
    localStorage.setItem('hotelId',id)
    this.router.navigate(['/executive/performance/detailedhp']);
  }

  navigateToFlightDetails(id:any){
    localStorage.setItem('flightId',id)
    this.router.navigate(['/executive/performance/detailedfp']);
  }

}
