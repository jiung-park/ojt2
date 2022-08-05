package com.tmax.project.service;

import com.tmax.project.dto.service.FlowDto;
import com.tmax.project.dto.service.StepDto;
import com.tmax.project.entity.Flow;
import com.tmax.project.entity.MessageStep;
import com.tmax.project.entity.WebsiteStep;
import com.tmax.project.repository.FlowRepository;
import com.tmax.project.repository.MessageStepRepository;
import com.tmax.project.repository.WebsiteStepRepository;
import com.tmax.project.type.StepType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlowService {

    private final FlowRepository flowRepository;
    private final MessageStepRepository messageStepRepository;
    private final WebsiteStepRepository websiteStepRepository;

    public Flow saveFlow(FlowDto flowDto) {
        Flow flow = flowDto.toFlow();
        flowRepository.save(flow);
        for (StepDto step : flowDto.getSteps()) {
            saveStep(flow, step);
        }
        return flow;
    }

    private void saveStep(Flow flow, StepDto stepDto) {
        StepType stepType = stepDto.getStepType();
        if(stepType.equals(StepType.message)){ // studio 코드 == 비교의 이유?
            MessageStep messageStep = stepDto.toMessageStep();
            messageStep.updateStep(flow);
            messageStepRepository.save(messageStep);
        }
        else if(stepType.equals(StepType.website)){
            WebsiteStep websiteStep = stepDto.toWebsiteStep();
            websiteStep.updateStep(flow);
            websiteStepRepository.save(websiteStep);
        }
    }

}

