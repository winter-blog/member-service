package com.devwinter.memberservice.application.port.output;

import com.devwinter.memberservice.domain.Member;

public interface UpdateMemberPort {
    void updatePassword(Member member);
}
