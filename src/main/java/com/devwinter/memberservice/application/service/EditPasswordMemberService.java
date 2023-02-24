package com.devwinter.memberservice.application.service;

import com.devwinter.memberservice.application.port.input.EditPasswordMemberUseCase;
import com.devwinter.memberservice.application.port.output.LoadMemberPort;
import com.devwinter.memberservice.application.port.output.UpdatePasswordMemberPort;
import com.devwinter.memberservice.application.port.output.history.MemberPasswordEditHistoryPort;
import com.devwinter.memberservice.application.service.exception.MemberException;
import com.devwinter.memberservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.devwinter.memberservice.application.service.exception.MemberErrorCode.MEMBER_PASSWORD_SAME;

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
        Member member = loadMemberPort.findById(command.memberId());

        String originalPassword = member.getPassword();

        if(passwordEncoder.matches(command.changePassword(), member.getPassword())) {
            throw new MemberException(MEMBER_PASSWORD_SAME);
        }
        member.changePassword(passwordEncoder.encode(command.changePassword()));
        updatePasswordMemberPort.updatePassword(member);
        // TODO: 변경 이력 (카프카 - 커넥터 사용)
        // memberPasswordEditHistoryPort.send(member, originalPassword);
    }
}
