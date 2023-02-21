package com.devwinter.memberservice.application.port.output;

import com.devwinter.memberservice.domain.Member;

import java.util.Optional;

public interface LoadMemberPort {
    Member findById(Long memberId);
    Member findByEmail(String email);
    boolean existByEmail(String email);
}
