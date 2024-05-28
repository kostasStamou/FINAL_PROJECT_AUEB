import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CustomersListComponent } from './customers-list/customers-list.component';

import { UpdateCustomerComponent } from './update-customer/update-customer.component';
import { DeleteCustomerComponent } from './delete-customer/delete-customer.component';
import { FavoritesComponent } from './favorites/favorites.component'

const routes: Routes = [
  {path: '', component: CustomersListComponent},
 
  {path: 'delete', component: DeleteCustomerComponent},
  {path: 'update', component: UpdateCustomerComponent},
  {path: 'favorites', component: FavoritesComponent}
];

@NgModule({
  declarations: [
    CustomersListComponent,
    
    UpdateCustomerComponent,
    DeleteCustomerComponent,
    FavoritesComponent
  ],
  imports: [
    CommonModule, RouterModule.forChild(routes),FormsModule,
    ReactiveFormsModule
  ]
})
export class CustomersModule { }
