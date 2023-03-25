package com.devwinter.memberservice.adapter.input.api.dto;

import lombok.Getter;

import java.util.List;

public class MemberInfo {
    @Getter
    public static class Request {
        private List<Long> memberIds;
    }
}
