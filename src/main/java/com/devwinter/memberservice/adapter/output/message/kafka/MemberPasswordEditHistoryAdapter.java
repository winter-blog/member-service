package com.devwinter.memberservice.adapter.output.message.kafka;

import com.devwinter.memberservice.adapter.output.message.kafka.utils.MemberChangePasswordSchema;
import com.devwinter.memberservice.application.port.output.history.MemberPasswordEditHistoryPort;
import com.devwinter.memberservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberPasswordEditHistoryAdapter implements MemberPasswordEditHistoryPort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final MemberChangePasswordSchema kafka;

    @Override
    public void send(Member member, String originalPassword) {
        String jsonInString = kafka.createMessage(new MemberPasswordEditHistoryCommand(member.getId().value(),
                member.getPassword(), originalPassword));

        kafkaTemplate.send(kafka.getTopics(), jsonInString);
    }
}
