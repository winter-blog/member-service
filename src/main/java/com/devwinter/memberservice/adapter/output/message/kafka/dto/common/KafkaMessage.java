package com.devwinter.memberservice.adapter.output.message.kafka.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class KafkaMessage<T> implements Serializable {
    private Schema schema;
    private T payload;
}
