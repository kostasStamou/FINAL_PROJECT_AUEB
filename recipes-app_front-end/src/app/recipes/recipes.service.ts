import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Recipe } from '../app.interfaces';

@Injectable({
  providedIn: 'root'
})
export class RecipesService {
  private apiUrl = 'http://localhost:8080/api/recipes';
  private apiUrl2 = 'http://localhost:8080/api/customer';
  
  constructor(private http: HttpClient) { }

  getAllRecipes(): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(`${this.apiUrl}/`);
  }

  addToFavorites(email:String,recipe:Recipe):Observable<any> {
    return this.http.post<any>(`${this.apiUrl2}/${email}/favorites`, recipe);
}

createRecipe(recipe:Recipe):Observable<any> {
  return this.http.post<any>(`${this.apiUrl}`, recipe);
}

deleteRecipe(recipeId:Number):Observable<any> {
  return this.http.delete<any>(`${this.apiUrl}/${recipeId}`);
}
}
