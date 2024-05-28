package gr.aueb.cf.recipesapp.validator;

import gr.aueb.cf.recipesapp.dto.PostDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PostValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return PostDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PostDTO postDTO = (PostDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required", "the field is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message", "field.required", "the field is required");
    }
}
