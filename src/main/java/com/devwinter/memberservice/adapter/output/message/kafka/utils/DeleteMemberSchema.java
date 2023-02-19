package com.devwinter.memberservice.adapter.output.message.kafka.utils;

import com.devwinter.memberservice.adapter.output.message.kafka.dto.payload.MemberDeletePayload;
import com.devwinter.memberservice.adapter.output.message.kafka.dto.common.Field;
import com.devwinter.memberservice.adapter.output.message.kafka.dto.common.KafkaMessage;
import com.devwinter.memberservice.adapter.output.message.kafka.dto.common.Schema;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DeleteMemberSchema extends AbstractObjectMapper {

    public DeleteMemberSchema(ObjectMapper mapper) {
        super(mapper);
    }

    public String createMessage(Long memberId) {
        return getMessage(new KafkaMessage<>(createSchema(), createPayload(memberId)));
    }

    private Schema createSchema() {
        List<Field> fields = Arrays.asList(
                new Field("int64", true, "member_id")
        );

        return Schema.builder()
                     .type("struct")
                     .fields(fields)
                     .optional(false)
                     .name(getTopics())
                     .build();
    }

    private MemberDeletePayload createPayload(Long memberId) {
        return  MemberDeletePayload.builder()
                                   .member_id(memberId)
                                   .build();
    }

    @Override
    public String getTopics() {
        return "member_delete_history";
    }
}
