package com.example.demo.service;
import com.example.demo.mapper.SampleMapper;
import com.example.demo.model.sample.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.constant.CommonEnums;

import java.util.List;

@Service
@Transactional
public class SampleServiceImpl implements SampleService {

    @Autowired
    private SampleMapper sampleMapper;

    @Override
    public Sample finsSampleById(Long id) {
        return sampleMapper.findSampleById(id);
    }

    @Override
    public Sample findUnFinishedSample(Long userId) {
        return sampleMapper.findUnFinishedSample(userId);
    }

    @Override
    public Sample updateSampleWithName(SampleName sampleName) {
        if (sampleName.getId() == null) {
            Long userId = sampleName.getUserId();
            if (sampleName.getSampleId() == null) {
                Sample sample = new Sample();
                sample.setUserId(userId);
                sample.setStatus(CommonEnums.SampleStatus.未完成.getValue());
                sampleMapper.insertSampleName(sampleName);
                sample.setNameId(sampleName.getId());
                sampleMapper.insertSample(sample);
                sampleMapper.insertSampleLabels(sampleName.getId(), sampleName.getLabelIds());
                return sampleMapper.findSampleById(sample.getId());
            } else {
                Sample sample = sampleMapper.findSampleById(sampleName.getSampleId());
                sampleMapper.insertSampleName(sampleName);
                sampleMapper.insertSampleLabels(sampleName.getId(), sampleName.getLabelIds());
                sample.setNameId(sampleName.getId());
                if (sample.getServiceId() != null
                        && sample.getPriceId() != null
                        && sample.getNameId() != null
                        && sample.getOtherId() != null) {
                    sample.setStatus(CommonEnums.SampleStatus.未发布.getValue());
                }
                sampleMapper.updateSample(sample);
                return sampleMapper.findSampleById(sample.getId());
            }

        } else {
            sampleMapper.updateSampleName(sampleName);
            sampleMapper.deleteSampleLabels(sampleName.getId());
            sampleMapper.insertSampleLabels(sampleName.getId(), sampleName.getLabelIds());
            Sample sample = sampleMapper.findSampleById(sampleName.getSampleId());
            return sample;
        }
    }

    @Override
    public Sample updateSampleWithPrice(SamplePrice samplePrice) {
        if (samplePrice.getId() == null) {
            Long userId = samplePrice.getUserId();
            if (samplePrice.getSampleId() == null) {
                Sample sample = new Sample();
                sample.setUserId(userId);
                sample.setStatus(CommonEnums.SampleStatus.未完成.getValue());
                sampleMapper.insertSamplePrice(samplePrice);
                sample.setPriceId(samplePrice.getId());
                sampleMapper.insertSample(sample);
                return sampleMapper.findSampleById(sample.getId());
            } else {
                Sample sample = sampleMapper.findSampleById(samplePrice.getSampleId());
                sampleMapper.insertSamplePrice(samplePrice);
                sample.setPriceId(samplePrice.getId());
                if (sample.getServiceId() != null
                        && sample.getPriceId() != null
                        && sample.getNameId() != null
                        && sample.getOtherId() != null) {
                    sample.setStatus(CommonEnums.SampleStatus.未发布.getValue());
                }
                sampleMapper.updateSample(sample);
                return sampleMapper.findSampleById(sample.getId());
            }

        } else {
            sampleMapper.updateSamplePrice(samplePrice);
            Sample sample = sampleMapper.findSampleById(samplePrice.getSampleId());
            return sample;
        }
    }

    public Sample updateSampleWithService(SampleServiceModel sampleServiceModel) {
        if (sampleServiceModel.getId() == null) {
            Long userId = sampleServiceModel.getUserId();
            if (sampleServiceModel.getSampleId() == null) {
                Sample sample = new Sample();
                sample.setUserId(userId);
                sample.setStatus(CommonEnums.SampleStatus.未完成.getValue());
                sampleMapper.insertSampleService(sampleServiceModel);
                sample.setServiceId(sampleServiceModel.getId());
                sampleMapper.insertSample(sample);
                return sampleMapper.findSampleById(sample.getId());
            } else {
                Sample sample = sampleMapper.findSampleById(sampleServiceModel.getSampleId());
                sampleMapper.insertSampleService(sampleServiceModel);
                sample.setServiceId(sampleServiceModel.getId());
                if (sample.getServiceId() != null
                        && sample.getPriceId() != null
                        && sample.getNameId() != null
                        && sample.getOtherId() != null) {
                    sample.setStatus(CommonEnums.SampleStatus.未发布.getValue());
                }
                sampleMapper.updateSample(sample);
                return sampleMapper.findSampleById(sample.getId());
            }

        } else {
            sampleMapper.updateSampleService(sampleServiceModel);
            Sample sample = sampleMapper.findSampleById(sampleServiceModel.getSampleId());
            return sample;
        }
    }

    public Sample updateSampleWithOther(SampleOther sampleOther) {
        if (sampleOther.getId() == null) {
            Long userId = sampleOther.getUserId();
            if (sampleOther.getSampleId() == null) {
                Sample sample = new Sample();
                sample.setUserId(userId);
                sample.setStatus(CommonEnums.SampleStatus.未完成.getValue());
                sampleMapper.insertSampleOther(sampleOther);
                sample.setOtherId(sampleOther.getId());
                sampleMapper.insertSample(sample);
                return sampleMapper.findSampleById(sample.getId());
            } else {
                Sample sample = sampleMapper.findSampleById(sampleOther.getSampleId());
                sampleMapper.insertSampleOther(sampleOther);
                sample.setOtherId(sampleOther.getId());
                if (sample.getServiceId() != null
                        && sample.getPriceId() != null
                        && sample.getNameId() != null
                        && sample.getOtherId() != null) {
                    sample.setStatus(CommonEnums.SampleStatus.未发布.getValue());
                }
                sampleMapper.updateSample(sample);
                return sampleMapper.findSampleById(sample.getId());
            }

        } else {
            sampleMapper.updateSampleOther(sampleOther);
            Sample sample = sampleMapper.findSampleById(sampleOther.getSampleId());
            return sample;
        }
    }
}
