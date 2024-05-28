package gr.aueb.cf.recipesapp.repository;

import gr.aueb.cf.recipesapp.model.Customer;
import gr.aueb.cf.recipesapp.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findByTitle(String title);
    List<Recipe> findAllByFavorites_Customer(Customer customer);
    boolean existsByTitle(String title);

    Recipe findByRecipeId(Long recipeId);
}
