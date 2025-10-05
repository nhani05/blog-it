package com.javaweb.project.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.project.converter.PostConverter;
import com.javaweb.project.dto.PostDTO;
import com.javaweb.project.entity.Post;
import com.javaweb.project.repository.PostRepository;
import com.javaweb.project.service.PostService;

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

//    @Override
//    public Set<BlogDTO> getBlogsByTitle(String title) {
//        Set<BlogDTO> blogs = new HashSet<BlogDTO>();
//
//        return Collections.emptySet();
//    }
}
