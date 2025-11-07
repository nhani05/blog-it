package com.javaweb.project.service.impl;

import com.javaweb.project.converter.PostConverter;
import com.javaweb.project.dto.response.PostDTO;
import com.javaweb.project.dto.response.TagDTO;
import com.javaweb.project.entity.Post;
import com.javaweb.project.entity.Tag;
import com.javaweb.project.repository.TagRepository;
import com.javaweb.project.service.TagService;
import com.javaweb.project.utils.SlugUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TagServiceImpl implements TagService {


    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PostConverter postConverter;

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
    public List<TagDTO> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        List<TagDTO> tagDTOs = new ArrayList<>();
        for(Tag tag : tags) {
            TagDTO tagDTO = modelMapper.map(tag, TagDTO.class);
            tagDTOs.add(tagDTO);
        }
        return tagDTOs;
    }

    @Override
    public List<PostDTO> getPostsBySlug(String slug) {
        Tag tag =  tagRepository.findBySlug(slug);
        if(tag == null) {
            throw new NoSuchElementException("Tag not found with slug: " + slug);
        }
        List<Post> posts = tag.getPosts();
        List<PostDTO> postDTOs = new ArrayList<>();
        for(Post post : posts) {
            postDTOs.add(postConverter.convertPostToPostDTO(post));
        }
        return postDTOs;
    }
}
