package com.devwinter.memberservice.application.port.output.history;

import com.devwinter.memberservice.domain.Member;


public interface MemberPasswordEditHistoryPort {

    void send(Member member, String originalPassword);

    record MemberPasswordEditHistoryCommand(
            Long memberId,
            String originalPassword,
            String newPassword
    ) {

    }
}
