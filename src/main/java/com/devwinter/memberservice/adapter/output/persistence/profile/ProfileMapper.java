package com.devwinter.memberservice.adapter.output.persistence.profile;

import com.devwinter.memberservice.domain.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {

    public Profile entityToDomain(ProfileJpaEntity profileJpaEntity) {
        if(profileJpaEntity == null) {
            return null;
        }

        return new Profile(profileJpaEntity.getPath());
    }
}
