import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-vendor-navbar',
  imports: [RouterLink],
  templateUrl: './vendor-navbar.component.html',
  styleUrl: './vendor-navbar.component.css'
})
export class VendorNavbarComponent {


  constructor (private router: Router) { }

  onLogout() {
    localStorage.removeItem('token');
    this.router.navigateByUrl('/login');
  }
}
