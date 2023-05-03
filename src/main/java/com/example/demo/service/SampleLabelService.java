package com.example.demo.service;

import com.example.demo.mapper.SampleLabelMapper;
import com.example.demo.model.sample.SampleLabel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@Transactional
public class SampleLabelService {

    @Autowired
    private SampleLabelMapper sampleLabelMapper;

    public SampleLabel findSampleLabelById(Long labelId) {
        return sampleLabelMapper.findSampleLabelById(labelId);
    }

    public List<SampleLabel> findSampleLabels() {
        return sampleLabelMapper.findSampleLabels();
    }
}
