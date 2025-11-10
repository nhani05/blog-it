package com.javaweb.project.repository.custom;

import com.javaweb.project.entity.Post;

import java.util.List;

public interface PostCustom {
    public List<Post> findPosts();
//    public List<Post> findPostsByTitleOrAuthor(String title, String authorName);
}
