import { Component } from '@angular/core';
import { ChartModule } from 'primeng/chart';
import { Chart } from 'chart.js';
import { PerformanceService } from '../../../service/performance.service';
import { NgFor, NgIf } from '@angular/common';
import { Router } from '@angular/router';
import { UsersService } from '../../../service/users.service';

@Component({
  selector: 'app-detailed-customer-performance',
  imports: [ChartModule, NgFor, NgIf],
  templateUrl: './detailed-customer-performance.component.html',
  styleUrl: './detailed-customer-performance.component.css'
})
export class DetailedCustomerPerformanceComponent {
  // Flags to toggle booking visibility
  showBookings = false; // Hotel bookings
  showFlightBookings = false; // Flight bookings

  customer:any

  // Object to store monthly spending with months as keys and spending as values
  monthlySpending: { [month: string]: number } = {};

  // Arrays to hold booking data for hotels and flights
  bookingData: any[] = [];
  flightBookingData: any[] = [];

  // Total amounts for different types of bookings
  totalBusAmount: any = 0;
  totalHotelAmount: any = 0;
  totalFlightAmount: any = 0;

  constructor(private performanceService: PerformanceService, private router:Router, private usersService: UsersService) {

    this.performanceService.getCustomerByUsername(localStorage.getItem('customerUsername')).subscribe({
      next:(data)=>{
        this.customer=data;
        console.log(this.customer)
      },
      error:()=>{}
    })

    // Fetch hotel bookings data and process it
    this.performanceService.getHotelBookingsByUsername(localStorage.getItem('customerUsername')).subscribe({
      next: (data) => {
        this.bookingData = data;
        this.calculateHotelAmount(); // Calculate total amount spent on hotels
        this.calculateMonthlySpending(data, 'bookingDate'); // Calculate monthly spending for hotels
      },
      error: () => {}
    });

    // Fetch flight bookings data and process it
    this.performanceService.getFlightBookingsByUsername(localStorage.getItem('customerUsername')).subscribe({
      next: (data) => {
        this.flightBookingData = data;
        this.calculateFlightAmount(); // Calculate total amount spent on flights
        this.createCharts(); // Create charts after data is set
        this.calculateMonthlySpending(data, 'bookingDate'); // Calculate monthly spending for flights
        this.createMonthlySpendingChart(); // Create the monthly spending chart
      },
      error: () => {}
    });
  }

  // Toggle visibility for hotel and flight bookings
  toggleShow(type: string): void {
    if (type === 'hotels') {
      this.showBookings = !this.showBookings;
      if (this.showBookings) {
        this.showFlightBookings = false;
      }
    } else if (type === 'flights') {
      this.showFlightBookings = !this.showFlightBookings;
      if (this.showFlightBookings) {
        this.showBookings = false;
      }
    }
  }

  /*deleteUser(){
    this.performanceService.deleteUsersByUsername(localStorage.getItem('customerUsername')).subscribe({
      next:(data)=>{
        this.router.navigate(["/executive/performance"]);
      },
      error:()=>{}
    })
  }*/

  // Method to create the spending donut chart
  createCharts(): void {
    // Data for the Donut Chart
    const spendingByEntityData = {
      labels: ['Hotel', 'Flight'],
      datasets: [{
        label: 'Spending by Entity',
        data: [this.totalHotelAmount, this.totalFlightAmount], // Use actual calculated values
        backgroundColor: ['#FF5733', '#33FF57', '#3357FF'],
        borderWidth: 1
      }]
    };

    // Create the Donut Chart
    new Chart('spendingDonutChart', {
      type: 'doughnut',
      data: spendingByEntityData
    });
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

  // Method to create the monthly spending bar chart
  createMonthlySpendingChart(): void {
    const labels = Object.keys(this.monthlySpending); // Month names
    const data = Object.values(this.monthlySpending); // Monthly totals

    new Chart('monthlySpendingBarChart', {
      type: 'bar',
      data: {
        labels: labels,
        datasets: [{
          label: 'Monthly Spending',
          data: data,
          backgroundColor: 'rgba(75, 192, 192, 0.2)',
          borderColor: 'rgba(75, 192, 192, 1)',
          borderWidth: 1,
        }],
      },
    });
  }

  // Method to calculate monthly spending
  calculateMonthlySpending(data: any[], dateField: string): void {
    for (let booking of data) {
      const date = new Date(booking[dateField]); // Parse booking date
      // booking is the object and dateField is the variable passed
      // booking[dateField] is bookingData[bookingDate] or bookingData.bookingDate
      // it's being converted to TypeScript's date format
      const month = date.toLocaleString('default', { month: 'long' }); // Get month name
      // 'long' retrieves the full name of the month (e.g., January, February)
      // 'default' is the default language of the browser

      if (!this.monthlySpending[month]) { // If the month doesn't exist in db, initialize it
        this.monthlySpending[month] = 0;
      }
      this.monthlySpending[month] += booking.amount; // Add booking amount to the month
    }
  }

  // Method to calculate total amount spent on hotels
  calculateHotelAmount(): void {
    for (let i = 0; i < this.bookingData.length; i++) {
      this.totalHotelAmount += this.bookingData[i].amount; // Add each amount to the total
    }
    console.log("hotel ammt="+this.totalHotelAmount)
  }

  // Method to calculate total amount spent on flights
  calculateFlightAmount(): void {
    for (let i = 0; i < this.flightBookingData.length; i++) {
      this.totalFlightAmount += this.flightBookingData[i].amount; // Add each amount to the total
    }
  }

  navigateToHotelDetails(hotelId:any){
    localStorage.setItem('hotelId',hotelId);
    this.router.navigate(['/executive/performance/detailedhp']);
  }

  navigateToOwnerDetails(username:any, role:any){
    localStorage.setItem('vendorUsername', username);
    localStorage.setItem('role', role);
    this.router.navigate(['/executive/performance/detailedv']);
  }

  navigateToFlightDetails(flightId:any){
    localStorage.setItem('flightId',flightId);
    this.router.navigate(['/executive/performance/detailedfp']);
  }
}
