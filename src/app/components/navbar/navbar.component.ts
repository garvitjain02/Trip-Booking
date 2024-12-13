import { NgIf } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { SearchService } from '../../service/search.service';
// import { logo } from '../../images/logo';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink, FormsModule, NgIf],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit {
  
  startDate: string = '';
  endDate: string = '';
  location: string = 'Delhi';
  guests: number = 2;
  msg: string | undefined;
  token: string | null;

  constructor(private router: Router, private actRoute: ActivatedRoute) {
    let d = new Date();
    this.startDate = d.toISOString().split('T')[0];
    
    d.setDate(d.getDate() + 1);
    this.endDate = d.toISOString().split('T')[0];

    this.token = localStorage.getItem('token');
  }

  ngOnInit(): void {
    this.actRoute.queryParams.subscribe(p => {
      this.location = p['location'];
      this.startDate = p['checkInDate'];
      this.endDate = p['checkOutDate'];
      this.guests = p['guests'];
    })
  }

  onSubmit() {
      let d = new Date();
      let d1 = new Date();
      d1.setDate(d.getDate()+1);
  
      if (this.startDate < d.toISOString().split('T')[0])
        this.msg = "Check-In Date cannot be previous date";
      else if (this.endDate < this.startDate)
        this.msg = "Check-Out Date cannot be less than Check-In Date";
      else if (this.endDate < d1.toISOString().split('T')[0] || this.startDate === this.endDate)
        this.msg = "Check-Out Date should at least be one day ahead of Check-In";
      else if (this.guests < 1 || this.guests > 10)
        this.msg = "Choose Guests within the range provided";
      else {
        this.router.navigateByUrl("/hotel/search?location="+this.location+"&checkInDate="+this.startDate+"&checkOutDate="+this.endDate+"&guests="+this.guests);
    
      }
    }

    logout() {
      localStorage.removeItem('token');
      this.router.navigateByUrl('/login');
    }
}
