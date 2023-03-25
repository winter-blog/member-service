package com.devwinter.memberservice.application.port.input;


public interface EditInfoMemberUseCase {
    void edit(EditInfoMemberCommand command);
    record EditInfoMemberCommand(Long memberId, String nickName) {

    }
}
