package com.devwinter.memberservice.adapter.output.persistence.member.entity;

import com.devwinter.memberservice.domain.Member;
import com.devwinter.memberservice.domain.Profile;
import com.devwinter.memberservice.domain.ProfileCollection;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MemberMapper {

    public MemberJpaEntity domainToEntity(Member member) {
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
                     .introduce(memberJpaEntity.getIntroduce())
                     .createdAt(memberJpaEntity.getCreatedAt())
                     .profiles(new ProfileCollection(memberJpaEntity.getProfiles().getProfiles().stream()
                                                                    .map(p -> new Profile(
                                                                            p.getId(),
                                                                            p.getPath(),
                                                                            p.getProfileType(),
                                                                            p.getCreatedAt(),
                                                                            p.isMain()))
                                                                    .collect(Collectors.toList())))
                     .deleted(memberJpaEntity.isDeleted())
                     .build();
    }
}
