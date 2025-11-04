package com.javaweb.project.dto.response;


import com.javaweb.project.entity.Post;
import com.javaweb.project.enums.PostStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {
    private String title;
    private String author;
    private String excerpt;
    private String content;
    private String slug;
}
