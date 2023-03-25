package com.devwinter.memberservice.adapter.input.api.dto;

import com.devwinter.memberservice.adapter.input.api.valid.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class EmailDuplicate {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @Email
        private String email;
    }
}
