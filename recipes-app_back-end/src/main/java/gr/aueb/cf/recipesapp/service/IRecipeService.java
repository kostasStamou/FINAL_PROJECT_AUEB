package gr.aueb.cf.recipesapp.service;


import gr.aueb.cf.recipesapp.dto.RecipeDTO;
import gr.aueb.cf.recipesapp.service.exceptions.EntityNotFoundException;

import java.util.List;

public interface IRecipeService {
    List<RecipeDTO> getAllRecipes() throws EntityNotFoundException;
    RecipeDTO getRecipeByTitle(String title) throws EntityNotFoundException;
    void addRecipe(RecipeDTO recipeDTO);

    void deleteRecipeById(Long recipeId) throws EntityNotFoundException;
}
