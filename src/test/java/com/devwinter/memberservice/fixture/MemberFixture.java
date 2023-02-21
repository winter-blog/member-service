package com.devwinter.memberservice.fixture;

import com.devwinter.memberservice.domain.Member;
import com.devwinter.memberservice.domain.Profile;
import com.devwinter.memberservice.domain.factory.MemberFactory;

public class MemberFixture {
    public Member complete() {
        return MemberFactory.withId(
                new Member.MemberId(1L),
                "nickName",
                "email",
                "password",
                new Profile(new Member.MemberId(1L), "profile.png", Profile.ProfileType.DEFAULT, createdAt)
        );
    }
}
