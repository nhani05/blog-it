package com.javaweb.project.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
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
    private List<User> users = new ArrayList<>();
}
