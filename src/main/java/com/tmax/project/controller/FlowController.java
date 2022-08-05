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
        System.out.println("flowId = " + flowId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
