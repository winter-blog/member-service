package com.devwinter.memberservice.adapter.output.persistence.member;

import com.devwinter.memberservice.domain.Profile;
import com.devwinter.memberservice.domain.Profile.ProfileType;
import lombok.*;

import javax.persistence.*;


@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberProfileJpaEntity {
    private String path;
    @Enumerated(EnumType.STRING)
    private ProfileType profileType;
}
