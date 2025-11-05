package com.javaweb.project.dto.request;

import com.javaweb.project.enums.CommentStatus;

import java.time.LocalDateTime;

public class CommentRequestDTO {
    private Long postId;
    private String content;

    private LocalDateTime createdAt;

    private CommentStatus status = CommentStatus.pending;
}
