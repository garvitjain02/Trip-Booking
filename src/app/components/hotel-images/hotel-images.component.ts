import { NgClass, NgFor, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { HotelImageService } from '../../service/hotelImage.service';

@Component({
  selector: 'app-hotel-images',
  imports: [NgFor],
  templateUrl: './hotel-images.component.html',
  styleUrl: './hotel-images.component.css'
})
export class HotelImagesComponent {
  images: any[] = [];
  id: any;

  constructor (private hotelImageService: HotelImageService, private actRoute: ActivatedRoute) { 
    // this.images = [
    //   {
    //     "url" : "images/1.jpeg"
    //   },
    //   {
    //     "url" : "images/2.jpg"
    //   },
    //   {
    //     "url" : "images/3.jpg"
    //   },
    // ]

    actRoute.paramMap.subscribe( p => {
      this.id = p.get('id');
    });


    hotelImageService.getHotelImages(this.id).subscribe({
      next : (data) => {
        this.images = data;
      },
      error : (err) => {
        console.log(err);
      }
    })
  }
}
