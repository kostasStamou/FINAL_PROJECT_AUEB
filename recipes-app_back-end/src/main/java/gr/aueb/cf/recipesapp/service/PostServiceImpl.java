package gr.aueb.cf.recipesapp.service;


import gr.aueb.cf.recipesapp.dto.PostDTO;


import gr.aueb.cf.recipesapp.model.Post;


import gr.aueb.cf.recipesapp.repository.PostRepository;
import gr.aueb.cf.recipesapp.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements IPostService{


    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl( PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Transactional
    @Override
    public List<PostDTO> getAllPosts() throws EntityNotFoundException {
        List<PostDTO> postDTOS = new ArrayList<>();
        List<Post> posts = postRepository.findAll();
        if (posts.size() == 0) {
         throw new EntityNotFoundException(Post.class);
        }

        for (Post post : posts) {
            PostDTO postDTO = convertToPostDTO(post);
            postDTOS.add(postDTO);
        }

        return postDTOS;
    }

    @Transactional
    @Override
    public void addPost(PostDTO postDTO) {
        Post post = convertToPost(postDTO);
        postRepository.save(post);
    }

    @Transactional
    @Override
    public void deletePost(Long postId){
        postRepository.deleteByPostId(postId);
    }

    private static Post convertToPost(PostDTO dto) {
        return new Post(dto.getPostId(), dto.getName(), dto.getMessage());
    }

    private static PostDTO convertToPostDTO(Post post) {
        return new PostDTO(post.getPostId(), post.getName(), post.getMessage());
   }
}
