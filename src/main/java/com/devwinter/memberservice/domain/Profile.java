package com.devwinter.memberservice.domain;

import com.devwinter.memberservice.domain.Member.MemberId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Profile {
    private MemberId memberId;
    private String path;
    private ProfileType type;

    public enum ProfileType {
        DEFAULT,
        CUSTOM
    }
}
