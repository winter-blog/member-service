package com.devwinter.memberservice.adapter.output.persistence.member;

import com.devwinter.memberservice.domain.Member;
import com.devwinter.memberservice.domain.Profile;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    public MemberJpaEntity domainToEntity(Member member) {
        return new MemberJpaEntity(
                (member.getId() == null) ? null : member.getId().value(),
                member.getNickName(),
                member.getEmail(),
                member.getPassword(),
                member.getLastPasswordChangedAt(),
                (member.getProfile() == null) ? null : member.getProfile().getPath(),
                member.isDeleted(),
                member.getDeletedAt()
        );
    }

    public Member entityToDomain(MemberJpaEntity memberJpaEntity) {
        if (memberJpaEntity == null) {
            return null;
        }

        return Member.builder()
                     .id(new Member.MemberId(memberJpaEntity.getId()))
                     .nickName(memberJpaEntity.getNickName())
                     .email(memberJpaEntity.getEmail())
                     .password(memberJpaEntity.getPassword())
                     .lastPasswordChangedAt(memberJpaEntity.getLastPasswordChangedAt())
                     .profile(new Profile(memberJpaEntity.getProfile()))
                     .deleted(memberJpaEntity.isDeleted())
                     .deletedAt(memberJpaEntity.getDeletedAt())
                     .build();
    }
}
