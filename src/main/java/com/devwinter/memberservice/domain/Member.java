package com.devwinter.memberservice.domain;

import com.devwinter.memberservice.application.exception.MemberException;
import lombok.*;

import java.time.LocalDateTime;

import static com.devwinter.memberservice.application.exception.MemberErrorCode.MEMBER_ALREADY_DELETE;


@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {
    private MemberId id;
    private String nickName;
    private String email;
    private String password;
    private LocalDateTime lastPasswordChangedAt;
    private Profile profile;
    private boolean deleted;
    private LocalDateTime deletedAt;

    public static Member withoutId(String nickName, String email, String password, Profile profile) {
        return Member.builder()
                     .nickName(nickName)
                     .email(email)
                     .password(password)
                     .profile(profile)
                     .build();
    }

    public static Member withId(MemberId id, String nickName, String email, String password, Profile profile) {
        return Member.builder()
                     .id(id)
                     .nickName(nickName)
                     .email(email)
                     .password(password)
                     .profile(profile)
                     .build();
    }

    public void changePassword(String newPassword) {
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
