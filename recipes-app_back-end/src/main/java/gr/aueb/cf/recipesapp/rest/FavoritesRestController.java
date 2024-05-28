package gr.aueb.cf.recipesapp.rest;



import gr.aueb.cf.recipesapp.dto.RecipeDTO;
import gr.aueb.cf.recipesapp.model.Favorite;
import gr.aueb.cf.recipesapp.service.IFavoritesOfCustomerService;
import gr.aueb.cf.recipesapp.service.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.recipesapp.service.exceptions.EntityNotFoundException;
import gr.aueb.cf.recipesapp.service.util.LoggerUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/customer")
public class FavoritesRestController {

    private final IFavoritesOfCustomerService favoritesOfCustomerService;


    @Autowired
    public FavoritesRestController(IFavoritesOfCustomerService favoritesOfCustomerService) {
        this.favoritesOfCustomerService = favoritesOfCustomerService;
    }

    @Operation(summary = "Get all favorites by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "favorites Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Favorite.class)) }),
            @ApiResponse(responseCode = "400", description = "not found",
                    content = @Content)})
    @GetMapping("/{email}/favorites")
    public ResponseEntity<List<RecipeDTO>> getAllFavorites(@PathVariable("email") String email) {
        try {
            List<RecipeDTO> favorites = favoritesOfCustomerService.getAllFavorites(email);
            return ResponseEntity.ok(favorites);
        } catch (EntityNotFoundException e) {
            LoggerUtil.logError(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Add a favorite")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "favorite created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Favorite.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content)})
    @PostMapping("/{email}/favorites")
    public ResponseEntity<?> addFavorite(@PathVariable("email") String email, @RequestBody RecipeDTO recipeDTO) {
        try {

            favoritesOfCustomerService.addFavorite(email, recipeDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (EntityAlreadyExistsException e) {
            LoggerUtil.logError(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @Operation(summary = "Delete a favorite")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "favorite Deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Favorite.class)) }),
            @ApiResponse(responseCode = "404", description = "favorite not found",
                    content = @Content)})
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{email}/favorites/{recipeId}")
    public ResponseEntity<?> deleteFavorite(@PathVariable("email") String email, @PathVariable("recipeId") Long recipeId) {
        try {
            favoritesOfCustomerService.deleteFavorite(email, recipeId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            LoggerUtil.logError(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

