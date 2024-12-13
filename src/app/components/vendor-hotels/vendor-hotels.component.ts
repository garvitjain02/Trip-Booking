import { NgFor, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { HotelService } from '../../service/hotel.service';

@Component({
  selector: 'app-vendor-hotels',
  imports: [NgFor, NgIf],
  templateUrl: './vendor-hotels.component.html',
  styleUrl: './vendor-hotels.component.css'
})
export class VendorHotelsComponent {
  hotels: any[] = [];

  constructor(private hotelService: HotelService, private router: Router) {
    // this.hotels = [
    //   {
    //     "hotel_name": "Grand Plaza Hotel",
    //     "location_from_city_centre": "2 km",
    //     "types_of_rooms": ["Single", "Double", "Suite"],
    //     "advantages": ["Complimentary Breakfast", "Free Cancellation", "Free Parking"],
    //     "starting_price": 120,
    //     "facilities": ["Free Wifi", "Spa", "Swimming Pool", "Fitness Center", "Restaurant"],
    //     "rating_in_stars": 5,
    //     "url" : "img4.png"
    //   },
    //   {
    //     "hotel_name": "Oceanview Resort",
    //     "location_from_city_centre": "10 km",
    //     "types_of_rooms": ["Ocean View", "Garden View", "Family Room"],
    //     "advantages": ["Complimentary Breakfast", "Free Cancellation", "Airport Shuttle"],
    //     "starting_price": 200,
    //     "facilities": ["Free Wifi", "Private Beach", "Swimming Pool", "Spa", "Restaurant"],
    //     "rating_in_stars": 4,
    //     "url" : "img5.png"
    //   },
    //   {
    //     "hotel_name": "City Inn",
    //     "location_from_city_centre": "1 km",
    //     "types_of_rooms": ["Standard", "Deluxe", "Family Suite"],
    //     "advantages": ["Free Cancellation", "24/7 Room Service"],
    //     "starting_price": 80,
    //     "facilities": ["Free Wifi", "Restaurant", "Business Center", "Laundry Service"],
    //     "rating_in_stars": 3,
    //     "url" : "img6.png"
    //   }
    // ];
  
    hotelService.getHotelByVendor().subscribe({
      next : (data) => {
        this.hotels = data;
        console.log(data);
      },
      error : (err) => {
        console.log(err);
      }
    })
  }

  editHotel(id: any) {
    this.router.navigateByUrl("vendor/hotel/edit/" + id);
  }
}
