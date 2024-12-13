import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../service/auth.service';
import { NgFor, NgIf } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  imports:[NgIf,ReactiveFormsModule],
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  signupForm: FormGroup;
  userType: string = 'CUSTOMER'; // Default user type
  successMsg: string | undefined;
  errorMsg: string | undefined;

  constructor(private fb: FormBuilder, private authService: AuthService, private router:Router) {
    this.signupForm = this.fb.group({
      name: ['', Validators.required],
      lastName: ['', Validators.required],
      dob: ['', Validators.required],
      phone: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      email: ['', [Validators.required, Validators.email,     
        Validators.pattern('^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$') // Ensures email has @ and valid format
              ]],
      username: ['', Validators.required],
      password: ['', Validators.required ],
      role: ['CUSTOMER', Validators.required] // Default role value
    });
  }

  setUserType(type: string) {
    this.userType = type;
    this.signupForm.get('role')?.setValue(type === 'CUSTOMER' ? 'CUSTOMER' : ''); // Update the role field
  }

  onSignUp() {
    if (this.signupForm.invalid) {
      this.errorMsg = 'Please fill out all required fields correctly.';
      this.successMsg = undefined;
      return;
    }

    const formValues = this.signupForm.value;
    this.authService.signUp(formValues).subscribe({
      next: (data) => {
        console.log(data)
        if(formValues.role === 'HOTEL_VENDOR' || formValues.role === 'FLIGHT_VENDOR'){
          this.certificateUpload(data.id);
        }
        this.successMsg = 'Sign Up Success, Please login';
        this.errorMsg = undefined;
        this.resetForm();
      },
      error: (err) => {
        console.error(err);
        this.errorMsg = err.msg || 'Sign Up failed. Please try again.';
        this.successMsg = undefined;
      }
    });
  }

  certificateUpload(id:any){
    localStorage.setItem('idForCertificate', id)
    console.log('id i am sending:'+id)
    this.router.navigateByUrl("/certificate")
  }

  resetForm() {
    this.signupForm.reset({
      role: 'CUSTOMER', // Reset role to default
    });
  }
}
