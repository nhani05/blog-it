package com.javaweb.project.service;

import com.javaweb.project.entity.Tag;

import java.util.List;

public interface TagService {
    public List<Tag> addTagToPost(List<String> tagNameList);
    public List<Tag> getAllTags();
}
