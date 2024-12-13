import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
// import { NavbarComponent } from "./components/navbar/navbar.component";
// import { LoginComponent } from "./components/login/login.component";
// import { LoginPageComponent } from "./pages/login-page/login-page.component";
// import { LandingPageComponent } from "./pages/landing-page/landing-page.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'hotel-booking';
}
