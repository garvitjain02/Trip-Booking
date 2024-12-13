import { Component } from '@angular/core';
import { ExecutivePerformancebarComponent } from "../executive-performancebar/executive-performancebar.component";
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-executive-performance',
  imports: [ExecutivePerformancebarComponent, RouterOutlet],
  templateUrl: './executive-performance.component.html',
  styleUrl: './executive-performance.component.css'
})
export class ExecutivePerformanceComponent {

}
