package com.tmax.project.dto.service;

import com.tmax.project.entity.MessageStep;
import com.tmax.project.entity.Step;
import com.tmax.project.entity.WebsiteStep;
import com.tmax.project.type.StepType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor // noargs const 추가했을때 이것도 추가해야하는 이유?
public class StepDto {

    private Long id;
    private String name;
    private StepType stepType;
    private String text;
    private String url;

    public MessageStep toMessageStep(){
        return MessageStep.builder() // 기존 코드에서 .flow(flow) 해주는 이유? 어차피 setFlow() 사용
                .name(name)
                .text(text)
                .build();
    }

    public static StepDto toMessageStepDto(MessageStep messageStep){
        return StepDto.builder()
                .id(messageStep.getId())
                .name(messageStep.getName())
                .stepType(StepType.message)
                .text(messageStep.getText())
                .build();
    }

    public WebsiteStep toWebsiteStep(){
        return WebsiteStep.builder()
                .name(name)
                .url(url)
                .build();
    }

    public static StepDto toWebsiteStepDto(WebsiteStep websiteStep){
        return StepDto.builder()
                .id(websiteStep.getId())
                .name(websiteStep.getName())
                .stepType(StepType.website)
                .url(websiteStep.getUrl())
                .build();
    }

    public static StepDto toDto(Step step) { // 왜 static 인가...
        if(step instanceof MessageStep)
            return toMessageStepDto((MessageStep) step);
        else if(step instanceof WebsiteStep)
            return toWebsiteStepDto((WebsiteStep) step);
        return new StepDto(); // ???
    }

}
