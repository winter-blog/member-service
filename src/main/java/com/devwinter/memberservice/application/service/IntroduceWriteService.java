package com.devwinter.memberservice.application.service;

import com.devwinter.memberservice.application.port.input.IntroduceWriteUseCase;
import com.devwinter.memberservice.application.port.output.LoadMemberQueryPort;
import com.devwinter.memberservice.application.port.output.SaveMemberPort;
import com.devwinter.memberservice.application.port.output.UpdateInfoMemberPort;
import com.devwinter.memberservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IntroduceWriteService implements IntroduceWriteUseCase {


    private final LoadMemberQueryPort loadMemberQueryPort;
    private final UpdateInfoMemberPort updateInfoMemberPort;

    @Override
    @Transactional
    public void write(Long memberId, String introduce) {
        Member member = loadMemberQueryPort.findByMemberId(memberId);

        member.writeIntroduce(introduce);
        updateInfoMemberPort.updateIntroduce(member);
    }
}
