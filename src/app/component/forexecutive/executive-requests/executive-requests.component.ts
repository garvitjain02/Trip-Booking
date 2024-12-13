import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { ExecutivePerformancebarComponent } from '../executive-performancebar/executive-performancebar.component';
import { ExecutiveRequestsbarComponent } from "../executive-requestsbar/executive-requestsbar.component";

@Component({
  selector: 'app-executive-requests',
  imports: [ExecutiveRequestsbarComponent, RouterOutlet],
  templateUrl: './executive-requests.component.html',
  styleUrl: './executive-requests.component.css'
})
export class ExecutiveRequestsComponent {

}
