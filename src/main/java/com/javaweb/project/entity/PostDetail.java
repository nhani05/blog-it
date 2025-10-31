package com.javaweb.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
@Entity
@Table(name = "post_details")
public class PostDetail {

    @Id
    @Column(name = "post_id")
    private Long id;

    @Column(name = "post_introduction", columnDefinition = "TEXT")
    private String introduction;

    @Column(name = "post_content", nullable = false)
    private String content;

    @Column(name = "post_end_content", nullable = false)
    private String endContent;

    @Column(name = "post_img", nullable = false)
    private String img;

    @Column(name = "post_link", nullable = false)
    private String link;

    @OneToOne(mappedBy = "postDetail")
    private Post post;
}
