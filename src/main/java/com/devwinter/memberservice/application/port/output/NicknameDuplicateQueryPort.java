package com.devwinter.memberservice.application.port.output;

public interface NicknameDuplicateQueryPort {
    boolean existByNickname(String nickName);
}
