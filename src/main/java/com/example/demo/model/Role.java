package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "chinese_name")
    private String chineseName;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnoreProperties({"roles"})
    private Set<User> users = new HashSet<>();

    @ManyToMany(targetEntity = Permission.class, fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties({"roles"})
    private Set<User> permissions = new HashSet<>();
}
