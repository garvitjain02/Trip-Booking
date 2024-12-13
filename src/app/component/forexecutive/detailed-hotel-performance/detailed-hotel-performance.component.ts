import { Component } from '@angular/core';
import { PerformanceService } from '../../../service/performance.service';
import { NgFor, NgIf } from '@angular/common';
import { Router } from '@angular/router';
import { Chart } from 'chart.js';

@Component({
  imports:[NgFor,NgIf],
  selector: 'app-detailed-hotel-performance',
  templateUrl: './detailed-hotel-performance.component.html',
  styleUrls: ['./detailed-hotel-performance.component.css']
})
export class DetailedHotelPerformanceComponent {
  hotel: any;
  hotelBookings: any[] = [];
  showBookings: boolean = false; // Control to toggle booking display
  monthlyRevenue: { [month: string]: number } = {};
  hotelRatings:any[]=[]

  constructor(private performanceService: PerformanceService, private router: Router) {

    this.performanceService.getRatingsByHotelId(localStorage.getItem("hotelId")).subscribe({
      next:(data)=>{
        console.log(data)
        this.hotelRatings=data
      },
      error:()=>{}
    })


    this.performanceService.getHotelById(localStorage.getItem("hotelId")).subscribe({
      next: (data) => {
        this.hotel = data;
      },
      error: () => {}
    });

    this.performanceService.getAllHotelBookingsByHotelId(localStorage.getItem("hotelId")).subscribe({
      next: (data) => {
        this.hotelBookings = data;
        this.calculateMonthlyRevenue(data);
        this.createMonthlyRevenueChart(); // Generate chart after processing data
      },
      error: () => {}
    });
  }

  toggleBookings() {
    this.showBookings = !this.showBookings;
  }

  deleteHotel(id: any) {
    this.performanceService.deleteHotelById(localStorage.getItem("hotelId")).subscribe({
      next: (data) => {
        this.router.navigate(["/executive/performance/entity"]);
      },
      error: () => {}
    });
  }

  // Calculate monthly revenue from hotel bookings
  calculateMonthlyRevenue(bookings: any[]): void {
    bookings.forEach((booking) => {
      const date = new Date(booking.bookingDate);
      const month = date.toLocaleString('default', { month: 'long' });

      if (!this.monthlyRevenue[month]) {
        this.monthlyRevenue[month] = 0;
      }
      this.monthlyRevenue[month] += booking.amount;
    });
  }

  // Create a bar chart for monthly revenue
  createMonthlyRevenueChart(): void {
    const months = Object.keys(this.monthlyRevenue);
    const revenues = Object.values(this.monthlyRevenue);

    const ctx = document.getElementById('hotelMonthlyRevenueBarChart') as HTMLCanvasElement;
    new Chart(ctx, {
      type: 'bar',
      data: {
        labels: months,
        datasets: [
          {
            label: 'Revenue (₹)',
            data: revenues,
            backgroundColor: 'rgba(75, 192, 192, 0.6)',
            borderColor: 'rgba(75, 192, 192, 1)',
            borderWidth: 1,
          },
        ],
      },
      options: {
        responsive: true,
        scales: {
          y: {
            beginAtZero: true,
            title: {
              display: true,
              text: 'Revenue (₹)',
            },
          },
          x: {
            title: {
              display: true,
              text: 'Months',
            },
          },
        },
        plugins: {
          legend: {
            display: true,
            position: 'top',
          },
        },
      },
    });
  }

}
