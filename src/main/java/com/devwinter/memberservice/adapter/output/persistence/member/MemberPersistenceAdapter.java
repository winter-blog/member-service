package com.devwinter.memberservice.adapter.output.persistence.member;

import com.devwinter.memberservice.application.port.output.LoadMemberPort;
import com.devwinter.memberservice.application.port.output.SaveMemberPort;
import com.devwinter.memberservice.application.port.output.UpdateMemberPort;
import com.devwinter.memberservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements LoadMemberPort, SaveMemberPort, UpdateMemberPort {

    private final MemberJpaEntityRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<Member> findById(Long memberId) {
        MemberJpaEntity memberJpaEntity = memberRepository.findById(memberId).orElse(null);
        return Optional.ofNullable(memberMapper.entityToDomain(memberJpaEntity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Member> findByEmail(String email) {
        MemberJpaEntity memberJpaEntity = memberRepository.findByEmail(email).orElse(null);
        return Optional.ofNullable(memberMapper.entityToDomain(memberJpaEntity));
    }

    @Override
    public Member save(Member member) {
        MemberJpaEntity memberJpaEntity = memberMapper.domainToEntity(member);
        memberJpaEntity = memberRepository.save(memberJpaEntity);
        return memberMapper.entityToDomain(memberJpaEntity);
    }

    @Override
    public void updatePassword(Member member) {
        MemberJpaEntity memberJpaEntity = memberRepository.findById(member.getId().value()).orElseThrow();
        memberJpaEntity.updatePassword(member.getPassword());
    }
}
