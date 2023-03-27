package com.devwinter.memberservice.application.port.input;

public interface IntroduceWriteUseCase {
    void write(Long memberId, String introduce);
}
