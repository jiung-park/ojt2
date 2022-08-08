package com.tmax.project.entity;

import com.tmax.project.repository.FlowRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FlowTest {

    @Autowired
    private FlowRepository flowRepository;

    @Test
    @DisplayName("flow insertion test")
    void insertFlowTest() throws Exception {

        // given
        Flow flow = Flow.builder()
                .name("flow1")
                .build();

        // when
        Flow insertFlow = flowRepository.save(flow);
        Flow findFlow = flowRepository.findById(insertFlow.getId())
                .orElseThrow(NullPointerException::new);

        // then
        Assertions.assertThat(insertFlow).isEqualTo(findFlow);

    }

}
