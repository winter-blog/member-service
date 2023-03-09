package com.devwinter.memberservice.adapter.input.api.dto;

import com.devwinter.memberservice.adapter.input.api.valid.Password;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

public class EditPasswordMember {
    @Getter
    public static class Request {
        @Password
        @NotBlank(message = "패스워드는 필수 값 입니다.")
        private String password;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {
        private static String DEFAULT_MESSAGE = "비밀번호를 변경하였습니다.";

        public static BaseResponse<EditPasswordMember.Response> success() {
            return BaseResponse.success(DEFAULT_MESSAGE);
        }
    }
}
