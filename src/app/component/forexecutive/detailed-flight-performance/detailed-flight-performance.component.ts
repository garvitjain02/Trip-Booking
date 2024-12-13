import { Component } from '@angular/core';
import { PerformanceService } from '../../../service/performance.service';
import { Router } from '@angular/router';
import { NgFor, NgIf } from '@angular/common';
import { Chart } from 'chart.js';

@Component({
  imports:[NgFor,NgIf],
  selector: 'app-detailed-flight-performance',
  templateUrl: './detailed-flight-performance.component.html',
  styleUrl: './detailed-flight-performance.component.css'
})
export class DetailedFlightPerformanceComponent {
  flight: any = null; // Holds flight details
  flightBookings: any[] = []; // Holds all flight bookings
  showBookings = false; // Controls visibility of bookings table
  monthlyRevenue: { [month: string]: number } = {};
  flightRatings:any[]=[]

  constructor(private performanceService: PerformanceService, private router: Router) {

    this.performanceService.getRatingsByFlightId(localStorage.getItem("flightId")).subscribe({
      next:(data)=>{
        console.log(data)
        this.flightRatings=data
        console.log("flightrating:"+this.flightRatings)
      },
      error:()=>{}
    })

      // Fetch flight details
      this.performanceService.getFlightById(localStorage.getItem('flightId')).subscribe({
        next: (data) => {
          this.flight = data;
        },
        error: (err) => {
          console.error('Error fetching flight details:', err);
        }
      });

    this.performanceService.getAllFlightBookingsByFlightId(localStorage.getItem('flightId')).subscribe({
      next: (data) => {
        this.flightBookings = data;
        this.calculateMonthlyRevenue(data);
        //this.createChart();
      },
      error: (err) => {
        console.error('Error fetching flight bookings:', err);
      }
    });
  }

  toggleBookings(): void {
    this.showBookings = !this.showBookings;
  }

  deleteFlight(id: any): void {
    const flightId = localStorage.getItem('flightId');
    if (flightId) {
      this.performanceService.deleteFlightById(flightId).subscribe({
        next: () => {
          this.router.navigate(['/executive/performance/entity']);
        },
        error: (err) => {
          console.error('Error deleting flight:', err);
        }
      });
    }
  }

  private calculateMonthlyRevenue(flightBookings:any[]): void {
    for (const booking of flightBookings) {
      const date = new Date(booking.bookingDate);
      const month = date.toLocaleString('default', { month: 'long' });
      if (!this.monthlyRevenue[month]) {
        this.monthlyRevenue[month] = 0;
      }
      this.monthlyRevenue[month] += booking.amount;
    }
    const ctx = document.getElementById('monthlyRevenueChart') as HTMLCanvasElement;
    new Chart(ctx, {
      type: 'bar',
      data: {
        labels: Object.keys(this.monthlyRevenue),
        datasets: [{
          label: 'Monthly Revenue',
          data: Object.values(this.monthlyRevenue),
          backgroundColor: 'rgba(54, 162, 235, 0.2)',
          borderColor: 'rgba(54, 162, 235, 1)',
          borderWidth: 1
        }]
      },
      options: {
        responsive: true,
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
  }

}
