package com.javaweb.project.service.impl;

import com.javaweb.project.converter.CommentConverter;
import com.javaweb.project.dto.request.CommentRequestDTO;
import com.javaweb.project.entity.Comment;
import com.javaweb.project.entity.Post;
import com.javaweb.project.entity.User;
import com.javaweb.project.enums.CommentStatus;
import com.javaweb.project.repository.CommentRepository;
import com.javaweb.project.repository.PostRepository;
import com.javaweb.project.repository.UserRepository;
import com.javaweb.project.repository.custom.CommentCustom;
import com.javaweb.project.service.CommentService;
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
    private UserRepository userRepository;

    @Autowired
    private CommentConverter commentConverter;

    @Override
    public void addCommentToPost(Long postId, CommentRequestDTO commentRequestDTO) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("Bai dang khong ton tai"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        Comment comment = commentConverter.covertCommentDTOToComment(commentRequestDTO);
        user.getComments().add(comment);
        post.getComments().add(comment);
        comment.setPost(post);
        comment.setUser(user);
        comment.setStatus(CommentStatus.approved);
        commentRepository.save(comment);
    }
}
