import { Component } from '@angular/core';
import { SignUpComponent } from "../../components/sign-up/sign-up.component";
import { NavbarComponent } from "../../components/navbar/navbar.component";

@Component({
  selector: 'app-sign-up-page',
  imports: [SignUpComponent, NavbarComponent],
  templateUrl: './sign-up-page.component.html',
  styleUrl: './sign-up-page.component.css'
})
export class SignUpPageComponent {

}
