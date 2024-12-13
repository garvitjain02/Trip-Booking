import { NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-landing-page',
  imports: [FormsModule, NgIf],
  templateUrl: './landing-page.component.html',
  styleUrl: './landing-page.component.css'
})

export class LandingPageComponent {
  checkInDate: any;
  checkOutDate: any;
  guests: number;
  location: string;
  msg: string | undefined;

  constructor(private router: Router) {
    let d = new Date();
    this.checkInDate = d.toISOString().split('T')[0];
    
    d.setDate(d.getDate()+1);
    this.checkOutDate = d.toISOString().split('T')[0];

    this.guests = 2;
    this.location = "Delhi";
  }


  search() {
    let d = new Date();
    let d1 = new Date();
    d1.setDate(d.getDate()+1);

    if (this.checkInDate < d.toISOString().split('T')[0])
      this.msg = "Check-In Date cannot be previous date";
    else if (this.checkOutDate < this.checkInDate)
      this.msg = "Check-Out Date cannot be less than Check-In Date";
    else if (this.checkOutDate < d1.toISOString().split('T')[0] || this.checkInDate === this.checkOutDate)
      this.msg = "Check-Out Date should at least be one day ahead of Check-In";
    else if (this.guests < 1 || this.guests > 10)
      this.msg = "Choose Guests within the range provided";
    else {
      this.router.navigateByUrl("/hotel/search?location="+this.location+"&checkInDate="+this.checkInDate+"&checkOutDate="+this.checkOutDate+"&guests="+this.guests);

    }
  }
}
