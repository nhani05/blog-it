package com.javaweb.project.dto.response;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentDTO {
    private Long id;
    private Long userId;
    private String contentComment;
    private String displayName;
}
