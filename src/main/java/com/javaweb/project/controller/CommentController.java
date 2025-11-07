package com.javaweb.project.controller;

import com.javaweb.project.dto.request.CommentRequestDTO;
import com.javaweb.project.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping(value = "/post/{postId}/comment")
    public ResponseEntity<Object> writeComment(@PathVariable("postId") Long postId,
                                               @RequestBody CommentRequestDTO commentRequestDTO) {
        commentService.addCommentToPost(postId, commentRequestDTO);
        return new ResponseEntity<>("COMMENT IS CREATED SUCCESSFULLY", HttpStatus.OK);
    }

    @PutMapping(value = "/post/{postId}/comments/{id}")
    public ResponseEntity<Object> updateComment(@PathVariable("postId") Long postId,
                                                @PathVariable("id") Long id,
                                                @RequestBody CommentRequestDTO commentRequestDTO) {
        commentService.editComment(id, commentRequestDTO);
    return new ResponseEntity<>("COMMENT IS UPDATED SUCCESSFULLY", HttpStatus.OK);
    }

    @DeleteMapping(value = "/post/{postId}/comments/{id}")
    public ResponseEntity<Object> deleteComment(@PathVariable("postId") Long postId,
                                                @PathVariable("id") Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>("COMMENT IS DELETED SUCCESSFULLY", HttpStatus.OK);
    }

}
