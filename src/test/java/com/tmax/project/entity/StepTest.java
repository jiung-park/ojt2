package com.tmax.project.entity;

import com.tmax.project.repository.MessageStepRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StepTest {

    @Autowired
    private MessageStepRepository messageStepRepository;

    @Test
    @DisplayName("step insertion test")
    void insertStepTest() throws Exception {

        // given
        MessageStep messageStep = MessageStep.builder()
                .name("step1")
                .text("hihi")
                .build();

        // when
        MessageStep insertMessageStep = messageStepRepository.save(messageStep);
        MessageStep findMessageStep = messageStepRepository.findById(insertMessageStep.getId())
                .orElseThrow(NullPointerException::new);

        // then
        Assertions.assertThat(insertMessageStep).isEqualTo(findMessageStep);

    }

}
