package com.devwinter.memberservice.application.port.input;

import com.devwinter.memberservice.adapter.input.api.valid.Email;
import com.devwinter.memberservice.adapter.input.api.valid.Nickname;
import com.devwinter.memberservice.common.SelfValidating;
import lombok.Getter;

public interface JoinDuplicateUseCase {
    void nickNameCheck(NicknameDuplicateCommand command);
    void emailCheck(EmailDuplicateCommand command);

    record NicknameDuplicateCommand(String nickName) {

    }

    record EmailDuplicateCommand(String email) {

    }
}
