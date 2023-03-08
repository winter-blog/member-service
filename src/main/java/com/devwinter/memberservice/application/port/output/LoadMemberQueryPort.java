package com.devwinter.memberservice.application.port.output;

import com.devwinter.memberservice.domain.Member;

public interface LoadMemberQueryPort {
    Member findByMemberId(Long memberId);
    boolean existByEmail(String email);
}
