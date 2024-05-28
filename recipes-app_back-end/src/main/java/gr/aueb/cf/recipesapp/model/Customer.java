package gr.aueb.cf.recipesapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorite> favorites;

    

    public Customer() {
		super();
	}

	public Customer(Long customerId, String firstname, String lastname, String email, String password) {
        this.customerId = customerId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public void addFavorite(Recipe recipe) {
        Favorite favorite = new Favorite();
        favorite.setCustomer(this);
        favorite.setRecipe(recipe);
        FavoriteId favoriteId = new FavoriteId();
        favoriteId.setCustomerId(this.customerId);
        favoriteId.setRecipeId(recipe.getRecipeId());
        favorite.setId(favoriteId);
        favorites.add(favorite);
    }

    public void removeFavorite(Recipe recipe) {
        favorites.removeIf(f -> f.getRecipe().getRecipeId().equals(recipe.getRecipeId()));
    }

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}

	
    
}
