package com.devwinter.memberservice.application.port.output;

import com.devwinter.memberservice.domain.Profile;

public interface UpdateMemberProfilePort {
    void addMemberProfile(Long memberId, Profile profile);
}
