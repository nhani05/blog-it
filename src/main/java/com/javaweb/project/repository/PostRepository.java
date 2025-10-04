package com.javaweb.project.repository;

import com.javaweb.project.entity.Post;
import com.javaweb.project.repository.custom.PostCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostCustom {
}
