package com.javaweb.project.service;

import com.javaweb.project.entity.Category;
import com.javaweb.project.utils.SlugUtils;

public interface CategoryService {
    public Category addCategoryToPost(String categoryName);
}
