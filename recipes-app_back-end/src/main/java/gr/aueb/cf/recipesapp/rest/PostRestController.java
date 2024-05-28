package gr.aueb.cf.recipesapp.rest;



import gr.aueb.cf.recipesapp.dto.PostDTO;
import gr.aueb.cf.recipesapp.dto.RecipeDTO;
import gr.aueb.cf.recipesapp.service.IPostService;
import gr.aueb.cf.recipesapp.service.exceptions.EntityNotFoundException;
import gr.aueb.cf.recipesapp.service.util.LoggerUtil;
import gr.aueb.cf.recipesapp.validator.PostValidator;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostRestController {

    private final IPostService postService;
    private final PostValidator postValidator;

    @Autowired
    public PostRestController(IPostService postService, PostValidator postValidator) {
        this.postService = postService;
        this.postValidator = postValidator;
    }

    @Operation(summary = "Get all posts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "posts Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PostDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "not found",
                    content = @Content)})
    @GetMapping("")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        try {
            List<PostDTO> posts = postService.getAllPosts();
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            LoggerUtil.logError(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @Operation(summary = "Add a post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "post created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PostDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content)})
    @PostMapping("")
    public ResponseEntity<String> addPost(@RequestBody PostDTO postDTO) {
        try {
            Errors errors = new BeanPropertyBindingResult(postDTO, "customerDTO");
            ValidationUtils.invokeValidator(postValidator, postDTO, errors);

            if (errors.hasErrors()) {
                throw new ValidationException(String.valueOf(errors.getFieldError().getDefaultMessage()));
            }
            postService.addPost(postDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch(ValidationException e) {
            LoggerUtil.logError(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Delete a post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "post Deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PostDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "post not found",
                    content = @Content)})
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
