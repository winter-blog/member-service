package com.devwinter.memberservice.adapter.input.api.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class AddProfileMember {
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {
        private static String DEFAULT_MESSAGE = "프로필을 등록하였습니다.";

        public static BaseResponse<AddProfileMember.Response> success() {
            return BaseResponse.success(DEFAULT_MESSAGE);
        }
    }
}
