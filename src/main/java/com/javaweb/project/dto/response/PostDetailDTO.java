package com.javaweb.project.dto.response;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostDetailDTO extends PostDTO{
    private String introduction;
    private  String contentDetail;
    private  String endContent;
    private String img;
    private String link;
}
