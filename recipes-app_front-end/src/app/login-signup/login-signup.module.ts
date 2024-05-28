import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './sign-up/sign-up.component'
import { LoginService } from './login-sign-up.service';

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'signup', component: SignupComponent},
];


@NgModule({
  declarations: [
    LoginComponent,
    SignupComponent
  ],
  imports: [
    CommonModule, RouterModule.forChild(routes),ReactiveFormsModule
  ],
  providers: [LoginService]
})
export class LoginSignupModule { }
