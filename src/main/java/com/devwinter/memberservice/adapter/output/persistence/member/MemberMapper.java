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
                null,
                (member.getProfile() == null) ? null : member.getProfile().getPath()
        );
    }

    public Member entityToDomain(MemberJpaEntity memberJpaEntity) {
        if(memberJpaEntity == null) {
            return null;
        }

        return Member.withId(
                new Member.MemberId(memberJpaEntity.getId()),
                memberJpaEntity.getNickName(),
                memberJpaEntity.getEmail(),
                memberJpaEntity.getPassword(),
                new Profile(memberJpaEntity.getProfile())
        );
    }
}
