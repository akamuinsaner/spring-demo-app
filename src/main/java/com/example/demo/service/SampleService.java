package com.example.demo.service;

import com.example.demo.model.sample.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SampleService {

    public Sample finsSampleById(Long id);

    public Sample findUnFinishedSample(Long userId);

    public Sample updateSampleWithName(SampleName sampleName);

    public Sample updateSampleWithPrice(SamplePrice samplePrice);

    public Sample updateSampleWithService(SampleServiceModel sampleServiceModel);

    public Sample updateSampleWithOther(SampleOther sampleOther);

    public Long insertSampleServiceTemplate(SampleServiceModel sampleServiceModel);

    public Long updateSampleServiceTemplate(SampleServiceModel sampleServiceModel);

    public List<SampleServiceModel> findSampleServiceTemplateByUserId(Long userId);

    public void deleteSampleServiceTemplateById(Long templateId);

    public void finishSampleInfo(Long sampleId) throws Exception;
}
