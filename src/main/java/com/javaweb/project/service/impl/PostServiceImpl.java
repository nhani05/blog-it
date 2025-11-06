package com.javaweb.project.service.impl;

import com.javaweb.project.converter.PostConverter;
import com.javaweb.project.dto.request.CreatePostRequest;
import com.javaweb.project.dto.request.UpdatePostRequest;
import com.javaweb.project.dto.response.PostDTO;
import com.javaweb.project.dto.response.PostDetailDTO;
import com.javaweb.project.entity.Category;
import com.javaweb.project.entity.Post;
import com.javaweb.project.entity.Tag;
import com.javaweb.project.entity.User;
import com.javaweb.project.repository.CategoryRepository;
import com.javaweb.project.repository.PostRepository;
import com.javaweb.project.repository.TagRepository;
import com.javaweb.project.repository.UserRepository;
import com.javaweb.project.service.PostService;
import com.javaweb.project.utils.SlugUtils;
import org.modelmapper.ModelMapper;
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
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostConverter postConverter;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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
    public List<PostDTO> findBlogsByTitleOrAuthorName(String title, String authorName) {
        List<PostDTO> blogPosts = new ArrayList<>();
        List<Post> posts = postRepository.findPostsByTitleOrAuthor(title, authorName);
        for(Post p : posts) {
            blogPosts.add(postConverter.convertPostToPostDTO(p));
        }
        return blogPosts;
    }

    @Override
    public void updateBlogPost(Long id, UpdatePostRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("NOT FOUND"));
        postRepository.save(postConverter.convertUpdatePostRequestToEntity(request, post));
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
        User author = userRepository.findByUsername(username);
        Post post = postConverter.convertCreatePostRequestToEntity(request);

        post.setSlug(generateSlugPost(request.getTitle()));
        post.setCategory(checkCategory(request.getCategoryName()));
        post.setTags(checkTag(request.getTagNameList()));

        post.setViewCount(1);
        post.setAuthorUser(author);
        postRepository.save(post);
    }

    @Override
    public List<PostDTO> getAllMyBlog() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<PostDTO> postDTOs = new ArrayList<>();
        User user = userRepository.findByUsername(username);
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

    private Category checkCategory(String categoryName) {
        String categorySlug = SlugUtils.toSlug(categoryName);
        if(categoryRepository.existsBySlug(categorySlug)) {
            return categoryRepository.findBySlug(categorySlug);
        }
        Category category = new Category();
        category.setSlug(categorySlug);
        category.setName(categoryName);
        categoryRepository.save(category);
        return category;
    }


    private List<Tag> checkTag(List<String> tagNameList) {
        List<Tag> tags = new ArrayList<>();
        for(String tagName : tagNameList) {
            String tagSlug = SlugUtils.toSlug(tagName);
            if(tagRepository.existsBySlug(tagSlug)) {
                tags.add(tagRepository.findBySlug(tagSlug));
            } else {
                Tag tag = new Tag();
                tag.setName(tagName);
                tag.setSlug(tagSlug);
                tagRepository.save(tag);
                tags.add(tag);
            }
        }
        return tags;
    }

}
