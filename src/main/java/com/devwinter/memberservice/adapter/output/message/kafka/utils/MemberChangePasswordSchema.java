package com.devwinter.memberservice.adapter.output.message.kafka.utils;

import com.devwinter.memberservice.adapter.output.message.kafka.dto.common.Field;
import com.devwinter.memberservice.adapter.output.message.kafka.dto.common.KafkaMessage;
import com.devwinter.memberservice.adapter.output.message.kafka.dto.payload.MemberChangePasswordPayload;
import com.devwinter.memberservice.adapter.output.message.kafka.dto.common.Schema;
import com.devwinter.memberservice.application.port.output.history.MemberPasswordEditHistoryPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class MemberChangePasswordSchema extends AbstractObjectMapper {
    public MemberChangePasswordSchema(ObjectMapper mapper) {
        super(mapper);
    }

    @Override
    public String getTopics() {
        return "member_password_edit_history";
    }

    public String createMessage(MemberPasswordEditHistoryPort.MemberPasswordEditHistoryCommand command) {
        return getMessage(new KafkaMessage<>(createSchema(), createPayload(command)));
    }

    private Schema createSchema() {
        List<Field> fields = Arrays.asList(
                new Field("int64", true, "member_id"),
                new Field("string", true, "original_password"),
                new Field("string", true, "new_password")
        );

        return Schema.builder()
                     .type("struct")
                     .fields(fields)
                     .optional(false)
                     .name(getTopics())
                     .build();
    }

    private MemberChangePasswordPayload createPayload(MemberPasswordEditHistoryPort.MemberPasswordEditHistoryCommand command) {
        return  MemberChangePasswordPayload.builder()
                                           .member_id(command.memberId())
                                           .original_password(command.originalPassword())
                                           .new_password(command.newPassword())
                                           .build();
    }
}
