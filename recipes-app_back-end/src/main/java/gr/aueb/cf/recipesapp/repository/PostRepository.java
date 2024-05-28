package gr.aueb.cf.recipesapp.repository;

import gr.aueb.cf.recipesapp.model.Customer;
import gr.aueb.cf.recipesapp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    void deleteByPostId(Long postId);
}
