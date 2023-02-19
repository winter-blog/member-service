package com.devwinter.memberservice.domain.factory;

import com.devwinter.memberservice.domain.Member;
import com.devwinter.memberservice.domain.Profile;

public class MemberFactory {
    public static Member withoutId(String nickName, String email, String password, Profile profile) {
        return Member.builder()
                     .nickName(nickName)
                     .email(email)
                     .password(password)
                     .profile(profile)
                     .build();
    }

    public static Member withId(Member.MemberId id, String nickName, String email, String password, Profile profile) {
        return Member.builder()
                     .id(id)
                     .nickName(nickName)
                     .email(email)
                     .password(password)
                     .profile(profile)
                     .build();
    }
}
