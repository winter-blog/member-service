package com.devwinter.memberservice.adapter.output.message.kafka;

import com.devwinter.memberservice.application.port.output.PasswordChangeEventPort;
import com.devwinter.memberservice.domain.event.PasswordChangeEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PasswordChangeEventProducer implements PasswordChangeEventPort {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    @Async("messagingTaskExecutor")
    public void send(PasswordChangeEvent event) {
        try {
            String message = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("member-change-password", message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
