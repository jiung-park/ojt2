package com.tmax.project.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("website")
public class WebsiteStep extends Step{

    private String url;

    @Builder
    public WebsiteStep(Flow flow, String name, String url) {
        super(flow, name);
        this.url = url;
    }

}
