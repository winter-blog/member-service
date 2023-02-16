package com.devwinter.memberservice.application.service;

import com.devwinter.memberservice.application.exception.MemberErrorCode;
import com.devwinter.memberservice.application.exception.MemberException;
import com.devwinter.memberservice.application.port.input.EditPasswordMemberUseCase;
import com.devwinter.memberservice.application.port.output.LoadMemberPort;
import com.devwinter.memberservice.application.port.output.UpdateMemberPort;
import com.devwinter.memberservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.devwinter.memberservice.application.exception.MemberErrorCode.MEMBER_NOT_FOUND;
import static com.devwinter.memberservice.application.exception.MemberErrorCode.MEMBER_PASSWORD_SAME;

@Service
@RequiredArgsConstructor
public class EditPasswordMemberService implements EditPasswordMemberUseCase {

    private final LoadMemberPort loadMemberPort;
    private final PasswordEncoder passwordEncoder;
    private final UpdateMemberPort updateMemberPort;

    @Override
    @Transactional
    public void editPassword(Long memberId, String newPassword) {
        Member member = loadMemberPort.findById(memberId)
                                      .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

        if(passwordEncoder.matches(newPassword, member.getPassword())) {
            throw new MemberException(MEMBER_PASSWORD_SAME);
        }

        member.changePassword(newPassword);
        updateMemberPort.updatePassword(member);
    }
}
