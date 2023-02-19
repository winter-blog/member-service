package com.devwinter.memberservice.adapter.output.persistence.kafka;

import com.devwinter.memberservice.adapter.output.persistence.kafka.utils.MemberChangePasswordSchemaMapper;
import com.devwinter.memberservice.application.port.output.MemberPasswordEditHistoryPort;
import com.devwinter.memberservice.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberPasswordEditHistoryAdapter implements MemberPasswordEditHistoryPort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final MemberChangePasswordSchemaMapper mapper;

    @Override
    public void send(Member member, String originalPassword) {
        String jsonInString = mapper.createMessage(new MemberPasswordEditHistoryCommand(member.getId().value(),
                member.getPassword(), originalPassword));

        kafkaTemplate.send("member_password_edit_history", jsonInString);
        log.info("Kafka Producer sent data from the Member microservice: " + jsonInString);
    }
}
