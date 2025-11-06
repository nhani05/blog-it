package com.javaweb.project.dto.response;


import com.javaweb.project.entity.*;
import com.javaweb.project.enums.PostStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private PostStatus status = PostStatus.draft;
    private Integer viewCount;
    private String categoryName;
    private List<CommentDTO> commentDTOs = new ArrayList<>();
    private List<TagDTO> tagDTOs = new ArrayList<>();
}
