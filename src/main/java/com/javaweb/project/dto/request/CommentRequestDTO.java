package com.javaweb.project.dto.request;

import com.javaweb.project.enums.CommentStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CommentRequestDTO {
    private String content;
}
