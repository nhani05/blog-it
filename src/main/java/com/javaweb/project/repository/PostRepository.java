package com.javaweb.project.repository;

import com.javaweb.project.entity.Post;
import com.javaweb.project.repository.custom.PostCustom;
import javafx.geometry.Pos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, PostCustom {
//    public List<Post> findByTitleContainingIgnoreCaseOrAuthorUserDisplayNameContainingIgnoreCase(String title);
}
