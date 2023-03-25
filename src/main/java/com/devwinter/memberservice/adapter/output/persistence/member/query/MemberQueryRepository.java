package com.devwinter.memberservice.adapter.output.persistence.member.query;

import com.devwinter.memberservice.adapter.output.persistence.member.entity.MemberJpaEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MemberQueryRepository {
    Optional<MemberJpaEntity> findByMemberId(Long memberId);
    Optional<MemberJpaEntity> findByMemberEmail(String email);
    boolean existByEmail(String email);
    boolean existByNickname(String nickName);
    List<MemberJpaEntity> findByMemberIds(List<Long> memberIds);
}
