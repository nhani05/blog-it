package com.javaweb.project.repository;

import com.javaweb.project.entity.Post;
import com.javaweb.project.repository.custom.PostCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PostRepository extends JpaRepository<Post, Long>, PostCustom {
    public void deleteById(Long id);
    public boolean existsBySlugIgnoreCase(String slug);
//    public List<Post> findByTitleContainingIgnoreCaseOrAuthorUserDisplayNameContainingIgnoreCase(String title);
}
