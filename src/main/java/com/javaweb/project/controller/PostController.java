package com.javaweb.project.controller;

import com.javaweb.project.dto.request.CreatePostRequest;
import com.javaweb.project.dto.response.PostDTO;
import com.javaweb.project.dto.request.UpdatePostRequest;
import com.javaweb.project.dto.response.PostDetailDTO;
import com.javaweb.project.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/posts")
    public ResponseEntity<Set<PostDTO>> getAllBlogPosts() {
        Set<PostDTO> blogs = postService.findAllBlogs();
        return new ResponseEntity<>(blogs, org.springframework.http.HttpStatus.OK);
    }

    @GetMapping(value = "/posts/search")
    public ResponseEntity<Set<PostDTO>> filterBlogPostsByASearchTerm(@RequestParam("title") String title,
                                                             @RequestParam("authorName") String authorName) {
        Set<PostDTO> blogs = postService.findBlogsByTitleOrAuthorName(title, authorName);
        return new ResponseEntity<>(blogs, org.springframework.http.HttpStatus.OK);
    }


//    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value="/post/create")
    public  ResponseEntity<String> createBlogPost(@RequestBody CreatePostRequest request) {
        postService.createNewBlogPost(request);
        return new ResponseEntity<>("Post is created", org.springframework.http.HttpStatus.CREATED);
    }

    @PutMapping(value="/post/update/{id}")
    public ResponseEntity<String> updateBlogPost(@PathVariable("id") Long id, @RequestBody UpdatePostRequest request) {
        postService.updateBlogPost(id, request);
        return new ResponseEntity<>("Post is updated", org.springframework.http.HttpStatus.OK);
    }

    @DeleteMapping(value="/post/delete/{id}")
    public ResponseEntity<String> deleteBlogPost(@PathVariable("id") Long id) {
        postService.deleteBlogPostById(id);
        return new ResponseEntity<>("Post is deleted", org.springframework.http.HttpStatus.OK);
    }

    @GetMapping(value= "/post/detail/{id}")
    public ResponseEntity<PostDetailDTO> getASinglePostDetail(@PathVariable("id") Long id) {
        PostDetailDTO postDetailDTO = postService.getAPostDetail(id);
        return new ResponseEntity<>(postDetailDTO, HttpStatus.OK);
    }
}
