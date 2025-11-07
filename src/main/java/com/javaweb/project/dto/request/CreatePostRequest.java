package com.javaweb.project.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CreatePostRequest extends PostRequest {
    private LocalDateTime createdAt = LocalDateTime.now();
}
