package com.devwinter.memberservice.adapter.input.api.dto;

import com.devwinter.memberservice.adapter.input.api.valid.Password;
import lombok.Getter;

public class ValidPassword {
    @Getter
    public static class Request {
        @Password
        private String password;
    }
}
