import { Component } from '@angular/core';
import { NavbarComponent } from "../../components/navbar/navbar.component";
import { NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { FooterComponent } from "../../components/footer/footer.component";
import { GuestService } from '../../service/guest.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-guest-page',
  imports: [NavbarComponent, NgFor, FormsModule, FooterComponent, NgIf],
  templateUrl: './add-guest-page.component.html',
  styleUrl: './add-guest-page.component.css'
})
export class AddGuestPageComponent {
  numberOfGuests: number = 0;
  guests: {name: string, age: any, idProof: any}[] = [];
  msg: string | undefined;
  bid: any;
  successMsg: string | undefined;

  constructor(private guestService: GuestService, private actRoute: ActivatedRoute) {
    actRoute.queryParams.subscribe(p => {
      this.bid = p['bid'];
      this.numberOfGuests = p['guests'];
      this.guests = Array.from({ length: this.numberOfGuests }, () => ({
        name : '',
        age : null,
        idProof : null
      }));
    })
  }

  onFileSelect(event: any, i: any) {
    this.guests[i].idProof = event.target.files[0];
  }

  onSubmit() {
    this.guests.forEach(g => {
      if (g.name == '')
        this.msg = "Name of Guests cannot be Null";
      else if (g.age == null || g.age < 0)
        this.msg = "Enter Valid Age of All Guests";
      else if (g.idProof == null)
        this.msg = "Enter Valid ID of All Guests";
      else {
        this.guestService.addGuest({
          name: g.name,
          age : g.age
        }).subscribe({
          next : (data) => {
            let addedGuest = data;
            console.log(addedGuest);
            let formData = new FormData();
            formData.set('file', g.idProof);
          
            this.guestService.addGuestID(formData, addedGuest.id).subscribe({
              next : (resp) => {
                console.log(resp);
              },
              error : (err) => {
                console.log(err);
              }
            });

            this.guestService.bookingHasGuest(this.bid, addedGuest.id).subscribe({
              next : (data) => {
                console.log(data);
              },
              error : (err) => {
                console.log(err);
              }
            });

            this.successMsg = "Booking Successful";
          },
          error : (err) => {
            console.log(err);
          }
        })
      }

    });




    // let formData = new FormData();
    // formData.set('file', this.guests.file);

  }
}
