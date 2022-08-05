package com.tmax.project.service;

import com.tmax.project.dto.service.FlowDto;
import com.tmax.project.dto.service.StepDto;
import com.tmax.project.entity.Flow;
import com.tmax.project.repository.FlowRepository;
import com.tmax.project.repository.MessageStepRepository;
import com.tmax.project.repository.WebsiteStepRepository;
import com.tmax.project.type.StepType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FlowServiceTest {

    @Autowired
    private FlowService flowService;

    @Test
    @DisplayName("flow service test")
    @Rollback(value = false)
    void postFlowServiceTest() throws Exception {

        // given
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

        List<StepDto> steps = List.of(messageStepDto, websiteStepDto);

        FlowDto flowDto = FlowDto.builder()
                .name("test flow")
                .steps(steps)
                .build();

        // when
        flowService.saveFlow(flowDto);

        // then
        
    }

}
