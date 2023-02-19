package com.devwinter.memberservice.application.service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode {
    MEMBER_DUPLICATE_EXCEPTION(HttpStatus.CONFLICT, "이미 등록된 회원입니다."),
    TEMPLATE_PROFILE_IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "템플릿 프로필 이미지가 존재하지 않습니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "회원이 존재하지 않습니다."),
    MEMBER_PASSWORD_SAME(HttpStatus.CONFLICT, "기존 비밀번호와 동일합니다."),
    MEMBER_ALREADY_DELETE(HttpStatus.BAD_REQUEST, "회원이 이미 휴면 계정입니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}