package com.javaweb.project.repository;

import com.javaweb.project.entity.Post;
import com.javaweb.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);

    boolean existsByUsername(String username);

    public List<Post> findPostByUsername(String username);
}
