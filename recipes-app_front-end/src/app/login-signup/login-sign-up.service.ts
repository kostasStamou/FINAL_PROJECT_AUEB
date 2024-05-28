import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {  Customer, UserCredentials } from '../app.interfaces';

@Injectable()
export class LoginService {
  private loginApiUrl = 'http://localhost:8080/api/login'; // Αντικαταστήστε το με τον πραγματικό URL του REST API
  private loginApiUrl2 = 'http://localhost:8080/api/customers';

  constructor(private http: HttpClient) {}

  login(customer: UserCredentials): Observable<any> {
    return this.http.post(this.loginApiUrl, customer);
  }

  signup(customer: Customer): Observable<any> {
    return this.http.post(this.loginApiUrl2, customer);
  }

  
}