package com.devwinter.memberservice.application.port.output;

import com.devwinter.memberservice.domain.Member;

import java.util.List;

public interface LoadMemberQueryPort {
    Member findByMemberId(Long memberId);
    List<Member> findByMemberIds(List<Long> memberIds);
    boolean existByEmail(String email);
}
