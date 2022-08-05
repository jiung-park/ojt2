package com.tmax.project.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Flow {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "flow")
    private List<Step> steps = new ArrayList<>(); // 양방향 연관관계 매핑을 위한 변수

    @Builder
    public Flow(String name){
        this.name = name;
    }

}
