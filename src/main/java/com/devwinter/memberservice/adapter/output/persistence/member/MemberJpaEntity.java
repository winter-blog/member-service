package com.devwinter.memberservice.adapter.output.persistence.member;

import com.devwinter.memberservice.adapter.output.persistence.BaseTimeEntity;
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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickName;
    private String email;
    private String password;
    private LocalDateTime lastPasswordChangedAt;
    private String profile;

    public void updatePassword(String newPassword) {
        this.password = newPassword;
        this.lastPasswordChangedAt = LocalDateTime.now();
    }
}
