package gr.aueb.cf.recipesapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecipeDTO {
    private Long recipeId;
    private String title;
    private String ingredients;
    private String description;
    private int duration;

    

    public RecipeDTO() {
		super();
	}



	public RecipeDTO(Long recipeId, String title, String ingredients, String description, int duration) {
        this.recipeId = recipeId;
        this.title = title;
        this.ingredients = ingredients;
        this.description = description;
        this.duration = duration;
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
    
    
}
