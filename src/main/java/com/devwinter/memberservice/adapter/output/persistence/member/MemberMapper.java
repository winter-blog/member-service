package com.devwinter.memberservice.adapter.output.persistence.member;

import com.devwinter.memberservice.domain.Member;
import com.devwinter.memberservice.domain.Profile;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    public MemberJpaEntity createEntity(Member member) {
        return new MemberJpaEntity(member);
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
                     .profile(new Profile(
                             new Member.MemberId(memberJpaEntity.getId()),
                             memberJpaEntity.getMemberProfileJpaEntity().getPath(),
                             memberJpaEntity.getMemberProfileJpaEntity().getProfileType())
                     )
                     .deleted(memberJpaEntity.isDeleted())
                     .build();
    }
}
