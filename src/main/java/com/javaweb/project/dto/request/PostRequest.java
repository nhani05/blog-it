package com.javaweb.project.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class PostRequest {
    private String title;
    private  String content;
    private String excerpt;
    private  String slug;

    private List<String> tagNameList;
    private String categoryName;
    private String introduction;
    private String contentDetail;
    private String endContent;
    private String img;
    private String link;

}
