import { NgFor } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { RoomSelectionService } from '../../service/room-selection.service';
import { AuthService } from '../../service/auth.service';
import { BookingService } from '../../service/booking.service';

@Component({
  selector: 'app-selected-room-overview',
  imports: [NgFor],
  templateUrl: './selected-room-overview.component.html',
  styleUrl: './selected-room-overview.component.css'
})
export class SelectedRoomOverviewComponent implements OnInit {
  rooms:any[] = [];
  //   {
  //     "room_type": "Deluxe Suite",
  //     "description": "A spacious suite with a king-size bed, a private balcony, and a living area. Features high-end amenities, a large flat-screen TV, and a minibar.",
  //     "accommodates": 2,
  //     "breakfast_included": true,
  //     "price_and_taxes": {
  //       "base_price": 250,
  //       "taxes": 25,
  //       "total_price": 275
  //     },
  //     "rooms_selected": 5,
  //     "url": "img2.png"
  //   },
  //   {
  //     "room_type": "Standard Double Room",
  //     "description": "A comfortable room with a queen-size bed, a work desk, and modern amenities. Ideal for business travelers or short stays.",
  //     "accommodates": 2,
  //     "breakfast_included": true,
  //     "price_and_taxes": {
  //       "base_price": 120,
  //       "taxes": 12,
  //       "total_price": 132
  //     },
  //     "rooms_selected": 10,
  //     "url": "img3.png"
  //   },
  //   {
  //     "room_type": "Family Suite",
  //     "description": "A large suite with two bedrooms, one with a king-size bed and the other with twin beds. Includes a kitchenette, dining area, and a spacious bathroom.",
  //     "accommodates": 4,
  //     "breakfast_included": false,
  //     "price_and_taxes": {
  //       "base_price": 300,
  //       "taxes": 30,
  //       "total_price": 330
  //     },
  //     "rooms_selected": 3,
  //     "url": "img4.png"
  //   }
  // ];

  taxes: any = 0;
  total: number = 0;

  checkInDate: any;
  checkOutDate: any;
  guests: any;
  location: any;

  hotelId: any;

  constructor (private roomSelectionService: RoomSelectionService, 
    private router: Router, 
    private bookingService: BookingService, 
    private actRoute: ActivatedRoute) { 

      actRoute.paramMap.subscribe(p => {
        this.hotelId = p.get('id');
      })
    }

  ngOnInit(): void {
    this.roomSelectionService.selectedRooms.subscribe(r => {
      this.rooms = r;
      this.total = 0;
      this.rooms.forEach(r => {
        this.total += (r.room.price);
      });
      console.log(this.rooms);
    });


    this.actRoute.queryParams.subscribe(p => {
      this.location = p['location'];
      this.checkInDate = p['checkInDate'];
      this.checkOutDate = p['checkOutDate'];
      this.guests = p['guests'];
    })
  }

  //  [routerLink]="['/hotel/reservation/confirm']"

  onBook() {
    if (localStorage.getItem('token')) {
      console.log(localStorage.getItem('token'));
      this.bookingService.createBooking({
        amount : this.total,
        bookingDate : new Date().toISOString().split('T')[0],
        startDate : this.checkInDate,
        endDate: this.checkOutDate,
        hotel : {
          id: this.hotelId
        }
      }).subscribe({
        next : (data) => {

          this.rooms.forEach(r => {
            this.bookingService.roomHasBooking(data.id, r.room.id).subscribe({
              next : (data) => {
                console.log(data);
              },
              error : (err) => {
                console.log(err);
              }
            })
          })

          this.router.navigateByUrl("/hotel/reservation/confirm?location="+this.location+"&checkInDate="+this.checkInDate+"&checkOutDate="+this.checkOutDate+"&guests="+this.guests+"&bid="+data.id);
        },
        error : (err) => {
          console.log(err);
        }
      });
    }
    else
      this.router.navigateByUrl('login');
  }
}
