package com.javaweb.project.controller;

import com.javaweb.project.dto.response.PostDTO;
import com.javaweb.project.dto.response.TagDTO;
import com.javaweb.project.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public ResponseEntity<List<TagDTO>> getAllTags() {
        List<TagDTO> tags = tagService.getAllTags();
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/tag/search")
    public ResponseEntity<List<PostDTO>> getPostsByTagSlug(@RequestParam("slug") String slug) {
        List<PostDTO> posts = tagService.getPostsBySlug(slug);
        return ResponseEntity.ok(posts);
    }
}
