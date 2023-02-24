package com.devwinter.memberservice.adapter.output.persistence.member.command;

import com.devwinter.memberservice.adapter.output.persistence.member.entity.MemberJpaEntity;
import com.devwinter.memberservice.adapter.output.persistence.member.entity.MemberMapper;
import com.devwinter.memberservice.application.port.output.*;
import com.devwinter.memberservice.application.service.exception.MemberErrorCode;
import com.devwinter.memberservice.application.service.exception.MemberException;
import com.devwinter.memberservice.domain.Member;
import com.devwinter.memberservice.domain.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements SaveMemberPort, UpdatePasswordMemberPort, DeleteMemberPort, UpdateInfoMemberPort, UpdateMemberProfilePort {

    private final MemberJpaEntityRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    public Member save(Member member) {
        MemberJpaEntity memberJpaEntity = memberMapper.domainToEntity(member);
        memberJpaEntity = memberRepository.save(memberJpaEntity);
        return memberMapper.entityToDomain(memberJpaEntity);
    }

    @Override
    public void updatePassword(Member member) {
        MemberJpaEntity memberJpaEntity = findMemberById(member.getId().value());
        memberJpaEntity.updatePassword(member);
    }

    @Override
    public void delete(Member member) {
        MemberJpaEntity memberJpaEntity = findMemberById(member.getId().value());
        memberJpaEntity.delete(member);
    }

    @Override
    public void updateMemberInfo(Member member) {
        MemberJpaEntity memberJpaEntity = findMemberById(member.getId().value());
        memberJpaEntity.updateInfo(member);
    }

    @Override
    public void addMemberProfile(Long memberId, Profile profile) {
        MemberJpaEntity memberJpaEntity = findMemberById(memberId);
        memberJpaEntity.addProfile(profile);
    }

    private MemberJpaEntity findMemberById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow();
    }
}
