import { NgFor, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RoomService } from '../../service/room.service';
import { RoomSelectionService } from '../../service/room-selection.service';

@Component({
  selector: 'app-room-box',
  imports: [NgFor, NgIf],
  templateUrl: './room-box.component.html',
  styleUrl: './room-box.component.css'
})
export class RoomBoxComponent {
  // rooms:any[] = [
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
  //     "rooms_available": 5,
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
  //     "rooms_available": 10,
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
  //     "rooms_available": 3,
  //     "url": "img4.png"
  //   }
  // ];
  id: any;
  rooms: any[] = [];

  constructor(private actRoute: ActivatedRoute, private roomService: RoomService, private roomSelectionService: RoomSelectionService) {
    
    actRoute.paramMap.subscribe( p => {
      this.id = p.get('id');
    });

    roomService.getRoomsByHotel(this.id).subscribe({
      next : (data) => {
        this.rooms = data;
        console.log(this.rooms);
      },
      error : (err) => {
        console.log(err);
      }
    })
  }

  addRoom(room: any) {
    this.roomSelectionService.addRoomToSelection(room);
  }
}
