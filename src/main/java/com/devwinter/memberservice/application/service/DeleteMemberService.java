package com.devwinter.memberservice.application.service;

import com.devwinter.memberservice.application.port.output.DeleteMemberPort;
import com.devwinter.memberservice.application.port.output.history.DeleteMemberHistoryPort;
import com.devwinter.memberservice.application.service.exception.MemberErrorCode;
import com.devwinter.memberservice.application.service.exception.MemberException;
import com.devwinter.memberservice.application.port.input.DeleteMemberUseCase;
import com.devwinter.memberservice.application.port.output.LoadMemberPort;
import com.devwinter.memberservice.application.port.output.UpdatePasswordMemberPort;
import com.devwinter.memberservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteMemberService implements DeleteMemberUseCase {

    private final LoadMemberPort loadMemberPort;
    private final DeleteMemberPort deleteMemberPort;
    // private final DeleteMemberHistoryPort deleteMemberHistoryPort;

    @Override
    @Transactional
    public void delete(Long memberId) {
        Member member = loadMemberPort.findById(memberId);
        member.delete();
        deleteMemberPort.delete(member);

        // deleteMemberHistoryPort.send(memberId);
    }
}
