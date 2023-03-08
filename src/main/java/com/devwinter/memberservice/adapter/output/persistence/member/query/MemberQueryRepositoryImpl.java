package com.devwinter.memberservice.adapter.output.persistence.member.query;

import com.devwinter.memberservice.adapter.output.persistence.member.entity.MemberJpaEntity;
import com.devwinter.memberservice.application.service.exception.MemberErrorCode;
import com.devwinter.memberservice.application.service.exception.MemberException;
import com.devwinter.memberservice.domain.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.devwinter.memberservice.adapter.output.persistence.member.entity.QMemberJpaEntity.memberJpaEntity;
import static com.devwinter.memberservice.application.service.exception.MemberErrorCode.MEMBER_ALREADY_DELETE;


@Repository
@RequiredArgsConstructor
public class MemberQueryRepositoryImpl implements MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<MemberJpaEntity> findByMemberId(Long memberId) {
        MemberJpaEntity result = queryFactory
                .select(memberJpaEntity)
                .from(memberJpaEntity)
                .where(
                        memberJpaEntity.id.eq(memberId)
                )
                .fetchOne();

        deleteMemberValid(result);

        return Optional.ofNullable(result);
    }

    @Override
    public Optional<MemberJpaEntity> findByMemberEmail(String email) {
        MemberJpaEntity result = queryFactory
                .select(memberJpaEntity)
                .from(memberJpaEntity)
                .where(
                        memberJpaEntity.email.eq(email)
                )
                .fetchOne();

        deleteMemberValid(result);

        return Optional.ofNullable(result);
    }

    @Override
    public boolean existByEmail(String email) {

        return queryFactory
                .select(memberJpaEntity)
                .from(memberJpaEntity)
                .where(
                        memberJpaEntity.email.eq(email),
                        memberJpaEntity.deleted.isFalse()
                )
                .fetchOne() != null;
    }

    @Override
    public boolean existByNickname(String nickName) {
        return queryFactory
                .select(memberJpaEntity)
                .from(memberJpaEntity)
                .where(
                        memberJpaEntity.nickName.eq(nickName)
                )
                .fetchFirst() != null;
    }

    private void deleteMemberValid(MemberJpaEntity result) {
        if(result != null && result.isDeleted()) {
            throw new MemberException(MEMBER_ALREADY_DELETE);
        }
    }
}
