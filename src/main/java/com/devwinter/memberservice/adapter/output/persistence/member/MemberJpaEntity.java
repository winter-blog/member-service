package com.devwinter.memberservice.adapter.output.persistence.member;

import com.devwinter.memberservice.adapter.output.persistence.BaseTimeEntity;
import com.devwinter.memberservice.domain.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class MemberJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickName;
    private String email;
    private String password;
    private LocalDateTime lastPasswordChangedAt;
    private String profile;
    private boolean deleted;
    private LocalDateTime deletedAt;

    public void updatePassword(Member member) {
        if (!this.password.equals(member.getPassword())) {
            this.password = member.getPassword();
            this.lastPasswordChangedAt = LocalDateTime.now();
        }
    }

    public void updateProfile(Member member) {
        if (!this.nickName.equals(member.getNickName())) {
            this.nickName = member.getNickName();
        }

        if (!this.profile.equals(member.getProfile().getPath())) {
            this.profile = member.getProfile().getPath();
        }
    }

    public void delete(Member member) {
        if(member.isDeleted() && !this.deleted) {
            this.deleted = true;
            this.deletedAt = LocalDateTime.now();
        }
    }
}
