package gr.aueb.cf.recipesapp.dto;

import gr.aueb.cf.recipesapp.model.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDTO {
    private Long postId;

    private String name;
    private String message;
    
    

    public PostDTO() {
		super();
	}

	public PostDTO(Long postId, String name, String message) {
        this.postId = postId;
        this.name = name;
        this.message = message;
    }

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
    
}
