import { Component } from '@angular/core';
import { NavbarComponent } from "../../components/navbar/navbar.component";
import { SidebarComponent } from "../../components/sidebar/sidebar.component";
import { HotelBoxComponent } from "../../components/hotel-box/hotel-box.component";

@Component({
  selector: 'app-hotel-search-page',
  imports: [NavbarComponent, SidebarComponent, HotelBoxComponent],
  templateUrl: './hotel-search-page.component.html',
  styleUrl: './hotel-search-page.component.css'
})
export class HotelSearchPageComponent {

}
