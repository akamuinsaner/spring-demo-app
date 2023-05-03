package com.example.demo.model.sample;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "sample_label")
public class SampleLabel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "parent_id")
    private Long parentId;
}
