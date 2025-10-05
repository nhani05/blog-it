package com.javaweb.project.service.impl;

import com.javaweb.project.entity.Post;
import com.javaweb.project.entity.User;
import com.javaweb.project.dto.BlogDTO;
import com.javaweb.project.repository.PostRepository;
import com.javaweb.project.service.PostService;
import com.sun.crypto.provider.BlowfishKeyGenerator;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;


    @Override
    public Set<BlogDTO> findAllBlogs() {
        Set<BlogDTO> blogs = new HashSet<BlogDTO>();
        List<Post> posts = postRepository.findAll();
        for (Post post : posts) {
            BlogDTO blogDTO = new BlogDTO();
            User author = post.getAuthorUser();
            blogDTO.setTitle(post.getTitle());
            blogDTO.setContent(post.getContent());
            blogDTO.setExcerpt(post.getExcerpt());
            blogDTO.setAuthor(author.getDisplayName());
            blogDTO.setCreatedAt(post.getPublishedAt().toString());
            blogDTO.setUpdatedAt(post.getUpdatedAt().toString());
            blogs.add(blogDTO);
        }
        return blogs;
    }

    @Override
    public Set<BlogDTO> findBlogsByTitleOrAuthorName(String title, String authorName) {
        Set<BlogDTO> blogs = new HashSet<>();
        List<Post> posts = postRepository.findPostsByTitleOrAuthor(title, authorName);
        for(Post p : posts) {
            BlogDTO blog = new BlogDTO();
            blog.setTitle(p.getTitle());
            blog.setContent(p.getContent());
            blog.setAuthor(p.getAuthorUser().getDisplayName());
            blog.setExcerpt(p.getExcerpt());
            blog.setCreatedAt(p.getPublishedAt().toString());
            blog.setUpdatedAt(p.getUpdatedAt().toString());
            blogs.add(blog);
        }
        return blogs;
    }

//    @Override
//    public Set<BlogDTO> getBlogsByTitle(String title) {
//        Set<BlogDTO> blogs = new HashSet<BlogDTO>();
//
//        return Collections.emptySet();
//    }
}
