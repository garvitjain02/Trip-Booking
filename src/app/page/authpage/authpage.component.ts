import { Component } from '@angular/core';
import { LoginHeaderComponent } from '../../component/login-header/login-header.component';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-authpage',
  imports: [LoginHeaderComponent, RouterOutlet],
  templateUrl: './authpage.component.html',
  styleUrl: './authpage.component.css'
})
export class AuthpageComponent {

}
