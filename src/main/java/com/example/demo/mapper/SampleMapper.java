package com.example.demo.mapper;

import com.example.demo.model.sample.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SampleMapper {

    Sample findSampleById(Long id);

    Long insertSample(Sample sample);

    Long updateSample(Sample sample);

    Long insertSampleName(SampleName sampleName);

    Long updateSampleName(SampleName sampleName);

    Long insertSamplePrice(SamplePrice samplePrice);

    Long updateSamplePrice(SamplePrice samplePrice);

    Long insertSampleService(SampleServiceModel sampleService);

    Long updateSampleService(SampleServiceModel sampleService);

    Long insertSampleOther(SampleOther sampleOther);

    Long updateSampleOther(SampleOther sampleOther);

    Sample findUnFinishedSample(Long userId);

    void insertSampleLabels(Long nameId, List<Long> labelIds);

    void deleteSampleLabels(Long nameId);
}