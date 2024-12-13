import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../service/auth.service';
import { NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [RouterLink, NgIf, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username: string = "";
  password: string = "";
  successMsg: string = "";
  errorMsg: string = "";

  constructor (private authService: AuthService, private router: Router) { }

  onLogin() {
    this.authService.login({
      username: this.username,
      password: this.password
    }).subscribe({
      next : (data) => {
        let token = data.token;

        this.authService.getUserByUsername(token).subscribe({
          next : (resp) => {
            localStorage.setItem("token", token);
            localStorage.setItem("firstName", resp.firstName);
            localStorage.setItem("username", resp.username);
            localStorage.setItem("role", resp.role);
            console.log(resp);

            this.errorMsg = "";
            this.successMsg = "Login Successful";
            if (resp.role == 'CUSTOMER')
              this.router.navigateByUrl("hotel");
            else if (resp.role == "HOTEL_VENDOR")
              this.router.navigateByUrl("vendor");
          },
          error : (err) => {
            this.successMsg = "";
            this.errorMsg = err.error.message;
          }
        });

      },
      error : (err) => {
        this.successMsg = "";
        this.errorMsg = err.error.message;
      }
    });
  }
}
