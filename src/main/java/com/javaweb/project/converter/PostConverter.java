package com.javaweb.project.converter;

import com.javaweb.project.dto.PostDTO;
import com.javaweb.project.dto.request.UpdatePostRequest;
import com.javaweb.project.entity.Post;
import com.javaweb.project.entity.User;
import org.springframework.stereotype.Component;

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

    public Post convertToEntity(UpdatePostRequest request, Post post) {
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setExcerpt(request.getExcerpt());
        post.setSlug(request.getSlug());
        post.setUpdatedAt(request.getUpdatedAt());
        post.setStatus(request.getStatus());
        return post;
    }
}
