import { Component } from '@angular/core';
import { VendorNavbarComponent } from "../../components/vendor-navbar/vendor-navbar.component";
import { BookingRequestsTableComponent } from "../../components/booking-requests-table/booking-requests-table.component";

@Component({
  selector: 'app-booking-requests-page',
  imports: [VendorNavbarComponent, BookingRequestsTableComponent],
  templateUrl: './booking-requests-page.component.html',
  styleUrl: './booking-requests-page.component.css'
})
export class BookingRequestsPageComponent {

}
