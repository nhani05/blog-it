package com.javaweb.project.service.impl;

import com.javaweb.project.converter.PostConverter;
import com.javaweb.project.dto.request.CreatePostRequest;
import com.javaweb.project.dto.response.PostDTO;
import com.javaweb.project.dto.request.UpdatePostRequest;
import com.javaweb.project.dto.response.PostDetailDTO;
import com.javaweb.project.entity.Post;
import com.javaweb.project.entity.User;
import com.javaweb.project.repository.PostRepository;
import com.javaweb.project.repository.UserRepository;
import com.javaweb.project.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.net.CacheRequest;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostConverter postConverter;

    @Override
    public Set<PostDTO> findAllBlogs() {
        Set<PostDTO> blogPosts = new HashSet<PostDTO>();
        List<Post> posts = postRepository.findAll();
        for (Post p : posts) {
            blogPosts.add(postConverter.convertPostToPostDTO(p));
        }
        return blogPosts;
    }

    @Override
    public Set<PostDTO> findBlogsByTitleOrAuthorName(String title, String authorName) {
        Set<PostDTO> blogPosts = new HashSet<>();
        List<Post> posts = postRepository.findPostsByTitleOrAuthor(title, authorName);
        for(Post p : posts) {
            blogPosts.add(postConverter.convertPostToPostDTO(p));
        }
        return blogPosts;
    }

    @Override
    public void updateBlogPost(Long id, UpdatePostRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bài đăng không được tìm thấy hoặc không tồn tại"));
        postRepository.save(postConverter.convertUpdatePostRequestToEntity(request, post));
    }

    @Override
    public void deleteBlogPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bài đăng không được tìm thấy hoặc không tồn tại"));
        postRepository.deleteById(id);
    }

    @Override
    public PostDTO findBlogById(Long id) {
        Post p = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bài đăng không được tìm thấy hoặc không tồn tại"));
        return postConverter.convertPostToPostDTO(p);
    }

    @Override
    public PostDetailDTO getAPostDetail(Long id) {
        Post post = postRepository.findById(id).get();
        return postConverter.convertPostToPostDetailDTO(post);
    }

    @Override
    public void createNewBlogPost(CreatePostRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User author = userRepository.findByUsername(username);
        Post post = postConverter.convertCreatePostRequestToEntity(request);
        post.setAuthorUser(author);
        postRepository.save(post);
    }



}
