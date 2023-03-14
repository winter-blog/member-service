package com.devwinter.memberservice.adapter.input.api.dto;

import com.devwinter.memberservice.adapter.input.api.valid.Password;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ValidPassword {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @Password
        private String password;
    }
}
