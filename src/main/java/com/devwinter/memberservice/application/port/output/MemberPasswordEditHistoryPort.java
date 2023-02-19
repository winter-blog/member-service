package com.devwinter.memberservice.application.port.output;

import com.devwinter.memberservice.domain.Member;

import java.time.LocalDateTime;

public interface MemberPasswordEditHistoryPort {

    void send(Member member, String originalPassword);

    record MemberPasswordEditHistoryCommand(
            Long memberId,
            String originalPassword,
            String newPassword
    ) {

    }
}
