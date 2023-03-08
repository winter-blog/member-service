package com.devwinter.memberservice.domain;

import com.devwinter.memberservice.application.service.exception.MemberException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

import static com.devwinter.memberservice.application.service.exception.MemberErrorCode.MEMBER_PROFILE_MAX_OVER;

@Getter
public class ProfileCollection {

    private static final Integer PROFILE_MAX_NUMBER = 5;
    private final List<Profile> profiles;
    public ProfileCollection(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public void addProfile(Profile profile) {
        if (this.profiles.size() + 1 > PROFILE_MAX_NUMBER) {
            throw new MemberException(MEMBER_PROFILE_MAX_OVER);
        }
        this.profiles.add(profile);
    }
}
