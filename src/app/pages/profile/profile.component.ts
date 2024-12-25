import { Component } from '@angular/core';
import { NavbarComponent } from "../../components/navbar/navbar.component";
import { NgFor } from '@angular/common';
import { BookingService } from '../../service/booking.service';
import { ProfileService } from '../../service/profile.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-profile',
  imports: [NavbarComponent, RouterLink],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
  profile: any;

  constructor (private profileService: ProfileService) { 
    profileService.getUserDetails().subscribe({
      next : (data) => {
        this.profile = data;
        console.log(this.profile);
      },
      error : (err) => {
        console.log(err);
      }
    })
  }
}
