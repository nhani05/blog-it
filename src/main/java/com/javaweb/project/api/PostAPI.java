package com.javaweb.project.api;

import com.javaweb.project.dto.PostDTO;
import com.javaweb.project.dto.request.UpdatePostRequest;
import com.javaweb.project.dto.response.ApiResponse;
import com.javaweb.project.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class PostAPI {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/api/posts")
    public ApiResponse<Set<PostDTO>> getAllBlogPosts() {
        Set<PostDTO> blogs = postService.findAllBlogs();
        return new ApiResponse<>( 200, "Success", blogs);
    }

    @GetMapping(value = "/api/posts/search")
    public ApiResponse<Set<PostDTO>> filterBlogPostsByASearchTerm(@RequestParam("title") String title,
                                                             @RequestParam("authorName") String authorName) {
        Set<PostDTO> blogs = postService.findBlogsByTitleOrAuthorName(title, authorName);
        return new ApiResponse<>(200, "Success", blogs);
    }


//    @PostMapping(value="/api/post/creation")
//    public void createB() {
//        // Logic to create a new blog post
//        System.out.println("Creating a new blog");
//    }

    @PutMapping(value="/api/post/update/{id}")
    public ApiResponse<String> updateBlogPost(@PathVariable("id") Long id, @RequestBody UpdatePostRequest request) {
        postService.updateBlogPost(id, request);
        return new ApiResponse<>(200, "Success", "Post is updated successfully");
    }

//    @DeleteMapping(value="/api/post/delete/{id}")
//    public ApiResponse<String> deleteBlogPost(@PathVariable("id") Long id) {
//        postService.deleteBlogPostById(id);
//        return new ApiResponse<>(200, "Success", "Post is deleted");
//    }
}
