import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from "../../components/navbar/navbar.component";
import { HotelImagesComponent } from "../../components/hotel-images/hotel-images.component";
import { NgFor } from '@angular/common';
import { FooterComponent } from "../../components/footer/footer.component";
import { RoomBoxComponent } from "../../components/room-box/room-box.component";
import { SelectedRoomOverviewComponent } from "../../components/selected-room-overview/selected-room-overview.component";
import { HotelService } from '../../service/hotel.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-hotel-detail-page',
  imports: [NavbarComponent, HotelImagesComponent, FooterComponent, RoomBoxComponent, SelectedRoomOverviewComponent, NgFor],
  templateUrl: './hotel-detail-page.component.html',
  styleUrl: './hotel-detail-page.component.css'
})

export class HotelDetailPageComponent implements OnInit {

  hotel: any;
  id: any;



  constructor (private hotelService: HotelService, private actRoute: ActivatedRoute) {
    actRoute.paramMap.subscribe( p => {
      this.id = p.get('id');
    });
  
    this.hotelService.getHotelById(this.id).subscribe({
      next : (data) => {
        this.hotel = data;
        console.log(this.hotel);
      }, 
      error : (err) => {
        console.log(err);
      }
    });
  }
  ngOnInit(): void {
    
  }
}
