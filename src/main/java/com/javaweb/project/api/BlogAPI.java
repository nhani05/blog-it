package com.javaweb.project.api;

import com.javaweb.project.dto.response.ApiResponse;
import com.javaweb.project.dto.BlogDTO;
import com.javaweb.project.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class BlogAPI {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/api/blogs")
    public ApiResponse<Set<BlogDTO>> getAllBlogPosts() {
        Set<BlogDTO> blogs = postService.findAllBlogs();
        return new ApiResponse<>( 200, "Success", blogs);
    }

    @GetMapping(value = "/api/blogs-search")
    public ApiResponse<Set<BlogDTO>> filterBlogPostsByASearchTerm(@RequestParam("title") String title,
                                                             @RequestParam("authorName") String authorName) {
        Set<BlogDTO> blogs = postService.findBlogsByTitleOrAuthorName(title, authorName);
        return new ApiResponse<>(200, "Success", blogs);
    }


    @PostMapping(value="/api/blog")
    public void createBlog() {
        // Logic to create a new blog post
        System.out.println("Creating a new blog");
    }
}
