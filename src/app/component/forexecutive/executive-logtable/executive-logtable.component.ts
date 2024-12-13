import { NgFor } from '@angular/common';
import { Component } from '@angular/core';
import { UsersService } from '../../../service/users.service';

@Component({
  imports: [NgFor],
  selector: 'app-executive-logtable',
  templateUrl: './executive-logtable.component.html',
  styleUrls: ['./executive-logtable.component.css']
})
export class ExecutiveLogtableComponent {
  logs: any[] = [];
  filteredLogs:any[]=[]
  page:number=0;
  size:number=14;
  totalElements: number = 0;
  pageArray:any[] =[];
  data:any[] = []

  prev(){
    if(this.page >0){
     this.page = this.page - 1 
     this.getData() 
   }

  }

  next(){
   this.page = this.page + 1 
   this.getData()

  }

  onClick(i:number){
     this.page = i
     this.getData()
  }

  getData(){
    this.usersService.getPagedLogs(this.page,this.size).subscribe({
      next:(resp)=>{
        console.log(resp)
        this.data = resp.content; 
        this.totalElements = resp.totalElements;
        let totalPages = this.totalElements / this.size;
        let i=1; 
        this.pageArray = [];
        while(totalPages > 0){
          this.pageArray.push(i)
          totalPages = totalPages - 1;
          i++;
        }
        console.log(this.pageArray)
      },
      error:()=>{}
    })
  }

  constructor(private usersService: UsersService){
    this.getData()
  }

  // This will hold the filtered log data
/*
  // Selected start and end date for the date range filter
  startDate: string | null = null;
  endDate: string | null = null;

  // Method to handle search functionality
  onSearch(event: any): void {
    this.filterLogs();
  }

  // Method to handle date range change
  onDateChange(event: any): void {
    const startDate = (event.target.id === 'startDate') ? event.target.value : this.startDate;
    const endDate = (event.target.id === 'endDate') ? event.target.value : this.endDate;

    if (event.target.id === 'startDate') {
      this.startDate = startDate;
    } else {
      this.endDate = endDate;
    }

    this.filterLogs();
  }

  filterLogs(): void {
    const searchTerm = (document.getElementById('search') as HTMLInputElement)?.value.toLowerCase() || '';
  
    this.filteredLogs = this.logs.filter((log) => {
      // Ensure each field has a value or fallback to an empty string
      const username = log.user?.username?.toLowerCase() || '';
      const activityType = log.activityType?.toLowerCase() || '';
      const activityDesc = log.activityDesc?.toLowerCase() || '';
      const timestamp = log.timestamp?.toLowerCase() || '';
      const flightNumber = log.flight?.flightNumber?.toLowerCase() || '';
      const hotelName = log.hotel?.hotelName?.toLowerCase() || '';
  
      // Search filter
      const matchesSearch =
        username.includes(searchTerm) ||
        activityType.includes(searchTerm) ||
        activityDesc.includes(searchTerm) ||
        timestamp.includes(searchTerm) ||
        flightNumber.includes(searchTerm) ||
        hotelName.includes(searchTerm);
  
      // Date range filter
      const logDate = new Date(log.timestamp).getTime();
      const startDate = this.startDate ? new Date(this.startDate).getTime() : null;
      const endDate = this.endDate ? new Date(this.endDate).getTime() : null;
      const matchesDate =
        (!startDate || logDate >= startDate) &&
        (!endDate || logDate <= endDate);
  
      return matchesSearch && matchesDate;
    });
  }*/
  
}
