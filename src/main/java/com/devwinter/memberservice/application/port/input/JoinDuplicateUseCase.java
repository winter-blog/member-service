package com.devwinter.memberservice.application.port.input;

import com.devwinter.memberservice.adapter.input.api.valid.Email;
import com.devwinter.memberservice.adapter.input.api.valid.Nickname;
import com.devwinter.memberservice.common.SelfValidating;
import lombok.Getter;

public interface JoinDuplicateUseCase {
    void nickNameCheck(NicknameDuplicateCommand command);
    void emailCheck(EmailDuplicateCommand command);

    @Getter
    class NicknameDuplicateCommand extends SelfValidating {
        @Nickname
        private final String nickName;

        public NicknameDuplicateCommand(String nickName) {
            this.nickName = nickName;
            this.validateSelf();
        }
    }

    @Getter
    class EmailDuplicateCommand extends SelfValidating {
        @Email
        private final String email;

        public EmailDuplicateCommand(String email) {
            this.email = email;
            this.validateSelf();
        }
    }
}
