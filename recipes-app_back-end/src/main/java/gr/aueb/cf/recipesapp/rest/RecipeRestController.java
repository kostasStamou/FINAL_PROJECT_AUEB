package gr.aueb.cf.recipesapp.rest;



import gr.aueb.cf.recipesapp.dto.RecipeDTO;
import gr.aueb.cf.recipesapp.service.IRecipeService;
import gr.aueb.cf.recipesapp.service.exceptions.EntityNotFoundException;
import gr.aueb.cf.recipesapp.service.util.LoggerUtil;
import gr.aueb.cf.recipesapp.validator.RecipeValidator;
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
@RequestMapping("api/recipes")
public class RecipeRestController {

    private final IRecipeService recipeService;
    private final RecipeValidator recipeValidator;

    @Autowired
    public RecipeRestController(IRecipeService recipeService, RecipeValidator recipeValidator) {
        this.recipeService = recipeService;
        this.recipeValidator = recipeValidator;
    }

    @Operation(summary = "Get all recipes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "recipes Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RecipeDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "not found",
                    content = @Content)})
    @GetMapping
    public ResponseEntity<List<RecipeDTO>> getAllRecipes() {
        try {
            List<RecipeDTO> recipes = recipeService.getAllRecipes();
            return new ResponseEntity<>(recipes, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            LoggerUtil.logError(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get recipe by title")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "recipe Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RecipeDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "recipe not found",
                    content = @Content)})
    @GetMapping("/{title}")
    public ResponseEntity<RecipeDTO> getRecipeByTitle(@PathVariable String title) {
        try {
            RecipeDTO recipe = recipeService.getRecipeByTitle(title);
            return new ResponseEntity<>(recipe, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            LoggerUtil.logError(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Add a recipe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "recipe created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RecipeDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<Void> addRecipe(@RequestBody RecipeDTO recipeDTO) {
        try {
            Errors errors = new BeanPropertyBindingResult(recipeDTO, "recipeDTO");
            ValidationUtils.invokeValidator(recipeValidator, recipeDTO, errors);

            if (errors.hasErrors()) {
                throw new ValidationException(String.valueOf(errors.getFieldError().getDefaultMessage()));

            }
            recipeService.addRecipe(recipeDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch(ValidationException e) {
            LoggerUtil.logError(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



    @Operation(summary = "Delete a recipe by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recipe Deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RecipeDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Recipe not found",
                    content = @Content)})
    @DeleteMapping("/{recipeId}")
    public ResponseEntity<Void> deleteRecipeById(@PathVariable Long recipeId) {
        try {
            recipeService.deleteRecipeById(recipeId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            LoggerUtil.logError(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
