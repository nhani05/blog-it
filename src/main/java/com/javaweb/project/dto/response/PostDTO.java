package com.javaweb.project.dto.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {
    private String title;
    private String author;
    private String createdAt;
    private String excerpt;
    private String content;
    private String updatedAt;
}
