package com.devwinter.memberservice.adapter.input.api.dto;

import com.devwinter.memberservice.adapter.input.api.valid.Email;
import lombok.Getter;

public class EmailDuplicate {

    @Getter
    public static class Request {
        @Email
        private String email;
    }
}
