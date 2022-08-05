package com.tmax.project.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "stepType")
public abstract class Step {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "flow_id") // 연관관계 주인인 Step이 Flow의 id를 FK로 갖음
    private Flow flow;

    public Step(Flow flow, String name) {
        this.flow = flow;
        this.name = name;
    }

    public void updateStep(Flow flow){ // 연관관계 편의 매소드
        this.flow = flow;
        flow.getSteps().add(this);
    }

    public void setName(String name) {
        this.name = name;
    }

}
