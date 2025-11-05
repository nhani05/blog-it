package com.javaweb.project.repository;

import com.javaweb.project.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    public boolean existsBySlug(String slug);

    public Tag findBySlug(String slug);
}
