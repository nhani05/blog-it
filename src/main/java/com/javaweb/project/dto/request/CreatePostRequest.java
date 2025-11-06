package com.javaweb.project.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CreatePostRequest extends PostRequest {
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    private List<String> tagNameList;
    private String categoryName;
    private String introduction;
    private String contentDetail;
    private String endContent;
    private String img;
    private String link;
}
