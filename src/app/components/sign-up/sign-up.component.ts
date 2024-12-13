import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../service/auth.service';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-sign-up',
  imports: [RouterLink, FormsModule, NgIf],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css'
})
export class SignUpComponent {
  
  firstName: string = "";
  lastName: string = "";
  username: string = "";
  password: string = "";
  successMsg: string = "";
  errorMsg: string = "";
  
  constructor (private authService: AuthService) {

  }

  onSignUp () {
    this.authService.signUp({
      firstName : this.firstName,
      lastName: this.lastName,
      username : this.username,
      password : this.password,
      role : "CUSTOMER"
    }).subscribe({
      next : (data) => {
        this.successMsg = "SignUp Success, Please Login"

      },
      error : (err) => {
        this.errorMsg = err.error.message;
      }
    })
  }
}
