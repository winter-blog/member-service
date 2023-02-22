package com.devwinter.memberservice.adapter.output.persistence.member.query;

import com.devwinter.memberservice.adapter.output.persistence.member.entity.MemberJpaEntity;

import java.util.Optional;

public interface MemberQueryRepository {
    Optional<MemberJpaEntity> findByMemberId(Long memberId);
}
