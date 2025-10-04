package com.javaweb.project.service.impl;

import com.javaweb.project.entity.Post;
import com.javaweb.project.entity.User;
import com.javaweb.project.model.BlogDTO;
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


    @Override
    public Set<BlogDTO> getAllBlogs() {
        Set<BlogDTO> blogs = new HashSet<BlogDTO>();
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            return blogs;
        }
        return blogs;
    }
}
