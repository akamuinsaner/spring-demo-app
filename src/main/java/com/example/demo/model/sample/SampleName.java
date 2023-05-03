package com.example.demo.model.sample;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "sample_name")
@Data
public class SampleName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sample_id")
    private Long sampleId;

    @Column(name = "label_id")
    private Long labelId;

    @Transient
    private List<Long> labelIds;

    @Transient
    private List<SampleLabel> labelList;

    @Transient
    private Long userId;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "cover_imgs")
    private String coverImgs;

    @Column(name = "detail_imgs")
    private String detailImgs;
}
