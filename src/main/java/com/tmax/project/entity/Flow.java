package com.tmax.project.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Flow {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "flow", cascade = CascadeType.REMOVE)
    private List<Step> steps = new ArrayList<>(); // 양방향 연관관계 매핑을 위한 변수

    public void update(String name) {
        this.name = name;
    }

    @Builder
    public Flow(String name){
        this.name = name;
    }

}
