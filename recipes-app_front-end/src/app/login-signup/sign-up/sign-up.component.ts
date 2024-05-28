import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService } from '../login-sign-up.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignupComponent implements OnInit {
  signupForm!: FormGroup ;

  constructor(private formBuilder: FormBuilder,private loginService: LoginService) { }

  ngOnInit(): void {
    this.signupForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit() {
    if (this.signupForm.invalid) {
      
      return;
    }

    const customer = {
      customerId: 0,
      firstname: this.signupForm.value.firstName,
      lastname: this.signupForm.value.lastName,
      email: this.signupForm.value.email,
      password: this.signupForm.value.password
    };

    this.loginService.signup(customer)
      .subscribe(
        response => {
          console.log('Η καταχώριση πελάτη ήταν επιτυχής:', response, customer);
          alert('Η καταχώριση πελάτη ήταν επιτυχής')
        },
        error => {
          console.error('Σφάλμα κατά την καταχώριση πελάτη:', error, customer);
          alert('email already exists')
        }
      );
  }
  }

 

