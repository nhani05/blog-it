package com.javaweb.project.entity;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private  String name;

    @Column(name = "code")
    private  String code;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}
