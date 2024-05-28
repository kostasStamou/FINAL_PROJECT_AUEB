import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService } from '../login-sign-up.service';
import { UserCredentials } from 'src/app/app.interfaces';
import { UserService } from 'src/app/user-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  customer!: UserCredentials;
  
  constructor(private formBuilder: FormBuilder, private loginService: LoginService, private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.buildLoginForm();
  }

  buildLoginForm(): void {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.loginForm.invalid) {
      return;
    }
  
    this.customer = { 
      email: this.loginForm.value.email,
      password: this.loginForm.value.password
    };
  
    this.loginService.login(this.customer).subscribe(
      (response) => {
        if (response.ROLE == "NONE") {
          alert('WRONG CREDENTIALS');
        } else {
          this.userService.setEmail(this.customer.email);
          this.userService.setRole(response.ROLE);
          this.router.navigate(['/post']);
        }
      },
      (error) => {
        alert('Σφάλμα: ' + error.message);
        console.error(error);
      }
    );
  }
}

