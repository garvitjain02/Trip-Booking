import { Component } from '@angular/core';
import { RouterOutlet, Router } from '@angular/router';
import { ExecutiveSidebarComponent } from '../../component/forexecutive/executive-sidebar/executive-sidebar.component';
import { ExecutiveNavbarComponent } from "../../component/forexecutive/executive-navbar/executive-navbar.component";

@Component({
  selector: 'app-executive',
  imports: [RouterOutlet, ExecutiveSidebarComponent, ExecutiveNavbarComponent],
  templateUrl: './executive.component.html',
  styleUrl: './executive.component.css'
})
export class ExecutiveComponent {

constructor(private router:Router){
  if (localStorage.getItem('token') === null) {
    this.router.navigate(['/back'])
  }
}

}
