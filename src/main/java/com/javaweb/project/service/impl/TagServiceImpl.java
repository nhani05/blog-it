package com.javaweb.project.service.impl;

import com.javaweb.project.entity.Tag;
import com.javaweb.project.repository.TagRepository;
import com.javaweb.project.service.TagService;
import com.javaweb.project.utils.SlugUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {


    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Tag> addTagToPost(List<String> tagNameList) {
        List<Tag> tags = new ArrayList<>();
        for(String tagName : tagNameList) {
            String tagSlug = SlugUtils.toSlug(tagName);
            if(tagRepository.existsBySlug(tagSlug)) {
                tags.add(tagRepository.findBySlug(tagSlug));
            } else {
                Tag tag = new Tag();
                tag.setName(tagName);
                tag.setSlug(tagSlug);
                tagRepository.save(tag);
                tags.add(tag);
            }
        }
        return tags;
    }

    @Override
    public List<Tag> getAllTags() {

        return null;
    }
}
