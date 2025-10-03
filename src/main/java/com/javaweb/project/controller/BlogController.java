package com.javaweb.project.controller;

import com.javaweb.project.model.BlogDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BlogController {
    @GetMapping(value = "api/blogs")
    public List<BlogDTO> getBlogs() {
        List<BlogDTO> blogs = new ArrayList<>();
        BlogDTO blog = new BlogDTO();
        blog.setId(1L);
        blog.setTitle("Java Web");
        blog.setAuthor("John");
        blog.setCreatedAt("2023-10-01");
        blog.setContent("Java Web is a web application framework for building web applications.\n" +
                " It is based on the Java programming language and provides a set of tools and libraries for developing web applications.");

        BlogDTO blog2 = new BlogDTO();
        blog2.setId(2L);
        blog2.setTitle("Spring Boot");
        blog2.setAuthor("Alice");
        blog2.setCreatedAt("2023-10-02");
        blog2.setContent("Spring Boot is a framework for building web applications using the Spring framework.\n" +
                " It provides a set of tools and libraries for developing web applications quickly and easily.");
        blogs.add(blog);
        blogs.add(blog2);
        return blogs;
    }
}
