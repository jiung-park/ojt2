package com.tmax.project.dto.service;

import com.tmax.project.entity.MessageStep;
import com.tmax.project.entity.WebsiteStep;
import com.tmax.project.type.StepType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
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

    public WebsiteStep toWebsiteStep(){
        return WebsiteStep.builder()
                .name(name)
                .url(url)
                .build();
    }

}
