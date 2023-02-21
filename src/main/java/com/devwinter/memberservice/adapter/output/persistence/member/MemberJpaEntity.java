package com.devwinter.memberservice.adapter.output.persistence.member;

import com.devwinter.memberservice.adapter.output.persistence.BaseTimeEntity;
import com.devwinter.memberservice.domain.Member;
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
    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private MemberProfileJpaEntity memberProfileJpaEntity;

    public MemberJpaEntity(Member member) {
        if(member.getId() != null) {
            this.id = member.getId().value();
        }
        this.nickName = member.getNickName();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.deleted = false;
        this.memberProfileJpaEntity = new MemberProfileJpaEntity(this, member.getProfile());
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
}
