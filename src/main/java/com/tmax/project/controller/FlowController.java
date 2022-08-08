package com.tmax.project.controller;

import com.tmax.project.dto.service.FlowDto;
import com.tmax.project.dto.service.StepDto;
import com.tmax.project.service.FlowService;
import com.tmax.project.type.StepType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flow")
@RequiredArgsConstructor
public class FlowController {

    @Autowired
    private final FlowService flowService;

    @PostMapping
    public ResponseEntity<?> createFlow() {
        StepDto messageStepDto = StepDto.builder()
                .name("message step")
                .stepType(StepType.message)
                .text("hello")
                .build();

        StepDto websiteStepDto = StepDto.builder()
                .name("website step")
                .stepType(StepType.website)
                .url("www.hello.com")
                .build();

        List<StepDto> steps = List.of(websiteStepDto, websiteStepDto, messageStepDto);

        FlowDto flowDto = FlowDto.builder()
                .name("test flow")
                .steps(steps)
                .build();

        flowService.saveFlow(flowDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{flowId}")
    public ResponseEntity<FlowDto> getFlow(@PathVariable Long flowId) {
        FlowDto flowDto = flowService.getFlow(flowId);
        return ResponseEntity.ok(flowDto);
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateFlow() {
        StepDto messageStep = StepDto.builder()
                .id(4L)
                .stepType(StepType.message)
                .name("update step")
                .build();

        StepDto websiteStep = StepDto.builder()
                .name("neeew step")
                .stepType(StepType.website)
                .url("new.com")
                .build();

        List<StepDto> steps = List.of(messageStep, websiteStep);

        FlowDto flowDto = FlowDto.builder()
                .id(1L)
                .name("update flow")
                .steps(steps)
                .build();


        flowService.updateFlow(flowDto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("/{flowId}")
    public ResponseEntity<Void> deleteFlow(@PathVariable Long flowId) {
        flowService.deleteFlow(flowId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
