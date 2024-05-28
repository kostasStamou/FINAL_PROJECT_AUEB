package gr.aueb.cf.recipesapp.validator;

import gr.aueb.cf.recipesapp.dto.CustomerDTO;
import gr.aueb.cf.recipesapp.dto.RecipeDTO;
import gr.aueb.cf.recipesapp.repository.CustomerRepository;
import gr.aueb.cf.recipesapp.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RecipeValidator implements Validator {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeValidator(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return RecipeDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RecipeDTO recipeDTO = (RecipeDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "field.required","This field is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ingredients", "field.required","This field is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "field.required","This field is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "duration", "field.required","This field is required.");

        if (recipeDTO.getDuration() <= 0) {
            errors.rejectValue("duration", "positive.integer", "The duration field must be a positive integer.");
        }

        if (recipeRepository.existsByTitle(recipeDTO.getTitle())) {
            errors.rejectValue("title", "field.duplicate","This field already exists.");
        }
    }
}
