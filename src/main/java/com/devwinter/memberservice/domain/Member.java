package com.devwinter.memberservice.domain;

import com.devwinter.memberservice.application.service.exception.MemberException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static com.devwinter.memberservice.application.service.exception.MemberErrorCode.MEMBER_ALREADY_DELETE;
import static com.devwinter.memberservice.application.service.exception.MemberErrorCode.MEMBER_PASSWORD_SAME;


@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {
    private MemberId id;
    private String nickName;
    private String email;
    private String password;
    private Profile profile;
    private boolean deleted;

    public void changePassword(String newPassword) {
        if (this.password.equals(newPassword)) {
            throw new MemberException(MEMBER_PASSWORD_SAME);
        }
        this.password = newPassword;
    }

    public void delete() {
        if(this.deleted) {
            throw new MemberException(MEMBER_ALREADY_DELETE);
        }
        this.deleted = true;
    }

    public record MemberId(Long value) {
    }
}
