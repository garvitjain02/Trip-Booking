import { Component } from '@angular/core';
import { BookingService } from '../../service/booking.service';
import { NavbarComponent } from "../../components/navbar/navbar.component";
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-bookings',
  imports: [NavbarComponent, NgFor],
  templateUrl: './bookings.component.html',
  styleUrl: './bookings.component.css'
})
export class BookingsComponent {
  bookings: any[] = [];

  constructor (private bookingService: BookingService) {
    bookingService.bookingsByUser().subscribe({
      next : (data) => {
        this.bookings = data;
      },
      error : (err) => {
        console.log(err);
      }
    })
  }
}
