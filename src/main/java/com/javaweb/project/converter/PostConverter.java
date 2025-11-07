package com.javaweb.project.converter;

import com.javaweb.project.dto.request.CreatePostRequest;
import com.javaweb.project.dto.request.UpdatePostRequest;
import com.javaweb.project.dto.response.CommentDTO;
import com.javaweb.project.dto.response.PostDTO;
import com.javaweb.project.dto.response.PostDetailDTO;
import com.javaweb.project.dto.response.TagDTO;
import com.javaweb.project.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class PostConverter {

    @Autowired
    private ModelMapper modelMapper;

    public PostDetailDTO convertPostToPostDetailDTO(Post post) {
        PostDetail postDetail = post.getPostDetail();
        PostDTO postDTO = modelMapper.map(post, PostDTO.class);
        PostDetailDTO postDetailDTO = modelMapper.map(postDetail, PostDetailDTO.class);

        User author = post.getAuthorUser();
        postDetailDTO.setTitle(post.getTitle());
        postDetailDTO.setAuthorName(author.getDisplayName());
        postDetailDTO.setContent(post.getContent());
        postDetailDTO.setExcerpt(post.getExcerpt());
        postDetailDTO.setSlug(post.getSlug());
        postDetailDTO.setPublishedAt(post.getPublishedAt());
        postDetailDTO.setUpdatedAt(post.getUpdatedAt());

        postDetailDTO.setViewCount(post.getViewCount());
        postDetailDTO.setCategoryName(post.getCategory().getName());

        CommentConverter commentConverter = new CommentConverter();
        List<CommentDTO> commentDTOs = new ArrayList<>();
        for (Comment comment : post.getComments()) {
            commentDTOs.add(commentConverter.covertCommentToCommentDTO(comment));
        }
        postDTO.setCommentDTOs(commentDTOs);

        List<TagDTO> tagDTOs = new ArrayList<>();
        for(Tag tag : post.getTags()) {
            TagDTO tagDTO = modelMapper.map(tag, TagDTO.class);
            tagDTOs.add(tagDTO);
        }
        postDetailDTO.setTagDTOs(tagDTOs);
        return postDetailDTO;
    }

    public Post convertUpdatePostRequestToEntity(UpdatePostRequest request, Post post) {
        PostDetail postDetail = post.getPostDetail();
        postDetail.setIntroduction(request.getIntroduction());
        postDetail.setContent(request.getContent());
        postDetail.setEndContent(request.getEndContent());
        postDetail.setImg(request.getImg());
        postDetail.setLink(request.getLink());

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setExcerpt(request.getExcerpt());
        post.setSlug(request.getSlug());
        post.setUpdatedAt(request.getUpdatedAt());

        postDetail.setPost(post);
        post.setPostDetail(postDetail);
        return post;
    }

    public Post convertCreatePostRequestToEntity(CreatePostRequest request) {
        PostDetail postDetail = modelMapper.map(request, PostDetail.class);
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setExcerpt(request.getExcerpt());
        post.setSlug(request.getSlug());
        post.setPublishedAt(request.getCreatedAt());
        post.setUpdatedAt(request.getUpdatedAt());
        post.setViewCount(0);

        postDetail.setPost(post);
        post.setPostDetail(postDetail);
        return post;
    }

    public PostDTO convertPostToPostDTO(Post post) {
        PostDTO postDTO = modelMapper.map(post, PostDTO.class);
        User author = post.getAuthorUser();
        postDTO.setAuthorName(author.getDisplayName());
        postDTO.setCategoryName(post.getCategory().getName());

        CommentConverter commentConverter = new CommentConverter();
        List<CommentDTO> commentDTOs = new ArrayList<>();
        for (Comment comment : post.getComments()) {
            commentDTOs.add(commentConverter.covertCommentToCommentDTO(comment));
        }
        postDTO.setCommentDTOs(commentDTOs);


        List<TagDTO> tagDTOs = new ArrayList<>();
        for(Tag tag : post.getTags()) {
            TagDTO tagDTO = modelMapper.map(tag, TagDTO.class);
            tagDTOs.add(tagDTO);
        }
        postDTO.setTagDTOs(tagDTOs);
        return postDTO;
    }

}
