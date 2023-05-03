package com.example.demo.model.sample;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class SampleBasic {

    private Long id;

    private Long sampleId;


    private Long userId;
}
