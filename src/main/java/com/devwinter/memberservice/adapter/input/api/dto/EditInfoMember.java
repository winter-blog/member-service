package com.devwinter.memberservice.adapter.input.api.dto;

import lombok.Getter;

public class EditInfoMember {
    @Getter
    public static class Request {
        private String nickName;

    }

    public static class Response {
        private static String DEFAULT_MESSAGE = "사용자 정보를 변경하였습니다.";

        public static BaseResponse<EditInfoMember.Response> success() {
            return BaseResponse.success(DEFAULT_MESSAGE);
        }
    }
}
