package com.devwinter.memberservice.application.service;

import com.devwinter.memberservice.application.port.input.NicknameDuplicateUseCase;
import com.devwinter.memberservice.application.port.output.NicknameDuplicateQueryPort;
import com.devwinter.memberservice.application.service.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.devwinter.memberservice.application.service.exception.MemberErrorCode.MEMBER_NICKNAME_DUPLICATE;

@Service
@RequiredArgsConstructor
public class NicknameDuplicateService implements NicknameDuplicateUseCase {

    private final NicknameDuplicateQueryPort nicknameDuplicateQueryPort;

    @Override
    @Transactional(readOnly = true)
    public void check(String nickName) {
        if (nicknameDuplicateQueryPort.existByNickname(nickName)) {
            throw new MemberException(MEMBER_NICKNAME_DUPLICATE);
        }
    }
}
