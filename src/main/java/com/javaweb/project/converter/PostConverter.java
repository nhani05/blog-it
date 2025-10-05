package com.javaweb.project.converter;

import org.springframework.stereotype.Component;

import com.javaweb.project.dto.PostDTO;
import com.javaweb.project.entity.Post;
import com.javaweb.project.entity.User;

@Component
public class PostConverter {
    public PostDTO convertToDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        User author = post.getAuthorUser();
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        postDTO.setExcerpt(post.getExcerpt());
        postDTO.setAuthor(author.getDisplayName());
        postDTO.setCreatedAt(post.getPublishedAt().toString());
        postDTO.setUpdatedAt(post.getUpdatedAt().toString());
        return postDTO;
    }
}
