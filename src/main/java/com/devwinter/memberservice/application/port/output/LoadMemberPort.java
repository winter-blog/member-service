package com.devwinter.memberservice.application.port.output;

import com.devwinter.memberservice.domain.Member;

import java.util.Optional;

public interface LoadMemberPort {
    Optional<Member> findByEmail(String email);
}
