package com.devwinter.memberservice.domain.factory;

import com.devwinter.memberservice.domain.Member;
import com.devwinter.memberservice.domain.Profile;

import java.util.UUID;

public class ProfileFactory {
    public static Profile createProfile(Member.MemberId memberId, String path) {
        return new Profile(memberId, UUID.randomUUID() + "-" + path, Profile.ProfileType.CUSTOM);
    }
}
