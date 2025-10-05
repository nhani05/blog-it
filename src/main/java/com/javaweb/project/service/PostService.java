package com.javaweb.project.service;

import com.javaweb.project.dto.BlogDTO;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.util.Set;

public interface PostService {
    public Set<BlogDTO> findAllBlogs();
    public Set<BlogDTO> findBlogsByTitleOrAuthorName(String title, String authorName);
}
