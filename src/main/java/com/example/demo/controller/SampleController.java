package com.example.demo.controller;

import com.example.demo.model.sample.*;
import com.example.demo.service.SampleLabelService;
import com.example.demo.service.SampleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sample")
public class SampleController {

    @Autowired
    private SampleServiceImpl sampleService;

    @Autowired
    private SampleLabelService sampleLabelService;

    @PostMapping("/findUnFinishedSample")
    public Sample findUnFinishedSample(@RequestBody Map<String, Long> map) {
        return sampleService.findUnFinishedSample(map.get("userId"));
    }

    @PostMapping("/updateWithName")
    public Sample updateSampleWithName(@RequestBody SampleName sampleName) {
        return sampleService.updateSampleWithName(sampleName);
    }

    @PostMapping("/updateWithPrice")
    public Sample updateSampleWithPrice(@RequestBody SamplePrice samplePrice) {
        return sampleService.updateSampleWithPrice(samplePrice);
    }

    @PostMapping("/updateWithOther")
    public Sample updateSampleWithOther(@RequestBody SampleOther sampleOther) {
        return sampleService.updateSampleWithOther(sampleOther);
    }

    @PostMapping("/updateWithService")
    public Sample updateSampleWithService(@RequestBody SampleServiceModel sampleServiceModel) {
        return sampleService.updateSampleWithService(sampleServiceModel);
    }

    @GetMapping("/findSampleLabelById")
    public SampleLabel updateSampleWithService(@RequestParam("labelId") Long labelId) {
        return sampleLabelService.findSampleLabelById(labelId);
    }
    @GetMapping("/sampleLabels")
    public List<SampleLabel> findSampleLabels() {
        return sampleLabelService.findSampleLabels();
    }
}
