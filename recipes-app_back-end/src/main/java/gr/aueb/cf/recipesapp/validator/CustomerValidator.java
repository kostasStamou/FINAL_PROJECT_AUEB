package gr.aueb.cf.recipesapp.validator;

import gr.aueb.cf.recipesapp.dto.CustomerDTO;
import gr.aueb.cf.recipesapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CustomerValidator implements Validator {

    private final CustomerRepository customerRepository;


    @Autowired
    public CustomerValidator(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CustomerDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerDTO customerDTO = (CustomerDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "field.required","This field firstname is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "field.required","This field lastname is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required","This field email is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required","This field password is required.");


        if (customerRepository.existsByEmail(customerDTO.getEmail())) {
            errors.rejectValue("email", "field.duplicate","This field already exists.");
        }


        if (!customerDTO.getEmail().matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")) {
            errors.rejectValue("email", "field.invalid","This field is invalid");
        }
    }
}

