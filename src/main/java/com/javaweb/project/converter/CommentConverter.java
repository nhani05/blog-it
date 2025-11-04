package com.javaweb.project.converter;

import com.javaweb.project.dto.response.CommentDTO;
import com.javaweb.project.entity.Comment;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CommentConverter {
    public CommentDTO covertCommentToCommentDTO(Comment comment) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setDisplayName(comment.getUser().getDisplayName());
            commentDTO.setContentComment(comment.getContent());
        return commentDTO;
    }
}
