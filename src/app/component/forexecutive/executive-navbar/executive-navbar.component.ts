import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-executive-navbar',
  imports: [],
  templateUrl: './executive-navbar.component.html',
  styleUrl: './executive-navbar.component.css'
})
export class ExecutiveNavbarComponent {

  name: any; 
  
  constructor(private router: Router){
    this.name = localStorage.getItem('name');
  }
  logout(){
      localStorage.clear();
      console.log("after logout"+localStorage.getItem('token'))
      this.router.navigateByUrl('login?msg=you have successfully logged out')
  }
  
}
