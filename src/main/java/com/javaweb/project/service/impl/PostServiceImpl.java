package com.javaweb.project.service.impl;

import com.javaweb.project.converter.PostConverter;
import com.javaweb.project.dto.request.CreatePostRequest;
import com.javaweb.project.dto.response.PostDTO;
import com.javaweb.project.dto.request.UpdatePostRequest;
import com.javaweb.project.dto.response.PostDetailDTO;
import com.javaweb.project.entity.Category;
import com.javaweb.project.entity.Post;
import com.javaweb.project.entity.Tag;
import com.javaweb.project.entity.User;
import com.javaweb.project.enums.PostStatus;
import com.javaweb.project.repository.CategoryRepository;
import com.javaweb.project.repository.PostRepository;
import com.javaweb.project.repository.TagRepository;
import com.javaweb.project.repository.UserRepository;
import com.javaweb.project.service.PostService;
import com.javaweb.project.utils.SlugUtils;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.net.CacheRequest;
import java.util.*;

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

        post.setSlug(generateSlugPost(request.getTitle()));
        post.setCategory(checkCategory(request.getCategoryName()));
        post.setTags(checkTag(request.getTagNameList()));

        post.setViewCount(1);
        post.setStatus(PostStatus.published);
        post.setAuthorUser(author);
        postRepository.save(post);
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


    private Set<Tag> checkTag(List<String> tagNameList) {
        Set<Tag> tags = new HashSet<>();
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
