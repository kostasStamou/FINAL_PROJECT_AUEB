import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { CustomerService } from '../customers.service';
import { UserService } from 'src/app/user-service.service';


@Component({
  selector: 'app-update-customer',
  templateUrl: './update-customer.component.html',
  styleUrls: ['./update-customer.component.css']
})
export class UpdateCustomerComponent {
  email!: string;
  customerForm!: FormGroup;

  constructor(private customerService: CustomerService, private userService: UserService,private formBuilder: FormBuilder) {
    this.userService.getEmail().subscribe((email: string) => {
      this.email = email;
    });

    this.customerForm = this.formBuilder.group({
      firstname: ['', Validators.required ],
      lastname: ['', Validators.required],
      password: ['', [Validators.required]]
    });
  }
  
  get formControls() {
    return this.customerForm.controls;
  }

  updateCustomer() {
    if (this.customerForm.invalid) {
      
      return;
    }

    const customer = {
      customerId: 0,
      firstname: this.customerForm.value.firstname,
      lastname: this.customerForm.value.lastname,
      email: 'thisemail@aueb.gr',
      password: this.customerForm.value.password
    };

    this.customerService.updateCustomer(this.email, customer)
      .subscribe(
        response => {
          console.log('Η ενημέρωση πελάτη ήταν επιτυχής:', response);
          alert('Η ενημέρωση πελάτη ήταν επιτυχής')
        },
        error => {
          console.error('Σφάλμα κατά την ενημέρωση πελάτη:', error);
          alert('Σφάλμα κατά την ενημέρωση πελάτη')
        }
      );
  }
}
