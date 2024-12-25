import { Component } from '@angular/core';
import { NavbarComponent } from "../../components/navbar/navbar.component";
import { NgIf } from '@angular/common';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProfileService } from '../../service/profile.service';
import { Router } from '@angular/router';
import { first } from 'rxjs';

@Component({
  selector: 'app-edit-profile-page',
  imports: [NavbarComponent, NgIf, ReactiveFormsModule],
  templateUrl: './edit-profile-page.component.html',
  styleUrl: './edit-profile-page.component.css'
})
export class EditProfilePageComponent {
  successMsg: String | undefined;
  errorMsg: String | undefined;
  profileForm : FormGroup;
  id: any;

  constructor(private profileService: ProfileService, private route: Router) {
    this.profileForm = new FormGroup({
      firstName: new FormControl('', [Validators.required]),
      lastName : new FormControl('', [Validators.required]),
      email : new FormControl('', [Validators.required]),
      phone : new FormControl('', [Validators.required]),
      username : new FormControl('', [Validators.required]),
      dob : new FormControl(new Date(), [Validators.required]),
      password: new FormControl('')
    });

    profileService.getUserDetails().subscribe({
      next : (data) => {
        console.log(data);
        this.id = data.id;
        this.profileForm.setValue({
          firstName : data.firstName,
          lastName : data.lastName,
          phone : data.phone,
          email : data.email,
          username : data.username,
          dob : data.dob.split('T')[0],
          password : ''
        })
        console.log(this.profileForm.value.firstName);
      },
      error : (err) => {
        console.log(err);
      }
    })
  }

  onSubmit() {
    if (this.profileForm.valid) {
      console.log(this.profileForm.value.dob);
      this.profileService.updateUser({
        id: this.id,
        firstName: this.profileForm.value.firstName,
        lastName: this.profileForm.value.lastName,
        email: this.profileForm.value.email,
        phone: this.profileForm.value.phone,
        username: this.profileForm.value.username,
        dob: new Date(this.profileForm.value.dob + "T00:00:00"),
        password: this.profileForm.value.password,
        role: 'CUSTOMER'
      }).subscribe({
        next : (data) => {
          this.errorMsg = undefined;
          this.successMsg = "Updated Successfully";
          this.route.navigateByUrl("/profile");
        },
        error : (err) => {
          this.successMsg = undefined;
          this.errorMsg = "Error Occurred";
          console.log(err);
        }
      })
    } else {
      this.errorMsg = "Enter All Details";
    }
  }
}
