package com.devwinter.memberservice.fixture;

import com.devwinter.memberservice.domain.Member;
import com.devwinter.memberservice.domain.Profile;
import com.devwinter.memberservice.domain.factory.MemberFactory;

import java.time.LocalDateTime;

public class MemberFixture {
    public Member complete() {
        return MemberFactory.withId(
                new Member.MemberId(1L),
                "nickName",
                "email",
                "password",
                new Profile(1L, "profile.png", Profile.ProfileType.DEFAULT, LocalDateTime.now(), true)
        );
    }
}
