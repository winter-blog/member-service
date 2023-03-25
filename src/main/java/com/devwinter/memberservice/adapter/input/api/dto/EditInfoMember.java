package com.devwinter.memberservice.adapter.input.api.dto;

import com.devwinter.memberservice.adapter.input.api.valid.Nickname;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class EditInfoMember {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @Nickname
        private String nickName;

    }

    public static class Response {
        private static String DEFAULT_MESSAGE = "사용자 정보를 변경하였습니다.";

        public static BaseResponse<EditInfoMember.Response> success() {
            return BaseResponse.success(DEFAULT_MESSAGE);
        }
    }
}
