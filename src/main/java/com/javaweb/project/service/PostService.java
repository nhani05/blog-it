package com.javaweb.project.service;

import com.javaweb.project.entity.Post;
import com.javaweb.project.model.BlogDTO;

import java.util.List;
import java.util.Set;

public interface PostService {
    public Set<BlogDTO> getAllBlogs();
}
