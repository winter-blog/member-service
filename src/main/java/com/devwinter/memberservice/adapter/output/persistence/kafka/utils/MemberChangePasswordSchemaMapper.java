package com.devwinter.memberservice.adapter.output.persistence.kafka.utils;

import com.devwinter.memberservice.adapter.output.persistence.kafka.dto.Field;
import com.devwinter.memberservice.adapter.output.persistence.kafka.dto.KafkaDto;
import com.devwinter.memberservice.adapter.output.persistence.kafka.dto.MemberChangePasswordPayload;
import com.devwinter.memberservice.adapter.output.persistence.kafka.dto.Schema;
import com.devwinter.memberservice.application.port.output.MemberPasswordEditHistoryPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class MemberChangePasswordSchemaMapper extends AbstractObjectMapper {
    public MemberChangePasswordSchemaMapper(ObjectMapper mapper) {
        super(mapper);
    }

    public String createMessage(MemberPasswordEditHistoryPort.MemberPasswordEditHistoryCommand command) {
        return getMessage(new KafkaDto(createSchema(), createPayload(command)));
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
                     .name("member_password_edit_history")
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
