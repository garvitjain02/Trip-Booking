import { Component } from '@angular/core';
import { UsersService } from '../../service/users.service';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-certificate',
  imports: [NgIf],
  templateUrl: './certificate.component.html',
  styleUrl: './certificate.component.css'
})
export class CertificateComponent {
  file: any;
  id: any;
  loading: boolean = false; // To show loading state
  successMessage: string | null = null; // To show success message

  constructor(private usersService: UsersService) {}

  onFileSelect(evnt: any) {
    const selectedFile = evnt.target.files[0];
    if (selectedFile) {
      console.log('Selected file:', selectedFile);
      this.file = selectedFile;
      this.successMessage = null; // Reset success message on new selection
    } else {
      console.error('No file selected');
    }
  }

  uploadFile() {
    if (!this.file) {
      alert('Please select a file before uploading.');
      return;
    }

    this.id = localStorage.getItem('idForCertificate');
    if (!this.id) {
      console.error('ID not found in localStorage');
      return;
    }
    console.log('ID obtained:', this.id);

    let formData = new FormData();
    formData.set('file', this.file);
    
    this.loading = true; // Set loading to true while uploading
    this.usersService.uploadImage(formData, this.id).subscribe({
      next: (data) => {
        console.log('File upload successful');
        this.loading = false;
        this.successMessage = 'Certificate uploaded successfully. Please wait for your vendor account to be approved.';
      },
      error: (error) => {
        console.error('Error uploading file:', error);
        this.loading = false;
        alert('Error uploading file. Please try again.');
      }
    });
  }

}
