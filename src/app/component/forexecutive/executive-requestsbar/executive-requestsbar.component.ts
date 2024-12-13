import { NgClass } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-executive-requestsbar',
  imports: [NgClass, RouterLink],
  templateUrl: './executive-requestsbar.component.html',
  styleUrl: './executive-requestsbar.component.css'
})
export class ExecutiveRequestsbarComponent {

  linkType: string='hotel';
   
  linkClick(linkType:string){
    this.linkType = linkType;
  }

  getClassHotel(){
    return {
      active: this.linkType === 'hotel'?true: false
    }
  }

  getClassFlight(){
    return {
      active: this.linkType === 'flight'?true: false
    }
  }

  getClassVendor(){
    return {
      active: this.linkType === 'vendor'?true: false
    }
  }

}
