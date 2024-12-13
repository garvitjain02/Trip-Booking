import { Component } from '@angular/core';
import { VendorNavbarComponent } from "../../components/vendor-navbar/vendor-navbar.component";
import { VendorHotelsComponent } from "../../components/vendor-hotels/vendor-hotels.component";

@Component({
  selector: 'app-vendor-dashboard-page',
  imports: [VendorNavbarComponent, VendorHotelsComponent],
  templateUrl: './vendor-dashboard-page.component.html',
  styleUrl: './vendor-dashboard-page.component.css'
})
export class VendorDashboardPageComponent {

}
