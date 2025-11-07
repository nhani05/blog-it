package com.javaweb.project.service;

import com.javaweb.project.dto.request.CommentRequestDTO;

public interface CommentService {
    public void addCommentToPost(Long postId, CommentRequestDTO commentRequestDTO);
    public void editComment(Long id, CommentRequestDTO commentRequestDTO);
    public void deleteComment(Long id);
}
