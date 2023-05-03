package com.example.demo.mapper;

import com.example.demo.model.sample.SampleLabel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SampleLabelMapper {

    SampleLabel findSampleLabelById(Long labelId);

    List<SampleLabel> findSampleLabels();
}
