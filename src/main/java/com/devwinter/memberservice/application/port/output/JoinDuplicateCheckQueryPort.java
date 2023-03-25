package com.devwinter.memberservice.application.port.output;

public interface JoinDuplicateCheckQueryPort {
    boolean existByNickname(String nickName);
    boolean existByEmail(String email);
}
