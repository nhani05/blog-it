package com.javaweb.project.repository.impl;

import com.javaweb.project.entity.Post;
import com.javaweb.project.repository.PostRepository;
import com.javaweb.project.repository.custom.PostCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PostRepositoryImpl implements PostCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Post> findPosts() {
        // Implementation of custom method to find posts
        String jpql = "select p from Post p";
        Query query = entityManager.createQuery(jpql, Post.class);
        return query.getResultList();

    }
}
