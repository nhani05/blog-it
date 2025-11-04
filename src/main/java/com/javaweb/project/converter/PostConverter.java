package com.javaweb.project.converter;

import com.javaweb.project.dto.request.CreatePostRequest;
import com.javaweb.project.dto.response.PostDTO;
import com.javaweb.project.dto.request.UpdatePostRequest;
import com.javaweb.project.dto.response.PostDetailDTO;
import com.javaweb.project.entity.Post;
import com.javaweb.project.entity.PostDetail;
import com.javaweb.project.entity.User;
import com.javaweb.project.enums.PostStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PostConverter {

    @Autowired
    private ModelMapper modelMapper;
    public PostDetailDTO convertPostToPostDetailDTO(Post post) {
        PostDetail postDetail = post.getPostDetail();
        PostDetailDTO postDetailDTO = modelMapper.map(postDetail, PostDetailDTO.class);

        User author = post.getAuthorUser();
        postDetailDTO.setTitle(post.getTitle());
        postDetailDTO.setContent(post.getContent());
        postDetailDTO.setExcerpt(post.getExcerpt());
        postDetailDTO.setAuthor(author.getDisplayName());
        return postDetailDTO;
    }

    public Post convertToEntity(UpdatePostRequest request, Post post) {
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setExcerpt(request.getExcerpt());
        post.setSlug(request.getSlug());
        post.setUpdatedAt(LocalDateTime.now());
        post.setStatus(request.getStatus());
        return post;
    }

    public Post convertPostDetailDTOToEntity(CreatePostRequest request) {
        PostDetail postDetail = modelMapper.map(request, PostDetail.class);
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setExcerpt(request.getExcerpt());
        post.setSlug(request.getSlug());
        post.setPublishedAt(request.getCreatedAt());
        post.setUpdatedAt(request.getUpdatedAt());
        post.setStatus(PostStatus.draft);
        postDetail.setPost(post);
        post.setPostDetail(postDetail);
        return post;
    }

}
