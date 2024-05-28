package gr.aueb.cf.recipesapp.repository;

import gr.aueb.cf.recipesapp.model.Customer;
import gr.aueb.cf.recipesapp.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByCustomer(Customer customer);
    void deleteById_CustomerId(Long customerId);
    void deleteById_RecipeId(Long recipeId);
}
