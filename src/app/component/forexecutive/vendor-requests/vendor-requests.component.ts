import { Component } from '@angular/core';
import { RequestService } from '../../../service/request.service';
import { NgFor } from '@angular/common';
import { UsersService } from '../../../service/users.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-vendor-requests',
  imports: [NgFor],
  templateUrl: './vendor-requests.component.html',
  styleUrl: './vendor-requests.component.css'
})
export class VendorRequestsComponent {

  vendorRequests:any[]=[]

  constructor(private requestService: RequestService, private router:Router){
    this.requestService.getVendorRequest().subscribe({
      next:(data)=>{
        this.vendorRequests=data;
      },
      error:()=>{}
    })
  }

  approveVendor(id:any){
    console.log(id)
    //vendorObj.vendor_approved="TRUE";
    this.requestService.postApproveVendors(id).subscribe({
      next:()=>{
        this.vendorRequests = this.vendorRequests.filter(vendor => vendor.id !== id);
      },
      error:()=>{}
  })
  }

  deleteVendor(id:any){
    this.requestService.deleteUserById(id).subscribe({
      next:(data)=>{
        this.vendorRequests = this.vendorRequests.filter(vendor => vendor.id !== id);
      },
      error:()=>{}
    })
  }

  viewCertificate(id:any){
    localStorage.setItem('vendorIdForCertificateView',id)
    this.router.navigate(['/executive/request/viewcertificate'])
  }

}
