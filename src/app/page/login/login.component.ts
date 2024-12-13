import { NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../service/auth.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [FormsModule, NgIf],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username: string = "";
  password: string = "";
  successMsg: string | undefined;
  errorMsg: string | undefined;
  msg: string | undefined;

  constructor(
    private authService: AuthService,
    private router: Router,
    private actRoute: ActivatedRoute
  ) {
    this.actRoute.queryParams.subscribe(p => {
      this.msg = p['msg'];
    });
  }

  onLogin() {
    this.authService.login({
      username: this.username,
      password: this.password
    }).subscribe({
      next: (data) => {
        let token = data.token;
        // Call the API to get user details using the token
        this.authService.getUserDetails(token).subscribe({
          next: (userDetails) => {
            localStorage.setItem('token', token);
            localStorage.setItem('username', userDetails.username);
            localStorage.setItem('name', userDetails.name);
            let role = userDetails.role;
            let vendorApproved = userDetails.vendor_approved;

            // Check role and vendor approval status
            if ((role === 'HOTEL_VENDOR' || role === 'FLIGHT_VENDOR') && vendorApproved==="FALSE") {
              this.errorMsg = "Cannot login. Please wait for approval.";
              return; // Stop execution if not approved
            }
            
            // Navigate based on role
            switch (role) {
              case 'CUSTOMER':
                this.successMsg = "Logged In.";
                break;
              case 'EXECUTIVE':
                this.router.navigateByUrl("/executive");
                break;
              case 'HOTEL_VENDOR':
              case 'FLIGHT_VENDOR':
                this.successMsg = "Logged In.";
                break;
              default:
                this.router.navigateByUrl("/broken-link");
                break;
            }
          },
          error: (err) => {
            this.errorMsg = err.error.msg;
          }
        });
      },
      error: (err) => {
        this.errorMsg = err.error.msg;
      }
    });
  }
}
