package com.javaweb.project.dto.response;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostDTO {
    private Long id;
    private String title;
    private String authorName;
    private String excerpt;
    private String content;
    private String slug;
    private LocalDateTime publishedAt;
    private LocalDateTime updatedAt;

    private Integer viewCount;
    private String categoryName;
    private List<CommentDTO> commentDTOs = new ArrayList<>();
    private List<TagDTO> tagDTOs = new ArrayList<>();
}
