package com.devwinter.memberservice.adapter.output.persistence.jpa.member;

import com.devwinter.memberservice.domain.Profile;
import com.devwinter.memberservice.domain.Profile.ProfileType;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member_profile")
public class MemberProfileJpaEntity {
    @Id
    @Column(name = "member_id")
    private Long id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "member_id")
    private MemberJpaEntity member;
    private String path;
    @Enumerated(EnumType.STRING)
    private ProfileType profileType;

    public MemberProfileJpaEntity(MemberJpaEntity member, Profile profile) {
        this.member = member;
        this.path = profile.getPath();
        this.profileType = profile.getType();
    }

    public void updateProfile() {

    }
}
