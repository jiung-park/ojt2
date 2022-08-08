package com.tmax.project.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("website")
@NoArgsConstructor
public class WebsiteStep extends Step{

    private String url;

    public void update(String name, String url) {
        this.setName(name);
        this.url = url;
    }

    @Builder
    public WebsiteStep(Flow flow, String name, String url) {
        super(flow, name);
        this.url = url;
    }

}
