package com.javaweb.project.model.request;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javaweb.project.enums.PostStatus;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PostRequest {
    private String title;
    private  String content;
    private String excerpt;
    private  String slug;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    private PostStatus status = PostStatus.draft;
}
