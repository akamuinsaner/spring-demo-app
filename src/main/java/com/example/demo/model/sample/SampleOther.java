package com.example.demo.model.sample;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sample_other")
@Data
public class SampleOther {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sample_id")
    private Long sampleId;

    @Column(name = "is_public")
    private Boolean isPublic = false;

    @Column(name = "tip")
    private String tip;

    @Transient
    private Long userId;
}
