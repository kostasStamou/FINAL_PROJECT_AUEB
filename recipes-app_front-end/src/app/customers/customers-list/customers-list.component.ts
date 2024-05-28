import { Component } from '@angular/core';
import { Customer } from 'src/app/app.interfaces';
import { CustomerService } from '../customers.service';

@Component({
  selector: 'app-customers-list',
  templateUrl: './customers-list.component.html',
  styleUrls: ['./customers-list.component.css']
})
export class CustomersListComponent {
  customers: Customer[] = [];

  constructor(private customerService: CustomerService) { }

  ngOnInit(): void {
    this.getCustomers();
  }

  getCustomers(): void {
    this.customerService.seeCustomers()
      .subscribe(customers => this.customers = customers);
  }
}
