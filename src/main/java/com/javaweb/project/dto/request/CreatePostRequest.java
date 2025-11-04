package com.javaweb.project.dto.request;

import java.time.LocalDateTime;

import com.javaweb.project.dto.response.PostDTO;
import com.javaweb.project.dto.response.PostDetailDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePostRequest extends PostRequest {
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    private String introduction;
    private String contentDetail;
    private String endContent;
    private String img;
    private String link;
}
