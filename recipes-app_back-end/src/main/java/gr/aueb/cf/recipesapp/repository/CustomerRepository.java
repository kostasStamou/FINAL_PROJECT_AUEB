package gr.aueb.cf.recipesapp.repository;

import gr.aueb.cf.recipesapp.model.Customer;
import gr.aueb.cf.recipesapp.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
    boolean existsByEmail(String email);


}
