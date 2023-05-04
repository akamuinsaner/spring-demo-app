package com.example.demo.model.sample;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sample_service")
@Data
public class SampleServiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private Long sampleId;

    @Column(name = "provide_models")
    private Boolean provideModels = false;

    @Column(name = "model_count")
    private String modelCount;

    @Column(name = "model_count_custom")
    private String modelCountCustom;

    @Column(name = "film_count", nullable = false)
    private String filmCount;

    @Column(name = "film_all_send")
    private Boolean filmAllSend = false;

    @Column(name = "shoot_duration", nullable = false)
    private String shootDuration;

    @Column(name = "shoot_duration_custom", nullable = false)
    private String shootDurationCustom;

    @Column(name = "finishing_quantity", nullable = false)
    private String finishingQuantity;

    @Column(name = "shoot_scene_indoor_count")
    private Integer shootSceneIndoorCount;

    @Column(name = "shoot_scene_outdoor_count")
    private Integer shootSceneOutdoorCount;

    @Column(name = "custom_service_detail")
    private String customServiceDetail;

    @Transient
    private Long userId;
}
