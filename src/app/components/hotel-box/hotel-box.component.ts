import { NgFor } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { HotelService } from '../../service/hotel.service';
import { SearchService } from '../../service/search.service';

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

  constructor(private actRoute: ActivatedRoute, private hotelService: HotelService, private router: Router, private searchService: SearchService) {
    
  }

  ngOnInit(): void {

    this.searchService.search.subscribe(s => {
      console.log(s);
      this.checkInDate = s.checkInDate,
      this.checkOutDate = s.checkOutDate,
      this.guests = s.guests,
      this.location = s.location
      console.log(this.checkInDate);
    console.log(this.checkOutDate);
    console.log(this.guests);
    console.log(this.location);
    this.getHotel();
    });
    

    // this.actRoute.queryParams.subscribe(p => {
    //   this.checkInDate = p['checkInDate'];
    //   this.checkOutDate = p['checkOutDate'];
    //   this.guests = p['guests'];
    //   this.location = p['location'];
    // });

    // console.log(this.checkInDate);
    // console.log(this.checkOutDate);
    // console.log(this.guests);
    // console.log(this.location);
    // this.getHotel();
    
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
