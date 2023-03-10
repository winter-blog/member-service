package com.devwinter.memberservice.application.service;

import com.devwinter.memberservice.application.port.input.EditInfoMemberUseCase;
import com.devwinter.memberservice.application.port.output.LoadMemberQueryPort;
import com.devwinter.memberservice.application.port.output.UpdateInfoMemberPort;
import com.devwinter.memberservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EditInfoMemberService implements EditInfoMemberUseCase {

    private final LoadMemberQueryPort loadMemberQueryPort;
    private final UpdateInfoMemberPort updateInfoMemberPort;

    @Override
    public void edit(EditInfoMemberCommand command) {
        Member member = loadMemberQueryPort.findByMemberId(command.memberId());

        member.editInfo(command.nickName());
        updateInfoMemberPort.updateMemberInfo(member);
    }
}
