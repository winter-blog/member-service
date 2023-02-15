package com.devwinter.memberservice.application.port.input;

public interface CreateMemberUseCase {

    Long createMember(CreateMemberCommand command);

    record CreateMemberCommand(String nickName, String email, String password) {

    }
}
