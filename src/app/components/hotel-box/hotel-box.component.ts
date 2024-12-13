import { NgFor } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { HotelService } from '../../service/hotel.service';

@Component({
  selector: 'app-hotel-box',
  imports: [NgFor],
  templateUrl: './hotel-box.component.html',
  styleUrl: './hotel-box.component.css'
})
export class HotelBoxComponent implements OnInit{
  hotels: any[] = [];
  checkInDate: any;
  checkOutDate: any;
  guests: number = 2;
  location: string = '';

  constructor(private actRoute: ActivatedRoute, private hotelService: HotelService, private router: Router) {
  }

  ngOnInit(): void {
    this.actRoute.queryParams.subscribe(p => {
      this.checkInDate = p['checkInDate'];
      this.checkOutDate = p['checkOutDate'];
      this.guests = p['guests'];
      this.location = p['location'];
    });

    
    this.getHotel();
  }



  getHotel() {
    this.hotelService.getHotels(this.location).subscribe({
      next : (data) => {
        this.hotels = data;
        console.log(data);
      },
      error : (err) => {
        console.log(err);
      }
    })
  }

  hotelDetailPage(id: number) { 
    this.router.navigateByUrl("/hotel/detail/"+ id + "?location="+this.location+"&checkInDate="+this.checkInDate+"&checkOutDate="+this.checkOutDate+"&guests="+this.guests);
  }
}
