package com.devwinter.memberservice.adapter.output.persistence.templateprofile;

import com.devwinter.memberservice.domain.Profile;
import org.springframework.stereotype.Component;

@Component
public class TemplateProfileMapper {

    public Profile entityToDomain(TemplateProfileJpaEntity templateProfileJpaEntity) {
        return (templateProfileJpaEntity == null) ? null : new Profile(templateProfileJpaEntity.getPath(), Profile.ProfileType.DEFAULT);
    }
}