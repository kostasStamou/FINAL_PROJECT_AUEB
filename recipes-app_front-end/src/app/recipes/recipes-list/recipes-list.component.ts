import { Component } from '@angular/core';
import { Recipe } from 'src/app/app.interfaces';
import { RecipesService } from '../recipes.service';
import { UserService } from 'src/app/user-service.service';

@Component({
  selector: 'app-recipes-list',
  templateUrl: './recipes-list.component.html',
  styleUrls: ['./recipes-list.component.css']
})
export class RecipesListComponent {
  email!:string;
  recipes: Recipe[] = [];

  constructor(private recipeService: RecipesService,private userService: UserService) { 
    this.userService.getEmail().subscribe((email: string) => {
      this.email = email;
    });
  }

  ngOnInit(): void {
    this.getRecipes();
  }

  getRecipes(): void {
    this.recipeService.getAllRecipes()
      .subscribe((recipes) => this.recipes = recipes);
  }

  addFavorite(recipe:Recipe){
    this.recipeService.addToFavorites(this.email,recipe)
    .subscribe(
      response => {
        console.log('Η καταχώριση συνταγής ήταν επιτυχής:', response);
        alert('Η καταχώριση πελάτη ήταν επιτυχής')
      },
      error => {
        console.error('Σφάλμα κατά την καταχώριση συνταγής:', error);
        alert('recipe already exists to favorites')
      }
    );
  }
}
