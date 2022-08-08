package com.tmax.project.service;

import com.tmax.project.dto.service.FlowDto;
import com.tmax.project.dto.service.StepDto;
import com.tmax.project.entity.Flow;
import com.tmax.project.entity.MessageStep;
import com.tmax.project.entity.Step;
import com.tmax.project.entity.WebsiteStep;
import com.tmax.project.repository.FlowRepository;
import com.tmax.project.repository.MessageStepRepository;
import com.tmax.project.repository.WebsiteStepRepository;
import com.tmax.project.type.StepType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

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

    public FlowDto getFlow(Long flowId) {
        Flow flow = flowRepository.findById(flowId).orElseThrow(NullPointerException::new);
        FlowDto flowDto = FlowDto.builder()
                .id(flow.getId())
                .name(flow.getName())
                .steps(flow.getSteps().stream().map(StepDto::toDto).collect(Collectors.toList()))
                .build();
        return flowDto;
    }

    public void updateFlow(FlowDto flowDto) {
        Long flow_id = flowDto.getId();
        Flow flow = flowRepository.findById(flow_id).orElseThrow(NullPointerException::new);
        if(!flowDto.getName().equals(flow.getName())){
            flow.update(flowDto.getName());
        }
        for (StepDto step : flowDto.getSteps()) {
            if(step.getId() != null) {
                updateStep(flow, step);
            }
            else {
                saveStep(flow, step);
            }

        }
    }

    public void updateStep(Flow flow, StepDto stepDto){
        StepType stepType = stepDto.getStepType();
        if(stepType.equals(StepType.message)){
            MessageStep messageStep = messageStepRepository.findById(stepDto.getId()).orElseThrow(NullPointerException::new);
            messageStep.update(stepDto.getName(), stepDto.getText());
        }
        else if(stepType.equals(StepType.website)){
            WebsiteStep websiteStep = websiteStepRepository.findById(stepDto.getId()).orElseThrow(NullPointerException::new);
            websiteStep.update(stepDto.getName(), stepDto.getUrl());
        }
    }

    public void deleteFlow(Long flowId) {
        flowRepository.deleteById(flowId);
    }

}

