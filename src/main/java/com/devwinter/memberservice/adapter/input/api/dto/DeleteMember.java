package com.devwinter.memberservice.adapter.input.api.dto;

public class DeleteMember {

    public static class Response {
        private static String DEFAULT_MESSAGE = "회원 탈퇴에 성공하였습니다.";

        public static BaseResponse<DeleteMember.Response> success() {
            return BaseResponse.success(DEFAULT_MESSAGE);
        }
    }
}
