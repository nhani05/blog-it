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

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/posts")
    public ResponseEntity<List<PostDTO>> getAllBlogPosts() {
        List<PostDTO> blogs = postService.findAllBlogs();
        return new ResponseEntity<>(blogs, org.springframework.http.HttpStatus.OK);
    }

    @GetMapping(value = "/post/{id}")
    public ResponseEntity<PostDTO> getBlogPostById(@PathVariable("id") Long id) {
        PostDTO blog = postService.findBlogById(id);
        return new ResponseEntity<>(blog, org.springframework.http.HttpStatus.OK);
    }

    @GetMapping(value = "/posts/search")
    public ResponseEntity<List<PostDTO>> filterBlogPostsByASearchTerm(@RequestParam("title") String title,
                                                             @RequestParam("authorName") String authorName) {
        List<PostDTO> blogs = postService.findBlogsByTitleOrAuthorName(title, authorName);
        return new ResponseEntity<>(blogs, org.springframework.http.HttpStatus.OK);
    }


//    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value="/post/create")
    public  ResponseEntity<String> createBlogPost(@RequestBody CreatePostRequest request) {
        postService.createNewBlogPost(request);
        return new ResponseEntity<>("POST IS CREATED SUCCESSFULLY", org.springframework.http.HttpStatus.CREATED);
    }

    @PutMapping(value="/post/update/{id}")
    public ResponseEntity<String> updateBlogPost(@PathVariable("id") Long id, @RequestBody UpdatePostRequest request) {
        postService.updateBlogPost(id, request);
        return new ResponseEntity<>("POST IS UPDATED SUCCESSFULLY", org.springframework.http.HttpStatus.OK);
    }

    @DeleteMapping(value="/post/delete/{id}")
    public ResponseEntity<String> deleteBlogPost(@PathVariable("id") Long id) {
        postService.deleteBlogPostById(id);
        return new ResponseEntity<>("POST IS DELETED SUCCESSFULLY", org.springframework.http.HttpStatus.OK);
    }

    @GetMapping(value= "/post/detail/{id}")
    public ResponseEntity<PostDetailDTO> getASinglePostDetail(@PathVariable("id") Long id) {
        PostDetailDTO postDetailDTO = postService.getAPostDetail(id);
        return new ResponseEntity<>(postDetailDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/post/myblog")
    public ResponseEntity<Object> getMyBlog() {
        List<PostDTO> postDTOs = postService.getAllMyBlog();
        if(postDTOs.isEmpty()) {
            return ResponseEntity.ok("USER DONT HAVE ANY POST");
        } else {
            return ResponseEntity.ok(postDTOs);
        }
    }
}
