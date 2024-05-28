import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { RecipesService } from '../recipes.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-recipe',
  templateUrl: './create-recipe.component.html',
  styleUrls: ['./create-recipe.component.css']
})
export class CreateRecipeComponent {
  recipeForm: FormGroup;
  postStatus: number | undefined;
  private subscription: Subscription = new Subscription();

  constructor(
    private formBuilder: FormBuilder,
    private recipeService: RecipesService,
    private router: Router
  ) {
    this.recipeForm = this.formBuilder.group({
      title: ['', Validators.required],
      ingredients: ['', Validators.required],
      description: ['', Validators.required],
      duration: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.recipeForm.invalid) {
      return;
    }

    const newRecipe = {
      recipeId: 0,
      title: this.recipeForm.value.title,
      ingredients: this.recipeForm.value.ingredients,
      description: this.recipeForm.value.description,
      duration: this.recipeForm.value.duration
    };

    this.subscription.add(
      this.recipeService.createRecipe(newRecipe).subscribe(
        (response) => {
          console.log(response);
          if (response === null) {
            this.postStatus = 201;
          } else {
            this.postStatus = -1;
          }
        },
        () => {
          this.postStatus = -1;
        }
      )
    );
  }

  navigateToPosts() {
    this.router.navigate(['/post']);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}

