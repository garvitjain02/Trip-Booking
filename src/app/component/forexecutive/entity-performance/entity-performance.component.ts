import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { PerformanceService } from '../../../service/performance.service';
import { NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  imports: [NgFor, NgIf, FormsModule],
  selector: 'app-entity-performance',
  templateUrl: './entity-performance.component.html',
  styleUrls: ['./entity-performance.component.css']
})
export class EntityPerformanceComponent {
  hotels: any[] = [];
  flights: any[] = [];
  filteredHotels: any[] = [];
  filteredFlights: any[] = [];
  searchQuery: string = ''; // Holds the search input
  showHotels = true;
  showFlights = false;

  constructor(private router: Router, private performanceService: PerformanceService) {
    this.performanceService.getAllFlight().subscribe({
      next: (data) => {
        this.flights = data;
        this.filteredFlights = this.flights; // Initialize filtered list
        console.log(this.flights);
      },
      error: () => {}
    });

    this.performanceService.getAllHotel().subscribe({
      next: (data) => {
        this.hotels = data;
        this.filteredHotels = this.hotels; // Initialize filtered list
        //console.log(this.hotels);
      },
      error: () => {}
    });
  }

  toggleHotels(): void {
    this.showHotels = true;
    this.showFlights = false;
    this.searchQuery = ''; // Reset search query when toggling
    this.filteredHotels = this.hotels; // Reset filtered list
  }

  toggleFlights(): void {
    this.showFlights = true;
    this.showHotels = false;
    this.searchQuery = ''; // Reset search query when toggling
    this.filteredFlights = this.flights; // Reset filtered list
  }

  navigateToHotelDetailedPage(hotelId: any): void {
    localStorage.setItem('hotelId', hotelId);
    this.router.navigate(['/executive/performance/detailedhp']);
  }

  navigateToFlightDetailedPage(flightId: any): void {
    localStorage.setItem('flightId', flightId);
    this.router.navigate(['/executive/performance/detailedfp']);
  }

  filterHotels(): void {
    const query = this.searchQuery.toLowerCase().trim();
    this.filteredHotels = this.hotels.filter((hotel) =>
      hotel.name.toLowerCase().includes(query)
    );
  }

  filterFlights(): void {
    const query = this.searchQuery.toLowerCase().trim();
    this.filteredFlights = this.flights.filter((flight) =>
      flight.flightNumber.toLowerCase().includes(query) ||
      flight.airline.toLowerCase().includes(query)
    );
  }
}
