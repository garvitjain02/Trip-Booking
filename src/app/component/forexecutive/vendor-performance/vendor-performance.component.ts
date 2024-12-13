import { NgFor } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UsersService } from '../../../service/users.service';

@Component({
  imports: [NgFor],
  selector: 'app-vendor-performance',
  templateUrl: './vendor-performance.component.html',
  styleUrls: ['./vendor-performance.component.css']
})
export class VendorPerformanceComponent {

  vendors:any[]=[];
  filteredVendors:any[]=[];

  constructor(private router: Router, private usersService: UsersService){
    usersService.getAllVendors().subscribe({
      next:(data)=>{
        this.vendors=data;
        this.filteredVendors = this.vendors.slice();
      },
      error:()=>{}
    })
  }

  // Method to handle the search functionality
  onSearch(event: any): void {
    const searchTerm = event.target.value.toLowerCase();
    this.filteredVendors = this.vendors.filter(vendor =>
      vendor.name.toLowerCase().includes(searchTerm)
    );
  }

  // Method to show detailed view when button is clicked
  showDetailedView(vendor: any): void {
    alert(`Showing detailed view for ${vendor.name}`);
    // Logic to show detailed view, maybe navigate to a new page or open a modal
  }

  navigateToDetailedPage(username:any, role:any){
    localStorage.setItem('vendorUsername', username);
    localStorage.setItem('role', role);
    this.router.navigate(['/executive/performance/detailedv']);
  }
}
