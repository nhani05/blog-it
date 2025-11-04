package com.javaweb.project.dto.response;


import com.javaweb.project.entity.*;
import com.javaweb.project.enums.PostStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class PostDTO {
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

    private Set<CommentDTO> commentDTOs = new HashSet<>();

    private Set<TagDTO> tagDTOs = new HashSet<>();

}
