package com.devwinter.memberservice.adapter.output.persistence.member.query;

import com.devwinter.memberservice.adapter.output.persistence.member.entity.MemberJpaEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.devwinter.memberservice.adapter.output.persistence.member.entity.QMemberJpaEntity.memberJpaEntity;


@Repository
@RequiredArgsConstructor
public class MemberQueryRepositoryImpl implements MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<MemberJpaEntity> findByMemberId(Long memberId) {
        return Optional.ofNullable(
                queryFactory
                        .select(memberJpaEntity)
                        .from(memberJpaEntity)
                        .where(
                                memberJpaEntity.id.eq(memberId),
                                memberJpaEntity.deleted.isFalse()
                        )
                        .fetchOne()
        );
    }
}
