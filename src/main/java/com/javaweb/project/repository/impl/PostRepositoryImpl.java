package com.javaweb.project.repository.impl;

import com.javaweb.project.entity.Post;
import com.javaweb.project.repository.PostRepository;
import com.javaweb.project.repository.custom.PostCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

@Repository
public class PostRepositoryImpl implements PostCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Post> findPosts() {
        // Implementation of custom method to find posts
        // Implementation of custom method to find posts
        String jpql = "select p from Post p";
        Query query = entityManager.createQuery(jpql, Post.class);
        return query.getResultList();
    }

    private static String addConditions(StringBuilder sql, String title, String authorName) {
        if(!title.trim().isEmpty())
            sql.append("where p.title like '%" + title + "%' ");
        if(authorName.trim().isEmpty())
            sql.append("or u.display_name like '%" + authorName + "%' ");
        return sql.toString();
    }

    @Override
    public List<Post> findPostsByTitleOrAuthor(String title, String authorName) {
        StringBuilder sql = new StringBuilder("select * from posts p ");
        sql.append("inner join users u on p.author_id ");
        Query query = entityManager.createNativeQuery(addConditions(sql, title, authorName), Post.class);
        return query.getResultList();
    }
}
