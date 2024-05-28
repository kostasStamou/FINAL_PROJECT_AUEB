package gr.aueb.cf.recipesapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Long recipeId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "ingredients", nullable = false)
    private String ingredients;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "duration", nullable = false)
    private int duration;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorite> favorites;

    
    public Recipe() {
		super();
	}

	public Recipe(Long recipeId, String title, String ingredients, String description, int duration) {
        this.recipeId = recipeId;
        this.title = title;
        this.ingredients = ingredients;
        this.description = description;
        this.duration = duration;
    }

    public void removeFavorite(Favorite favorite) {
        favorites.remove(favorite);
        favorite.setRecipe(null);
    }

	public Long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Long recipeId) {
		this.recipeId = recipeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public List<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}
    
    
    
    
}
