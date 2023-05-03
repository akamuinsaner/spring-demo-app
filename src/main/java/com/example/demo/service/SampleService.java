package com.example.demo.service;

import com.example.demo.model.sample.*;
import org.springframework.stereotype.Service;

@Service
public interface SampleService {

    public Sample finsSampleById(Long id);

    public Sample findUnFinishedSample(Long userId);

    public Sample updateSampleWithName(SampleName sampleName);

    public Sample updateSampleWithPrice(SamplePrice samplePrice);

    public Sample updateSampleWithService(SampleServiceModel sampleServiceModel);

    public Sample updateSampleWithOther(SampleOther sampleOther);
}
