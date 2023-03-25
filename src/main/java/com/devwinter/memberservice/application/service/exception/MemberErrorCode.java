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
    MEMBER_PASSWORD_NOT_SAME(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
    MEMBER_ALREADY_DELETE(HttpStatus.BAD_REQUEST, "회원이 이미 휴면 계정입니다."),
    MEMBER_NICKNAME_SAME(HttpStatus.CONFLICT, "회원 닉네임이 동일합니다."),
    MEMBER_PROFILE_MAX_OVER(HttpStatus.BAD_REQUEST, "프로필은 최대 5개까지 등록 가능합니다."),
    MEMBER_NICKNAME_DUPLICATE(HttpStatus.CONFLICT, "동일한 닉네임이 존재합니다."),
    MEMBER_MAIN_PROFILE_NOT_FOUND(HttpStatus.NOT_FOUND, "메인 프로필이 존재하지 않습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
