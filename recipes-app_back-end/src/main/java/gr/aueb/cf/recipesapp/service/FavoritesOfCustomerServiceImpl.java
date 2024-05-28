package gr.aueb.cf.recipesapp.service;

import gr.aueb.cf.recipesapp.dto.RecipeDTO;
import gr.aueb.cf.recipesapp.model.Customer;
import gr.aueb.cf.recipesapp.model.Favorite;
import gr.aueb.cf.recipesapp.model.Recipe;
import gr.aueb.cf.recipesapp.repository.CustomerRepository;

import gr.aueb.cf.recipesapp.repository.RecipeRepository;
import gr.aueb.cf.recipesapp.service.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.recipesapp.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FavoritesOfCustomerServiceImpl implements IFavoritesOfCustomerService{

    private final CustomerRepository customerRepository;
    private final RecipeRepository recipeRepository;

    @Autowired
    public FavoritesOfCustomerServiceImpl(CustomerRepository customerRepository,RecipeRepository recipeRepository) {
        this.customerRepository = customerRepository;
        this.recipeRepository = recipeRepository;
    }

    @Transactional
    @Override
    public List<RecipeDTO> getAllFavorites(String email) throws EntityNotFoundException {
        Customer customer = customerRepository.findByEmail(email);
        List<Favorite> favorites = customer.getFavorites();
        List<RecipeDTO> recipeDTOS = new ArrayList<>();

        if (favorites.size() == 0) throw  new EntityNotFoundException(Favorite.class);

        for (Favorite favorite : favorites) {
            RecipeDTO recipeDTO = convertToRecipeDTO(favorite.getRecipe());
            recipeDTOS.add(recipeDTO);
        }

        return recipeDTOS;
    }

    @Transactional
    @Override
    public void addFavorite(String email, RecipeDTO recipeDTO) throws EntityAlreadyExistsException {
        Customer customer = customerRepository.findByEmail(email);
        List<Favorite> favorites = customer.getFavorites();
        for (Favorite favorite : favorites) {
            if ((favorite.getCustomer().getCustomerId() == customer.getCustomerId()) && (favorite.getRecipe().getRecipeId() == recipeDTO.getRecipeId())) {
                throw new EntityAlreadyExistsException(Favorite.class);

            }
        }
        Recipe recipe = convertToRecipe(recipeDTO);
        customer.addFavorite(recipe);
    }

    @Transactional
    @Override
    public void deleteFavorite(String email, Long recipeId)  {
        Customer customer = customerRepository.findByEmail(email);
        Recipe recipe = recipeRepository.findByRecipeId(recipeId);
        customer.removeFavorite(recipe);
    }

    private static Recipe convertToRecipe(RecipeDTO dto) {
        return new Recipe(dto.getRecipeId(), dto.getTitle(), dto.getIngredients(), dto.getDescription(), dto.getDuration());
    }

    private static RecipeDTO convertToRecipeDTO(Recipe recipe) {
        return new RecipeDTO(recipe.getRecipeId(), recipe.getTitle(), recipe.getIngredients(), recipe.getDescription(), recipe.getDuration());
    }
}
