import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Recipe } from 'src/app/app.interfaces';
import { RecipesService } from '../recipes.service';

@Component({
  selector: 'app-delete-recipe',
  templateUrl: './delete-recipe.component.html',
  styleUrls: ['./delete-recipe.component.css']
})
export class DeleteRecipeComponent {
  recipes: Recipe[] = [];
  postStatus: number | undefined;

  constructor(private recipeService: RecipesService, private router: Router) {}

  ngOnInit(): void {
    this.getRecipes();
  }

  getRecipes(): void {
    this.recipeService.getAllRecipes().subscribe(recipes => (this.recipes = recipes));
  }

  deleteRecipe(recipeId: Number): void {
    this.recipeService.deleteRecipe(recipeId).subscribe(
      (response) => {
        console.log(response);
        if (response === null) {
          this.postStatus = 201;
          alert("ok")
        } else {
          this.postStatus = -1;
        }
      },
      () => {
        this.postStatus = -1;
        alert("something wrong")
      }
    );
  }

  navigateToPosts(): void {
    this.router.navigate(['/post']);
  }
}
