package com.devwinter.memberservice.adapter.output.persistence.kafka.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberChangePasswordPayload {
    private Long member_id;
    private String original_password;
    private String new_password;
}
