package gr.aueb.cf.recipesapp.service;


import gr.aueb.cf.recipesapp.dto.RecipeDTO;



import gr.aueb.cf.recipesapp.model.Recipe;

import gr.aueb.cf.recipesapp.repository.FavoriteRepository;
import gr.aueb.cf.recipesapp.repository.RecipeRepository;
import gr.aueb.cf.recipesapp.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeServiceImpl implements IRecipeService {

    private final RecipeRepository recipeRepository;
    private final FavoriteRepository favoriteRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, FavoriteRepository favoriteRepository) {
        this.recipeRepository = recipeRepository;
        this.favoriteRepository = favoriteRepository;
    }

    @Transactional
    @Override
    public List<RecipeDTO> getAllRecipes() throws EntityNotFoundException {
        List<Recipe> recipes;
        List<RecipeDTO> recipeDTOS = new ArrayList<>();
        recipes =  recipeRepository.findAll();
        if (recipes.size() == 0) throw new EntityNotFoundException(Recipe.class);

        for (Recipe recipe : recipes) {
            RecipeDTO recipeDTO;
            recipeDTO = convertToRecipeDTO(recipe);
            recipeDTOS.add(recipeDTO);
        }

        return recipeDTOS;
    }

    @Transactional
    @Override
    public RecipeDTO getRecipeByTitle(String title) throws EntityNotFoundException {
        Recipe recipe;
        RecipeDTO recipeDTO;
        recipe = recipeRepository.findByTitle(title);
        if (recipe == null) throw new  EntityNotFoundException(Recipe.class);

        recipeDTO = convertToRecipeDTO(recipe);
        return recipeDTO;
    }

    @Transactional
    @Override
    public void addRecipe(RecipeDTO recipeDTO) {
        Recipe recipe;
        recipe = convertToRecipe(recipeDTO);
        recipeRepository.save(recipe);
    }



    @Transactional
    @Override
    public void deleteRecipeById(Long recipeId) throws EntityNotFoundException {
        Recipe recipe = recipeRepository.findByRecipeId(recipeId);

        if (recipe == null) throw new EntityNotFoundException(Recipe.class);



        favoriteRepository.deleteById_RecipeId(recipe.getRecipeId());
        recipeRepository.delete(recipe);
    }

    private static Recipe convertToRecipe(RecipeDTO dto) {
        return new Recipe(dto.getRecipeId(), dto.getTitle(), dto.getIngredients(), dto.getDescription(), dto.getDuration());
    }

    private static RecipeDTO convertToRecipeDTO(Recipe recipe) {
        return new RecipeDTO(recipe.getRecipeId(), recipe.getTitle(), recipe.getIngredients(), recipe.getDescription(), recipe.getDuration());
    }
}
