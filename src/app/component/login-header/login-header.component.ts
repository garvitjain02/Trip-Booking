import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-login-header',
  imports: [RouterLink],
  templateUrl: './login-header.component.html',
  styleUrl: './login-header.component.css'
})
export class LoginHeaderComponent {

  constructor(private router: Router){}
  navigateToSignUp(){
    this.router.navigateByUrl("/sign-up");
  }
}