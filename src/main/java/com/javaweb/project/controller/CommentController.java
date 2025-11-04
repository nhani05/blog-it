package com.javaweb.project.controller;

import com.javaweb.project.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    public ResponseEntity<Object> writeComment() {

        return null;
    }

    public ResponseEntity<Object> updateComment() {

        return null;
    }

    public ResponseEntity<Object> deleteComment() {
        return null;
    }

}
