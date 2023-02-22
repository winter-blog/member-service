package com.devwinter.memberservice.application.service;

import com.devwinter.memberservice.application.port.input.MyPageMemberQuery;
import com.devwinter.memberservice.application.port.output.LoadMemberQueryPort;
import com.devwinter.memberservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageMemberService implements MyPageMemberQuery {

    private final LoadMemberQueryPort loadMemberQueryPort;

    @Override
    public MyPageMemberDto getMemberInfo(Long memberId) {
        Member member = loadMemberQueryPort.findByMemberId(memberId);
        return MyPageMemberDto.of(member);
    }
}
