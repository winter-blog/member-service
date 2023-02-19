package com.devwinter.memberservice.adapter.output.persistence.jpa.templateprofile;

import com.devwinter.memberservice.application.port.output.LoadTemplateProfilePort;
import com.devwinter.memberservice.domain.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TemplateTemplateProfilePersistenceAdapter implements LoadTemplateProfilePort {

    private final TemplateProfileJpaEntityRepository profileRepository;
    private final TemplateProfileMapper templateProfileMapper;

    @Override
    public Optional<Profile> getRandomDefaultProfile() {
        long totalCount = profileRepository.count();
        Long randomImageNumber = (long) (Math.random() * totalCount) + 1;
        TemplateProfileJpaEntity templateProfileJpaEntity = profileRepository.findById(randomImageNumber).orElse(null);
        return Optional.ofNullable(templateProfileMapper.entityToDomain(templateProfileJpaEntity));
    }
}
