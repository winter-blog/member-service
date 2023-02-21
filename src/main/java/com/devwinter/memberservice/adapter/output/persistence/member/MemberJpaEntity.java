package com.devwinter.memberservice.adapter.output.persistence.member;

import com.devwinter.memberservice.adapter.output.persistence.BaseTimeEntity;
import com.devwinter.memberservice.domain.Member;
import com.devwinter.memberservice.domain.Profile;
import com.devwinter.memberservice.domain.ProfileCollection;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class MemberJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String nickName;
    private String email;
    private String password;
    private boolean deleted;
    @Embedded
    private MemberProfileCollectionJpaEntity profiles;

    public MemberJpaEntity(Member member) {
        if(member.getId() != null) {
            this.id = member.getId().value();
        }
        this.nickName = member.getNickName();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.deleted = false;
        addProfileCollection(member.getProfiles());
    }

    public void updatePassword(Member member) {
        if (!this.password.equals(member.getPassword())) {
            this.password = member.getPassword();
        }
    }

    public void updateInfo(Member member) {
        if(!this.nickName.equals(member.getNickName())) {
            this.nickName = member.getNickName();
        }
    }

    public void delete(Member member) {
        if (member.isDeleted() && !this.deleted) {
            this.deleted = true;
        }
    }

    private void addProfileCollection(ProfileCollection profileCollection) {
        if(this.profiles == null) {
            this.profiles = new MemberProfileCollectionJpaEntity();
        }
        for (Profile profile : profileCollection.getProfiles()) {
            this.profiles.addProfile(profile);
        }
    }

    public void addProfile(Profile profile) {
        this.profiles.addProfile(profile);
    }
}
