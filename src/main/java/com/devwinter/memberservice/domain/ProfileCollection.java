package com.devwinter.memberservice.domain;

import com.devwinter.memberservice.application.service.exception.MemberException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static com.devwinter.memberservice.application.service.exception.MemberErrorCode.MEMBER_PROFILE_DUPLICATE;

@Getter
public class ProfileCollection {

    private final List<Profile> profiles;

    public ProfileCollection(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public void addProfile(Profile profile) {

        for (Profile p : profiles) {
            if (p.getType()
                 .equals(profile.getType()) &&
                    p.getPath()
                     .equals(profile.getPath())) {
                throw new MemberException(MEMBER_PROFILE_DUPLICATE);
            }
        }

        this.profiles.add(profile);
    }
}
