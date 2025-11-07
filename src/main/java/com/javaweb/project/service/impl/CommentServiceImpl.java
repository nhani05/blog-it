package com.javaweb.project.service.impl;

import com.javaweb.project.converter.CommentConverter;
import com.javaweb.project.dto.request.CommentRequestDTO;
import com.javaweb.project.entity.Comment;
import com.javaweb.project.entity.Post;
import com.javaweb.project.entity.User;
import com.javaweb.project.repository.CommentRepository;
import com.javaweb.project.repository.PostRepository;
import com.javaweb.project.service.CommentService;
import com.javaweb.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentConverter commentConverter;

    @Override
    public void addCommentToPost(Long postId, CommentRequestDTO commentRequestDTO) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("POST DOES NOT EXIST"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userService.findByUsername(username);
        Comment comment = commentConverter.covertCommentDTOToComment(commentRequestDTO);
        user.getComments().add(comment);
        post.getComments().add(comment);
        comment.setPost(post);
        comment.setUser(user);
        commentRepository.save(comment);
    }

    @Override
    public void editComment(Long id, CommentRequestDTO commentRequestDTO) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("COMMENT DOES NOT EXIST"));
        comment.setContent(commentRequestDTO.getContent());
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }


}
