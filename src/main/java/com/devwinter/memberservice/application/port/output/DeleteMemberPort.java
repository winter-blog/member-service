package com.devwinter.memberservice.application.port.output;

import com.devwinter.memberservice.domain.Member;

public interface DeleteMemberPort {
    void delete(Member member);
}
