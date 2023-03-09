package com.devwinter.memberservice.application.service;

import com.devwinter.memberservice.application.port.input.PasswordCheckSameUseCase;
import com.devwinter.memberservice.application.port.output.LoadMemberQueryPort;
import com.devwinter.memberservice.application.service.exception.MemberErrorCode;
import com.devwinter.memberservice.application.service.exception.MemberException;
import com.devwinter.memberservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PasswordCheckSameService implements PasswordCheckSameUseCase {

    private final LoadMemberQueryPort loadMemberQueryPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public void check(PasswordCheckCommand command) {
        Member member = loadMemberQueryPort.findByMemberId(command.memberId());

        if(!passwordEncoder.matches(command.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.MEMBER_PASSWORD_NOT_SAME);
        }
    }
}
