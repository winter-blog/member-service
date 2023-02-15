package com.devwinter.memberservice.domain;

import lombok.*;


@Getter
@Builder(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {
    private MemberId id;
    private String nickName;
    private String email;
    private String password;
    private Profile profile;

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


    public record MemberId(Long value) {
    }
}
