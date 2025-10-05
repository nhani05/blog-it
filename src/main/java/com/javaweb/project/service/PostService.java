package com.javaweb.project.service;

import com.javaweb.project.dto.PostDTO;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.util.Set;

public interface PostService {
    public Set<PostDTO> findAllBlogs();
    public Set<PostDTO> findBlogsByTitleOrAuthorName(String title, String authorName);
}
