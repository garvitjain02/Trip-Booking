import { NgFor } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UsersService } from '../../../service/users.service';

@Component({
  selector: 'app-customer-performance',
  imports: [NgFor],
  templateUrl: './customer-performance.component.html',
  styleUrl: './customer-performance.component.css'
})
export class CustomerPerformanceComponent {

  users:any[]=[];

  username:any;

  filteredUsers : any[]=[];

  constructor(private router: Router, private usersService: UsersService){
    this.usersService.getAllCustomers().subscribe({
      next:(data)=>{
        console.log(data);
        this.users= data; 
        this.filteredUsers = this.users;
      },
      error:()=>{}
    })
  }
  
  // This will hold the filtered users based on search input

  // Handle search functionality
  onSearch(event: any): void {
    const searchTerm = event.target.value.toLowerCase();
    this.filteredUsers = this.users.filter(user => 
      user.username.toLowerCase().includes(searchTerm)
    );
  }

  navigateToDetailedPage(username:any):void{
    localStorage.setItem('customerUsername',username);
    console.log(localStorage.getItem('customerUsername'));
    this.router.navigate(['/executive/performance/detailedc']);
  }

  

}
