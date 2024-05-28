import { Component } from '@angular/core';
import { CustomerService } from '../customers.service';
import { UserService } from 'src/app/user-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-delete-customer',
  templateUrl: './delete-customer.component.html',
  styleUrls: ['./delete-customer.component.css']
})
export class DeleteCustomerComponent {
  email!: string;

  constructor(private customerService: CustomerService, private userService: UserService,private router: Router) {
    this.userService.getEmail().subscribe((email: string) => {
      this.email = email;
    });
  }

  deleteCustomer(): void {
    this.customerService.deleteCustomerByEmail(this.email).subscribe(
      () => {
        console.log('Η διαγραφή του πελάτη ήταν επιτυχής');
        alert('Η διαγραφή του πελάτη ήταν επιτυχής');
        this.EXIT();
      },
      error => {
        console.error('Σφάλμα κατά τη διαγραφή του πελάτη:', error);
        alert('Σφάλμα κατά τη διαγραφή του πελάτη');
      }
    );
  }

  EXIT(): void {
    this.userService.setEmail('');
    this.userService.setRole('');
    this.router.navigate(['']);
  }

}
