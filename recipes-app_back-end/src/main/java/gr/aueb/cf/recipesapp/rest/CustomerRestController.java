package gr.aueb.cf.recipesapp.rest;



import gr.aueb.cf.recipesapp.dto.CustomerDTO;
import gr.aueb.cf.recipesapp.dto.RecipeDTO;
import gr.aueb.cf.recipesapp.service.ICustomerService;
import gr.aueb.cf.recipesapp.service.exceptions.EntityNotFoundException;
import gr.aueb.cf.recipesapp.service.util.LoggerUtil;
import gr.aueb.cf.recipesapp.validator.CustomerValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/customers")
public class CustomerRestController {

    private final ICustomerService customerService;
    private final CustomerValidator customerValidator;

    @Autowired
    public CustomerRestController(ICustomerService customerService, CustomerValidator customerValidator) {
        this.customerService = customerService;
        this.customerValidator = customerValidator;
    }

    @Operation(summary = "Get all customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "customers Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "not found",
                    content = @Content)})
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        try {
            List<CustomerDTO> customers = customerService.getAllCustomers();
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            LoggerUtil.logError(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get customer by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "customer Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "customer not found",
                    content = @Content)})
    @GetMapping("/{email}")
    public ResponseEntity<CustomerDTO> getCustomerByEmail(@PathVariable String email) {
        try {
            CustomerDTO customer = customerService.getCustomerByEmail(email);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            LoggerUtil.logError(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Add a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "customer created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<Void> addCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            Errors errors = new BeanPropertyBindingResult(customerDTO, "customerDTO");
            ValidationUtils.invokeValidator(customerValidator, customerDTO, errors);

            if (errors.hasErrors()) {
                throw new ValidationException(String.valueOf(errors.getFieldError().getDefaultMessage()));
            }
            customerService.addCustomer(customerDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch(ValidationException e) {
            LoggerUtil.logError(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @Operation(summary = "Update a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "customer updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content) })
    @CrossOrigin(origins = "*")
    @PutMapping("/{email}")
    public ResponseEntity<Void> updateCustomer(@PathVariable String email, @RequestBody CustomerDTO customerDTO) {
        try {
            Errors errors = new BeanPropertyBindingResult(customerDTO, "customerDTO");
            ValidationUtils.invokeValidator(customerValidator, customerDTO, errors);

            if (errors.hasErrors()) {
                throw new ValidationException(String.valueOf(errors.getFieldError().getDefaultMessage()));
            }

            customerService.updateCustomer(email, customerDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            LoggerUtil.logError(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch(ValidationException e) {
            LoggerUtil.logError(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Delete a customer by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "customer Deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content)})
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteCustomerByEmail(@PathVariable String email) {
        try {
            customerService.deleteCustomerByEmail(email);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            LoggerUtil.logError(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

