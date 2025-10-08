package com.javaweb.project.service;


import com.javaweb.project.dto.PostDTO;
import com.javaweb.project.dto.request.UpdatePostRequest;


import java.util.Set;

public interface PostService {
    public Set<PostDTO> findAllBlogs();
    public Set<PostDTO> findBlogsByTitleOrAuthorName(String title, String authorName);
    public void updateBlogPost(Long id, UpdatePostRequest request);
//    public void deleteBlogPostById(Long id);
}
