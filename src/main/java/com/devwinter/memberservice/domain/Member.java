package com.devwinter.memberservice.domain;

import com.devwinter.memberservice.application.service.exception.MemberException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.devwinter.memberservice.application.service.exception.MemberErrorCode.*;


@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {
    private MemberId id;
    private String nickName;
    private String email;
    private String password;
    private ProfileCollection profiles;
    private boolean deleted;

    public void changePassword(String newPassword) {
        if (this.password.equals(newPassword)) {
            throw new MemberException(MEMBER_PASSWORD_SAME);
        }
        this.password = newPassword;
    }

    public void delete() {
        if(this.deleted) {
            throw new MemberException(MEMBER_ALREADY_DELETE);
        }
        this.deleted = true;
    }

    public void editInfo(String nickName) {
        if(this.nickName.equals(nickName)) {
            throw new MemberException(MEMBER_NICKNAME_SAME);
        }
        this.nickName = nickName;
    }

    public void addProfile(Profile profile) {
        if(this.profiles == null) {
            this.profiles = new ProfileCollection(new ArrayList<>());
        }
        this.profiles.addProfile(profile);
    }

    public record MemberId(Long value) {
    }
}
