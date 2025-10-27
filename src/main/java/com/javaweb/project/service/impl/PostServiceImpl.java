package com.javaweb.project.service.impl;

import com.javaweb.project.converter.PostConverter;
import com.javaweb.project.dto.PostDTO;
import com.javaweb.project.dto.request.UpdatePostRequest;
import com.javaweb.project.entity.Post;
import com.javaweb.project.repository.PostRepository;
import com.javaweb.project.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostConverter postConverter;

    @Override
    public Set<PostDTO> findAllBlogs() {
        Set<PostDTO> blogPosts = new HashSet<PostDTO>();
        List<Post> posts = postRepository.findAll();
        for (Post p : posts) {
            blogPosts.add(postConverter.convertToDTO(p));
        }
        return blogPosts;
    }

    @Override
    public Set<PostDTO> findBlogsByTitleOrAuthorName(String title, String authorName) {
        Set<PostDTO> blogPosts = new HashSet<>();
        List<Post> posts = postRepository.findPostsByTitleOrAuthor(title, authorName);
        for(Post p : posts) {
            blogPosts.add(postConverter.convertToDTO(p));
        }
        return blogPosts;
    }

    @Override
    public void updateBlogPost(Long id, UpdatePostRequest request) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        postRepository.save(postConverter.convertToEntity(request, post));
    }

    @Override
    public void deleteBlogPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        postRepository.deleteById(id);
    }


}
