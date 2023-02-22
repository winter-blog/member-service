package com.devwinter.memberservice.adapter.output.persistence.member.entity;

import com.devwinter.memberservice.domain.Member;
import com.devwinter.memberservice.domain.Profile;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Embeddable
public class MemberProfileCollectionJpaEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "memberJpaEntity", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<MemberProfileJpaEntity> profiles = new ArrayList<>();

    public void addProfile(MemberJpaEntity member, Profile profile) {
        this.profiles.add(new MemberProfileJpaEntity(member, profile.getPath(), profile.getType()));
    }
}
