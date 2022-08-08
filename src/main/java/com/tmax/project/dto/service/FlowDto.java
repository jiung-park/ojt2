package com.tmax.project.dto.service;

import com.tmax.project.entity.Flow;
import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.util.List;

@Builder
@Getter
public class FlowDto {

    private Long id;
    private String name;

    @Nullable
    private List<StepDto> steps;

    public Flow toFlow() {
        return Flow.builder()
                .name(name)
                .build();
    }

}
