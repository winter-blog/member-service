package com.devwinter.memberservice.adapter.output.persistence.member.query;

import com.devwinter.memberservice.adapter.output.persistence.member.entity.MemberMapper;
import com.devwinter.memberservice.application.port.output.LoadMemberQueryPort;
import com.devwinter.memberservice.application.service.exception.MemberException;
import com.devwinter.memberservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.devwinter.memberservice.application.service.exception.MemberErrorCode.MEMBER_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class LoadMemberQueryAdapter implements LoadMemberQueryPort {

    private final MemberQueryRepository memberQueryRepository;
    private final MemberMapper memberMapper;

    @Override
    public Member findByMemberId(Long memberId) {
        return memberMapper.entityToDomain(
                memberQueryRepository.findByMemberId(memberId)
                                     .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND))
        );
    }
}
