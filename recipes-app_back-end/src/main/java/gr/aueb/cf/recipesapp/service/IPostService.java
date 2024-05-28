package gr.aueb.cf.recipesapp.service;

import gr.aueb.cf.recipesapp.dto.PostDTO;

import gr.aueb.cf.recipesapp.service.exceptions.EntityNotFoundException;

import java.util.List;

public interface IPostService {
    List<PostDTO> getAllPosts() throws EntityNotFoundException;

    void addPost(PostDTO postDTO);

    void deletePost(Long postId);
}
