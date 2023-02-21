package com.devwinter.memberservice.adapter.output.persistence.member;

import com.devwinter.memberservice.application.port.output.*;
import com.devwinter.memberservice.application.service.exception.MemberErrorCode;
import com.devwinter.memberservice.application.service.exception.MemberException;
import com.devwinter.memberservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements LoadMemberPort, SaveMemberPort, UpdatePasswordMemberPort, DeleteMemberPort, UpdateInfoMemberPort {

    private final MemberJpaEntityRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    public boolean existByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Member findById(Long memberId) {
        MemberJpaEntity memberJpaEntity = memberRepository.findById(memberId)
                                                          .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return memberMapper.entityToDomain(memberJpaEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Member findByEmail(String email) {
        MemberJpaEntity memberJpaEntity = memberRepository.findByEmail(email)
                                                          .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return memberMapper.entityToDomain(memberJpaEntity);
    }

    @Override
    public Member save(Member member) {
        MemberJpaEntity memberJpaEntity = memberMapper.createEntity(member);
        memberJpaEntity = memberRepository.save(memberJpaEntity);
        return memberMapper.entityToDomain(memberJpaEntity);
    }

    @Override
    public void updatePassword(Member member) {
        MemberJpaEntity memberJpaEntity = memberRepository.findById(member.getId().value()).orElseThrow();
        memberJpaEntity.updatePassword(member);
    }

    @Override
    public void delete(Member member) {
        MemberJpaEntity memberJpaEntity = memberRepository.findById(member.getId().value()).orElseThrow();
        memberJpaEntity.delete(member);
    }

    @Override
    public void update(Member member) {
        MemberJpaEntity memberJpaEntity = memberRepository.findById(member.getId().value()).orElseThrow();
        memberJpaEntity.updateInfo(member);
    }
}
