package com.devwinter.memberservice.adapter.output.persistence.member;

import com.devwinter.memberservice.domain.Profile;
import com.devwinter.memberservice.domain.ProfileCollection;
import lombok.Getter;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;

@Getter
@Embeddable
public class MemberProfileCollectionJpaEntity {

    @ElementCollection
    @CollectionTable(
            name = "member_profile",
            joinColumns = {
                    @JoinColumn(name = "member_id")
    })
    private List<MemberProfileJpaEntity> profiles = new ArrayList<>();

    public void addProfile(Profile profile) {
        this.profiles.add(new MemberProfileJpaEntity(profile.getPath(), profile.getType()));
    }
}
