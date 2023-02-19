package com.devwinter.memberservice.application.service;

import com.devwinter.memberservice.application.service.exception.MemberException;
import com.devwinter.memberservice.application.port.input.EditPasswordMemberUseCase;
import com.devwinter.memberservice.application.port.output.LoadMemberPort;
import com.devwinter.memberservice.application.port.output.MemberPasswordEditHistoryPort;
import com.devwinter.memberservice.application.port.output.UpdatePasswordMemberPort;
import com.devwinter.memberservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.devwinter.memberservice.application.service.exception.MemberErrorCode.MEMBER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class EditPasswordMemberService implements EditPasswordMemberUseCase {

    private final LoadMemberPort loadMemberPort;
    private final PasswordEncoder passwordEncoder;
    private final UpdatePasswordMemberPort updatePasswordMemberPort;
    private final MemberPasswordEditHistoryPort memberPasswordEditHistoryPort;

    @Override
    @Transactional
    public void editPassword(EditPasswordMemberCommand command) {
        Member member = loadMemberPort.findById(command.memberId())
                                      .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

        String originalPassword = member.getPassword();
        String changePassword = passwordEncoder.encode(command.changePassword());

        member.changePassword(changePassword);
        updatePasswordMemberPort.updatePassword(member);
        // TODO: 변경 이력 (카프카 - 커넥터 사용)
        memberPasswordEditHistoryPort.send(member, originalPassword);
    }
}
