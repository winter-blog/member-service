package com.devwinter.memberservice.adapter.output.persistence.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class KafkaDto implements Serializable {
    private Schema schema;
    private MemberChangePasswordPayload memberChangePasswordPayload;
}
