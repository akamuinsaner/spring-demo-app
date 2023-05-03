package com.example.demo.model.sample;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sample_price")
@Data
public class SamplePrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sample_id")
    private Long sampleId;

    @Column(name = "price")
    private Integer price;

    @Column(name = "earnest")
    private Integer earnest;

    @Transient
    private Long userId;
}
