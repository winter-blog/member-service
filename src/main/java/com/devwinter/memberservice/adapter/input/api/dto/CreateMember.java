package com.devwinter.memberservice.adapter.input.api.dto;

import com.devwinter.memberservice.adapter.input.api.valid.Email;
import com.devwinter.memberservice.adapter.input.api.valid.Nickname;
import com.devwinter.memberservice.adapter.input.api.valid.Password;
import com.devwinter.memberservice.application.port.input.CreateMemberUseCase;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

public class CreateMember {
    @Getter
    public static class Request {
        @Nickname
        private String nickName;
        @Email
        private String email;
        @Password
        private String password;

        public CreateMemberUseCase.CreateMemberCommand toCommand() {
            return new CreateMemberUseCase.CreateMemberCommand(nickName, email, password);
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {
        private Long memberId;
        private static String DEFAULT_MESSAGE = "회원 가입에 성공하였습니다.";

        public static BaseResponse<CreateMember.Response> success(Long memberId) {
            return BaseResponse.success(DEFAULT_MESSAGE, new Response(memberId));
        }
    }
}
