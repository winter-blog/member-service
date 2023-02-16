package com.devwinter.memberservice.application.service;

import com.devwinter.memberservice.application.exception.MemberErrorCode;
import com.devwinter.memberservice.application.exception.MemberException;
import com.devwinter.memberservice.application.port.input.DeleteMemberUseCase;
import com.devwinter.memberservice.application.port.output.LoadMemberPort;
import com.devwinter.memberservice.application.port.output.UpdateMemberPort;
import com.devwinter.memberservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteMemberService implements DeleteMemberUseCase {

    private final LoadMemberPort loadMemberPort;
    private final UpdateMemberPort updateMemberPort;

    @Override
    @Transactional
    public void delete(Long memberId) {
        Member member = loadMemberPort.findById(memberId)
                                      .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        member.delete();
        updateMemberPort.delete(member);
    }
}
