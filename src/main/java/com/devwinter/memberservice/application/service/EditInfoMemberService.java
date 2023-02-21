package com.devwinter.memberservice.application.service;

import com.devwinter.memberservice.application.port.input.EditInfoMemberUseCase;
import com.devwinter.memberservice.application.port.output.LoadMemberPort;
import com.devwinter.memberservice.application.port.output.UpdateInfoMemberPort;
import com.devwinter.memberservice.application.service.exception.MemberErrorCode;
import com.devwinter.memberservice.application.service.exception.MemberException;
import com.devwinter.memberservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EditInfoMemberService implements EditInfoMemberUseCase {

    private final LoadMemberPort loadMemberPort;
    private final UpdateInfoMemberPort updateInfoMemberPort;

    @Override
    public void edit(EditInfoMemberCommand command) {
        Member member = loadMemberPort.findById(command.memberId());

        member.editInfo(command.nickName());
        updateInfoMemberPort.update(member);
    }
}
