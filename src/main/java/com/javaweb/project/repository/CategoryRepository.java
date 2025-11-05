package com.javaweb.project.repository;

import com.javaweb.project.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    public boolean existsBySlug(String slug);

    public Category findBySlug(String slug);
}
