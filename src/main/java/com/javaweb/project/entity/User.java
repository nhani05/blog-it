package com.javaweb.project.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String password;



    @Column(name= "display_name")
    private String displayName;

    @Column(name= "is_admin", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isAdmin = false;

    @Column(name= "created_at", updatable = false)
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    @OneToMany(mappedBy = "authorUser", cascade = CascadeType.ALL)
    private Set<Post> posts = new HashSet<>();
}
