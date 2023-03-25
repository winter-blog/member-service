package com.devwinter.memberservice.application.port.input;

public interface PasswordCheckSameUseCase {
    void check(PasswordCheckCommand command);

    record PasswordCheckCommand(Long memberId, String password) {

    }
}
