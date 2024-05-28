import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import { CreateRecipeComponent } from './create-recipe/create-recipe.component';

import { DeleteRecipeComponent } from './delete-recipe/delete-recipe.component';
import { RecipesListComponent } from './recipes-list/recipes-list.component'
import { ReactiveFormsModule } from '@angular/forms';

const routes: Routes = [
  {path: '', component: RecipesListComponent},
  {path: 'create', component: CreateRecipeComponent},
  {path: 'delete', component: DeleteRecipeComponent},
  
];


@NgModule({
  declarations: [
    CreateRecipeComponent,
   
    DeleteRecipeComponent,
    RecipesListComponent
  ],
  imports: [
    CommonModule, RouterModule.forChild(routes), ReactiveFormsModule
  ]
})
export class RecipesModule { }
