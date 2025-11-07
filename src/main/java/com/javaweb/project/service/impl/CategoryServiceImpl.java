package com.javaweb.project.service.impl;

import com.javaweb.project.entity.Category;
import com.javaweb.project.repository.CategoryRepository;
import com.javaweb.project.service.CategoryService;
import com.javaweb.project.utils.SlugUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category addCategoryToPost(String categoryName) {
        String categorySlug = SlugUtils.toSlug(categoryName);
        if(categoryRepository.existsBySlug(categorySlug)) {
            return categoryRepository.findBySlug(categorySlug);
        }
        Category category = new Category();
        category.setSlug(categorySlug);
        category.setName(categoryName);
        categoryRepository.save(category);
        return category;
    }
}
