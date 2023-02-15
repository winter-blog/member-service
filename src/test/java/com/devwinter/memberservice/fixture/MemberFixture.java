package com.devwinter.memberservice.fixture;

import com.devwinter.memberservice.domain.Member;
import com.devwinter.memberservice.domain.Profile;

public class MemberFixture {
    public Member complete() {
        return Member.withId(
                new Member.MemberId(1L),
                "nickName",
                "email",
                "password",
                new Profile("profile.png")
        );
    }
}
