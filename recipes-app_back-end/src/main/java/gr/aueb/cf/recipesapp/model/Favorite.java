package gr.aueb.cf.recipesapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Favorites")
public class Favorite {
    @EmbeddedId
    private FavoriteId id;

    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "recipe_id", insertable = false, updatable = false)
    private Recipe recipe;
    
    

    public Favorite() {
		super();
	}

	public Favorite(FavoriteId id, Customer customer, Recipe recipe) {
        this.id = id;
        this.customer = customer;
        this.recipe = recipe;
    }

	public FavoriteId getId() {
		return id;
	}

	public void setId(FavoriteId id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

    

}
