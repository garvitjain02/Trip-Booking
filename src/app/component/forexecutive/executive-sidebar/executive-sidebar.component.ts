import { NgClass } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-executive-sidebar',
  imports: [RouterLink, NgClass],
  templateUrl: './executive-sidebar.component.html',
  styleUrl: './executive-sidebar.component.css'
})
export class ExecutiveSidebarComponent {

  linkType: string='logt';
   
  linkClick(linkType:string){
    this.linkType = linkType;
  }

  getClassLog(){
    return {
      active: this.linkType === 'logt'?true: false
    }
  }

  getClassRequest(){
    return {
      active: this.linkType === 'request'?true: false
    }
  }

  getClassPerformance(){
    return {
      active: this.linkType === 'performance'?true: false
    }
  }

  getClassForm(){
    return {
      active: this.linkType === 'formex'?true: false
    }
  }
  
}
