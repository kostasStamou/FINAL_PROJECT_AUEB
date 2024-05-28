import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private email: BehaviorSubject<string> = new BehaviorSubject<string>('');
  private role: BehaviorSubject<string> = new BehaviorSubject<string>('');
  
  setEmail(email: string): void {
    this.email.next(email);
  }

  setRole(role: string): void {
    this.role.next(role);
  }

  getEmail(): Observable<string> {
    return this.email.asObservable();
  }

  getRole(): Observable<string> {
    return this.role.asObservable();
  }
}
