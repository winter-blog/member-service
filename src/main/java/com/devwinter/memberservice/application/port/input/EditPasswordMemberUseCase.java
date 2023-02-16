package com.devwinter.memberservice.application.port.input;

public interface EditPasswordMemberUseCase {
    void editPassword(Long memberId, String newPassword);
}
