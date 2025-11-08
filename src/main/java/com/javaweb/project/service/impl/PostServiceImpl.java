package com.javaweb.project.service.impl;

import com.javaweb.project.converter.PostConverter;
import com.javaweb.project.dto.request.CreatePostRequest;
import com.javaweb.project.dto.request.UpdatePostRequest;
import com.javaweb.project.dto.response.PostDTO;
import com.javaweb.project.dto.response.PostDetailDTO;
import com.javaweb.project.entity.Post;
import com.javaweb.project.entity.User;
import com.javaweb.project.repository.PostRepository;
import com.javaweb.project.service.CategoryService;
import com.javaweb.project.service.PostService;
import com.javaweb.project.service.TagService;
import com.javaweb.project.service.UserService;
import com.javaweb.project.utils.SlugUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostConverter postConverter;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Override
    public List<PostDTO> findAllBlogs() {
        List<PostDTO> blogPosts = new ArrayList<>();
        List<Post> posts = postRepository.findAll();
        for (Post p : posts) {
            blogPosts.add(postConverter.convertPostToPostDTO(p));
        }
        return blogPosts;
    }

    @Override
    public List<PostDTO> findPostsByTitle(String title) {
        List<PostDTO> blogPosts = new ArrayList<>();
        List<Post> posts = postRepository.findByTitleIsContainingIgnoreCase(title);
        for(Post p : posts) {
            blogPosts.add(postConverter.convertPostToPostDTO(p));
        }
        return blogPosts;
    }

    @Override
    public void updateBlogPost(Long id, UpdatePostRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("NOT FOUND"));
        post = postConverter.convertUpdatePostRequestToEntity(request, post);
        post.setSlug(generateSlugPost(request.getTitle()));
        if(request.getCategoryName() != null || !request.getCategoryName().trim().isEmpty()) {
            post.setCategory(categoryService.addCategoryToPost(request.getCategoryName()));
        }
        if(request.getTagNameList() != null) {
            post.setTags(tagService.addTagToPost(request.getTagNameList()));
        }
        postRepository.save(post);
    }

    @Override
    public void deleteBlogPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("NOT FOUND"));
        postRepository.deleteById(id);
    }

    @Override
    public PostDTO findBlogById(Long id) {
        Post p = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("NOT FOUND"));
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
        User author = userService.findByUsername(username);
        Post post = postConverter.convertCreatePostRequestToEntity(request);
        post.setSlug(generateSlugPost(request.getTitle()));


        if(request.getCategoryName() != null || !request.getCategoryName().trim().isEmpty()) {
            post.setCategory(categoryService.addCategoryToPost(request.getCategoryName()));
        }
        if(request.getTagNameList() != null) {
            post.setTags(tagService.addTagToPost(request.getTagNameList()));
        }
//        post.setCategory(categoryService.addCategoryToPost(request.getCategoryName()));
//        post.setTags(tagService.addTagToPost(request.getTagNameList()));

        post.setViewCount(1);
        post.setAuthorUser(author);
        postRepository.save(post);
    }

    @Override
    public List<PostDTO> getAllMyBlog() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<PostDTO> postDTOs = new ArrayList<>();
        User user = userService.findByUsername(username);
        for(Post p : user.getPosts()) {
            postDTOs.add(postConverter.convertPostToPostDTO(p));
        }
        return postDTOs;
    }

    private String generateSlugPost(String postTitle) {
        String postSlug = SlugUtils.toSlug(postTitle);
        if(postRepository.existsBySlugIgnoreCase(postSlug)) {
            postSlug = SlugUtils.toUniqueSlug(postSlug);
        }
        return postSlug;
    }

}
