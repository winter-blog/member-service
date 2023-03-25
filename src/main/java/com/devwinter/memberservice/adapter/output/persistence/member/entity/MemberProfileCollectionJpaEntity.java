package com.devwinter.memberservice.adapter.output.persistence.member.entity;

import com.devwinter.memberservice.domain.Member;
import com.devwinter.memberservice.domain.Profile;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberProfileCollectionJpaEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "memberJpaEntity", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<MemberProfileJpaEntity> profiles = new ArrayList<>();

    public void addProfile(MemberJpaEntity member, Profile profile) {
        this.profiles.add(new MemberProfileJpaEntity(member, profile.getPath(), profile.getType(), profile.isMain()));
    }
}
