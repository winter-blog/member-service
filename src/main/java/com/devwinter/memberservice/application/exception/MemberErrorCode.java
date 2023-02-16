package com.devwinter.memberservice.application.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode {
    MEMBER_DUPLICATE_EXCEPTION(HttpStatus.CONFLICT, "이미 등록된 회원입니다."),
    RANDOM_PROFILE_IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "랜덤 프로필 이미지가 존재하지 않습니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "회원이 존재하지 않습니다."),
    MEMBER_PASSWORD_SAME(HttpStatus.CONFLICT, "기존 비밀번호와 동일합니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
