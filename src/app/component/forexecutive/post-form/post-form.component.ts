import { NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { AuthService } from '../../../service/auth.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-post-form',
  imports: [NgIf, FormsModule],
  templateUrl: './post-form.component.html',
  styleUrl: './post-form.component.css'
})
export class PostFormComponent {

  name: string = '';
  username: string = '';
  password: string = ''; 
  errorMsg: string | undefined;
  successMsg: string | undefined;
  lastName: string = '';
  dob: string = '';
  phone: string = '';
  email: string = '';
  role: string = 'EXECUTIVE'; // Fixed role for executives

  constructor(private authService: AuthService) {}

  onSignUp() {
    // Validate required fields
    if (!this.name || !this.lastName || !this.phone || !this.email || !this.username || !this.password) {
      this.errorMsg = 'All fields are required.';
      this.successMsg = undefined;
      return;
    }

    // Call the AuthService to perform sign up
    this.authService.signUp({
      name: this.name,
      username: this.username,
      password: this.password,
      lastName: this.lastName,
      dob: this.dob,
      phone: this.phone,
      email: this.email,
      role: this.role // Fixed to 'EXECUTIVE'
    }).subscribe({
      next: () => {
        this.successMsg = 'New Executive has been added';
        this.errorMsg = undefined;
        this.resetForm();
      },
      error: (err) => {
        console.log(err);
        this.errorMsg = err.msg || 'Failed to add new Executive. Please try again.';
        this.successMsg = undefined;
      }
    });
  }

  resetForm() {
    this.name = '';
    this.username = '';
    this.password = '';
    this.lastName = '';
    this.dob = '';
    this.phone = '';
    this.email = '';
  }


}
