package com.javaweb.project.dto.request;

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
    private PostStatus status = PostStatus.draft;
}
