package com.javaweb.project.converter;

import com.javaweb.project.dto.request.CommentRequestDTO;
import com.javaweb.project.dto.response.CommentDTO;
import com.javaweb.project.dto.response.UserDTO;
import com.javaweb.project.entity.Comment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommentConverter {

    @Autowired
    private ModelMapper modelMapper;

    public CommentDTO covertCommentToCommentDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        UserDTO userDTO = modelMapper.map(comment.getUser(), UserDTO.class);
        commentDTO.setUserDTO(userDTO);
        commentDTO.setContentComment(comment.getContent());
        return commentDTO;
    }

    public Comment covertCommentDTOToComment(CommentRequestDTO commentRequestDTO) {
        Comment comment =  modelMapper.map(commentRequestDTO, Comment.class);
        comment.setCreatedAt(LocalDateTime.now());
        return comment;
    }
}
