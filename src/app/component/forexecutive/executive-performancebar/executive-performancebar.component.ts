import { NgClass } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-executive-performancebar',
  imports: [RouterLink, NgClass],
  templateUrl: './executive-performancebar.component.html',
  styleUrl: './executive-performancebar.component.css'
})
export class ExecutivePerformancebarComponent {

  linkType: string='customer';
   
  linkClick(linkType:string){
    this.linkType = linkType;
  }

  getClassCustomer(){
    return {
      active: this.linkType === 'customer'?true: false
    }
  }

  getClassEntity(){
    return {
      active: this.linkType === 'entity'?true: false
    }
  }

  getClassVendor(){
    return {
      active: this.linkType === 'vendor'?true: false
    }
  }

}
