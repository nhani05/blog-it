package com.javaweb.project.controller;

import com.javaweb.project.model.response.PostDTO;
import com.javaweb.project.model.request.UpdatePostRequest;
import com.javaweb.project.model.response.ApiResponse;
import com.javaweb.project.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/api/posts")
    public ResponseEntity<Set<PostDTO>> getAllBlogPosts() {
        Set<PostDTO> blogs = postService.findAllBlogs();
        return new ResponseEntity<>(blogs, org.springframework.http.HttpStatus.OK);
    }

    @GetMapping(value = "/api/posts/search")
    public ResponseEntity<Set<PostDTO>> filterBlogPostsByASearchTerm(@RequestParam("title") String title,
                                                             @RequestParam("authorName") String authorName) {
        Set<PostDTO> blogs = postService.findBlogsByTitleOrAuthorName(title, authorName);
        return new ResponseEntity<>(blogs, org.springframework.http.HttpStatus.OK);
    }


    @PostMapping(value="/api/post/creation")
    public void createB() {
        // Logic to create a new blog post
        System.out.println("Creating a new blog");
    }

    @PutMapping(value="/api/post/update/{id}")
    public ResponseEntity<String> updateBlogPost(@PathVariable("id") Long id, @RequestBody UpdatePostRequest request) {
        postService.updateBlogPost(id, request);
        return new ResponseEntity<>("Post is updated", org.springframework.http.HttpStatus.OK);
    }

    @DeleteMapping(value="/api/post/delete/{id}")
    public ResponseEntity<String> deleteBlogPost(@PathVariable("id") Long id) {
        postService.deleteBlogPostById(id);
        return new ResponseEntity<>("Post is deleted", org.springframework.http.HttpStatus.OK);
    }
}
