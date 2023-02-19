package com.devwinter.memberservice.adapter.output.message.kafka;

import com.devwinter.memberservice.adapter.output.message.kafka.utils.DeleteMemberSchema;
import com.devwinter.memberservice.application.port.output.history.DeleteMemberHistoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteMemberHistoryAdapter implements DeleteMemberHistoryPort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final DeleteMemberSchema kafka;

    @Override
    public void send(Long memberId) {
        String jsonInString = kafka.createMessage(memberId);
        kafkaTemplate.send(kafka.getTopics(), jsonInString);
    }
}
