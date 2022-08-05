package com.tmax.project.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("message")
public class MessageStep extends Step{

    private String text;

    @Builder
    public MessageStep(Flow flow, String name, String text){
        super(flow, name);
        this.text = text;
    }

}
