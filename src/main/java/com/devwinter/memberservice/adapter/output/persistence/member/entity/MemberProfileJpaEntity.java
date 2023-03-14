package com.devwinter.memberservice.adapter.output.persistence.member.entity;

import com.devwinter.memberservice.adapter.output.persistence.BaseTimeEntity;
import com.devwinter.memberservice.domain.Profile.ProfileType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member_profile")
public class MemberProfileJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_profile_id")
    private Long id;
    private String path;
    @Enumerated(EnumType.STRING)
    private ProfileType profileType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberJpaEntity memberJpaEntity;

    public MemberProfileJpaEntity(MemberJpaEntity memberJpaEntity, String path, ProfileType profileType) {
        this.memberJpaEntity = memberJpaEntity;
        this.path = path;
        this.profileType = profileType;
    }
}
