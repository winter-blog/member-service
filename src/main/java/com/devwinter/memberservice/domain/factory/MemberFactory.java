package com.devwinter.memberservice.domain.factory;

import com.devwinter.memberservice.domain.Member;
import com.devwinter.memberservice.domain.Profile;

public class MemberFactory {
    public static Member withoutId(String nickName, String email, String password, Profile profile) {
        Member member = Member.builder()
                             .nickName(nickName)
                             .email(email)
                             .password(password)
                             .build();

        member.addProfile(profile);
        return member;
    }

    public static Member withId(Member.MemberId id, String nickName, String email, String password, Profile profile) {
        Member member = Member.builder()
                             .id(id)
                             .nickName(nickName)
                             .email(email)
                             .password(password)
                             .build();

        member.addProfile(profile);
        return member;
    }
}
