package com.devwinter.memberservice.application.service;

import com.devwinter.memberservice.application.port.input.MyPageMemberQuery;
import com.devwinter.memberservice.application.port.output.LoadMemberQueryPort;
import com.devwinter.memberservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageMemberService implements MyPageMemberQuery {

    private final LoadMemberQueryPort loadMemberQueryPort;
    @Value("${cloud.aws.s3.base-url}")
    private String baseUrl;
    @Value("${cloud.aws.s3.profile-prefix}")
    private String basePrefix;
    @Override
    public MyPageMemberDto getMemberInfo(Long memberId) {
        Member member = loadMemberQueryPort.findByMemberId(memberId);
        return MyPageMemberDto.of(member, baseUrl + basePrefix);
    }
}
