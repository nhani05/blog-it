package com.javaweb.project.api;

import com.javaweb.project.model.BlogDTO;
import com.javaweb.project.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class BlogAPI {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/api/blogs")
    public Set<BlogDTO> getAllBlogs() {
        return postService.getAllBlogs();
    }

    @GetMapping(value = "/api/blogs")
    public Set<BlogDTO> findBlogsByTitle(@RequestParam String title) {
        
        return new HashSet<>();
    }


    @PostMapping(value="/api/blog")
    public void createBlog() {
        // Logic to create a new blog post
        System.out.println("Creating a new blog");
    }
}
