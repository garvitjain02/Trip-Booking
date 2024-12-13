import { Component } from '@angular/core';
import { VendorNavbarComponent } from "../../components/vendor-navbar/vendor-navbar.component";
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HotelService } from '../../service/hotel.service';
import { ActivatedRoute } from '@angular/router';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-hotel-edit',
  imports: [VendorNavbarComponent, ReactiveFormsModule, NgIf],
  templateUrl: './hotel-edit.component.html',
  styleUrl: './hotel-edit.component.css'
})
export class HotelEditComponent {
  hotelEditForm: FormGroup;
  hotel: any;
  hotelId: any;
  successMsg: string | undefined;
  errorMsg : string | undefined;

  constructor(private hotelService: HotelService, private actRoute: ActivatedRoute) {
    this.hotelEditForm = new FormGroup({
      name : new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required]),
      rooms: new FormControl(0, [Validators.required]),
      stars: new FormControl(0, [Validators.required]),
      address : new FormControl('', [Validators.required])
    })
    
    actRoute.paramMap.subscribe(p => {
      this.hotelId = p.get('id');

      hotelService.getHotelByHotelId(this.hotelId).subscribe({
        next : (data) => {
          this.hotel = data;
          console.log(data);

          this.hotelEditForm.setValue({
            name : this.hotel.name,
            description : this.hotel.description,
            address : this.hotel.address,
            rooms : this.hotel.rooms,
            stars : this.hotel.stars
          });
        },
        error: (err) => {
          console.log(err);
        }
      });
    })

    

  }

  onSubmit() {
    if (!this.hotelEditForm.valid) {
      this.successMsg = undefined;
      this.errorMsg = "Invalid Form Fields!";
    }
    else {
      this.hotel.name = this.hotelEditForm.value.name;
      this.hotel.description = this.hotelEditForm.value.description;
      this.hotel.rooms = this.hotelEditForm.value.rooms;
      this.hotel.stars = this.hotelEditForm.value.stars;
      this.hotel.address = this.hotelEditForm.value.address;

      console.log(this.hotel);
      this.hotelService.updateHotel({
        id: this.hotel.id,
        name: this.hotelEditForm.value.name,
        description: this.hotelEditForm.value.description,
        rooms: this.hotelEditForm.value.rooms,
        stars: this.hotelEditForm.value.stars,
        address: this.hotelEditForm.value.address
      }).subscribe({
        next : (data) => {
          this.hotel = data;
          console.log(this.hotel);
          this.errorMsg = undefined;
          this.successMsg = "Hotel Details Updated Successfully";
        },
        error : (err) => {
          console.log(err);
        }
      })
    }
  }
}
