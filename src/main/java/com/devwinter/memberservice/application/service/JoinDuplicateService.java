package com.devwinter.memberservice.application.service;

import com.devwinter.memberservice.application.port.input.JoinDuplicateUseCase;
import com.devwinter.memberservice.application.port.output.JoinDuplicateCheckQueryPort;
import com.devwinter.memberservice.application.service.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.devwinter.memberservice.application.service.exception.MemberErrorCode.MEMBER_DUPLICATE_EXCEPTION;
import static com.devwinter.memberservice.application.service.exception.MemberErrorCode.MEMBER_NICKNAME_DUPLICATE;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JoinDuplicateService implements JoinDuplicateUseCase {

    private final JoinDuplicateCheckQueryPort joinDuplicateCheckQueryPort;

    @Override
    public void nickNameCheck(NicknameDuplicateCommand command) {
        if (joinDuplicateCheckQueryPort.existByNickname(command.nickName())) {
            throw new MemberException(MEMBER_NICKNAME_DUPLICATE);
        }
    }

    @Override
    public void emailCheck(EmailDuplicateCommand command) {
        if (joinDuplicateCheckQueryPort.existByEmail(command.email())) {
            throw new MemberException(MEMBER_DUPLICATE_EXCEPTION);
        }
    }
}
