package com.devwinter.memberservice.application.port.output.history;

public interface DeleteMemberHistoryPort {
    void send(Long memberId);
}
