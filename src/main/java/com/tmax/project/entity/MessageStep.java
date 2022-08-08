package com.tmax.project.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("message")
@NoArgsConstructor
public class MessageStep extends Step{

    private String text;

    public void update(String name, String text){
        this.setName(name);
        this.text = text;
    }

    @Builder
    public MessageStep(Flow flow, String name, String text){
        super(flow, name);
        this.text = text;
    }

}
