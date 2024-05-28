import { Component, OnInit, OnDestroy } from '@angular/core';
import { Recipe } from 'src/app/app.interfaces';
import { CustomerService } from '../customers.service';
import { UserService } from 'src/app/user-service.service';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites.component.html',
  styleUrls: ['./favorites.component.css']
})
export class FavoritesComponent implements OnInit, OnDestroy {
  email!: string;
  favorites: Recipe[] = [];
  private emailSubscription: Subscription | undefined;

  constructor(private customerService: CustomerService, private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.emailSubscription = this.userService.getEmail().subscribe(async (email: string) => {
      this.email = email;
      await this.getRecipes();
    });
  }

  ngOnDestroy(): void {
    if (this.emailSubscription) {
      this.emailSubscription.unsubscribe();
    }
  }

  async getRecipes(): Promise<void> {
    try {
      this.favorites = await this.customerService.seeFavorites(this.email).toPromise();
    } catch (error) {
      console.error('Σφάλμα κατά τη λήψη των αγαπημένων συνταγών:', error);
    }
  }

  async removeFavorite(favorite: Recipe): Promise<void> {
    console.log(this.email, favorite.recipeId);
    try {
      await this.customerService.deleteFavorite(this.email, favorite.recipeId).toPromise();
      console.log('Η διαγραφή συνταγής ήταν επιτυχής');
      alert('Η διαγραφή συνταγής ήταν επιτυχής');
      
      this.favorites = this.favorites.filter((fav: Recipe) => fav.recipeId !== favorite.recipeId);
    } catch (error) {
      console.error('Σφάλμα κατά τη διαγραφή συνταγής:', error, favorite.recipeId);
      alert('Σφάλμα κατά τη διαγραφή συνταγής');
    }
  }
}
