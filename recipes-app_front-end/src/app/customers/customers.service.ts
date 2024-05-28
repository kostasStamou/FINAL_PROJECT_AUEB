import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer, Recipe } from '../app.interfaces';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private apiUrl = 'http://localhost:8080/api/customers'; 
  private apiUrl2 = 'http://localhost:8080/api/customer'; 

  constructor(private http: HttpClient) { }

  seeCustomers(): Observable<any> {
    const url = `${this.apiUrl}`;
    return  this.http.get(url);
  }

  updateCustomer(email: string, customer: Customer): Observable<any> {
    const url = `${this.apiUrl}/${email}`;
    return this.http.put(url, customer);
  }

  deleteCustomerByEmail(email: string): Observable<any> {
    const url = `${this.apiUrl}/${email}`;
    return this.http.delete(url);
  }

  seeFavorites(email:string):Observable<any> {
    const url = `${this.apiUrl2}/${email}/favorites`;
    return this.http.get(url);
  }

  deleteFavorite(email: string, recipeId: Number): Observable<any> {
    const url = `${this.apiUrl2}/${email}/favorites/${recipeId}`;
    return this.http.delete(url);
  }
}
