package com.devwinter.memberservice.adapter.output.message.kafka.dto.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDeletePayload {
    private Long member_id;
}
