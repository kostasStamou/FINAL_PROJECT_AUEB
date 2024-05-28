package gr.aueb.cf.recipesapp.service;

import gr.aueb.cf.recipesapp.dto.RecipeDTO;
import gr.aueb.cf.recipesapp.service.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.recipesapp.service.exceptions.EntityNotFoundException;

import java.util.List;

public interface IFavoritesOfCustomerService {
    List<RecipeDTO> getAllFavorites(String email) throws EntityNotFoundException;

    void addFavorite(String email, RecipeDTO recipeDTO) throws EntityAlreadyExistsException;

    void deleteFavorite(String email, Long recipeId) ;
}
