import { Component } from '@angular/core';
import { MenuItem } from './app.interfaces';
import { UserService } from './user-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'MyRecipes';
  email!: string;
  role!: string ;

  customersMenuForCustomer: MenuItem[] = [
    {text:"update your profile", link:"customer/update"},
    {text:"delete your profile", link:"customer/delete"},
    {text:"see your favorites recipes", link:"customer/favorites"},
];
customersMenuForAdmin: MenuItem[] = [
  {text:"list of all customers", link:"customer"},
];
  recipesMenuForCustomer: MenuItem[] = [
    {text:"list of all recipes", link:"recipe"},
  ];
  recipesMenuForAdmin: MenuItem[] = [
    
    {text:"insert a recipe", link:"recipe/create"},
    
    {text:"delete a recipe", link:"recipe/delete"},
  ];
  postsMenuForCustomer: MenuItem[] = [
   
    {text:"create a post", link:"post/create"},
    
  ];
  postsMenuForAdmin: MenuItem[] = [
   
    {text:"create a post", link:"post/create"},
    {text:"delete a post", link:"post/delete"},
  ];
  router: any;
  

  constructor(private userService: UserService) {
    this.userService.getEmail().subscribe((email: string) => {
      this.email = email;
    });

    this.userService.getRole().subscribe((role: string) => {
      this.role = role;
    });
  }

  logOut(): void {
    this.userService.setEmail('');
    this.userService.setRole('');
  }
}
