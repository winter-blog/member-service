package com.devwinter.memberservice.adapter.input.api.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class EditPasswordMember {
    @Getter
    public static class Request {
        @Password
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
