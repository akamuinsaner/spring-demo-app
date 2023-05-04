package com.example.demo.controller;

import com.example.demo.model.sample.*;
import com.example.demo.service.SampleLabelService;
import com.example.demo.service.SampleService;
import com.example.demo.service.SampleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @GetMapping("/template/findByUserId")
    public List<SampleServiceModel> findSampleServiceTemplateByUserId(@RequestParam("userId") Long userId) {
        return sampleService.findSampleServiceTemplateByUserId(userId);
    }

    @PostMapping("/template/insert")
    public Long insertSampleServiceTemplate(@RequestBody SampleServiceModel sampleServiceModel) {
        return sampleService.insertSampleServiceTemplate(sampleServiceModel);
    }

    @PostMapping("/template/update")
    public Long updateSampleServiceTemplate(@RequestBody SampleServiceModel sampleServiceModel) {
        return sampleService.updateSampleServiceTemplate(sampleServiceModel);
    }

    @PostMapping("/template/deleteById")
    public void deleteSampleServiceTemplateById(@RequestBody Map<String, Long> map) {
        sampleService.deleteSampleServiceTemplateById(map.get("templateId"));
    }
}
