import { Component } from '@angular/core';
import { UsersService } from '../../../service/users.service';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-viewcertificate',
  imports: [NgIf],
  templateUrl: './viewcertificate.component.html',
  styleUrl: './viewcertificate.component.css'
})
export class ViewcertificateComponent {

  path:any;

  goBack() {
    window.history.back(); // Navigate to the previous page
  }

  constructor(private usersService: UsersService){
    usersService.viewCertificate(localStorage.getItem('vendorIdForCertificateView')).subscribe({
      next:(data)=>{
        console.log(data);
        this.path='./images/'+ data.fileName;
      },
      error:()=>{}
    })
  }

}
