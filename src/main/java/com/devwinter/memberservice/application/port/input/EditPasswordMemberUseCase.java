package com.devwinter.memberservice.application.port.input;

public interface EditPasswordMemberUseCase {
    void editPassword(EditPasswordMemberCommand command);

    record EditPasswordMemberCommand(Long memberId, String changePassword) {

    }
}
