package com.javaweb.project.service;


import com.javaweb.project.dto.request.CreatePostRequest;
import com.javaweb.project.dto.response.PostDTO;
import com.javaweb.project.dto.request.UpdatePostRequest;
import com.javaweb.project.dto.response.PostDetailDTO;


import java.util.Set;

public interface PostService {
    public Set<PostDTO> findAllBlogs();
    public Set<PostDTO> findBlogsByTitleOrAuthorName(String title, String authorName);
    public void updateBlogPost(Long id, UpdatePostRequest request);
    public void deleteBlogPostById(Long id);

    public PostDetailDTO getAPostDetail(Long id);
    public void createNewBlogPost(CreatePostRequest request);
}
