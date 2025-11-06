package com.javaweb.project.dto.request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PostRequest {
    private String title;
    private  String content;
    private String excerpt;
    private  String slug;

}
