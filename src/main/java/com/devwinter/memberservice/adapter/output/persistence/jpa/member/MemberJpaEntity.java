package com.devwinter.memberservice.adapter.output.persistence.jpa.member;

import com.devwinter.memberservice.adapter.output.persistence.jpa.BaseTimeEntity;
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

    public void delete(Member member) {
        if (member.isDeleted() && !this.deleted) {
            this.deleted = true;
        }
    }
}
