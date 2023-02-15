package com.devwinter.memberservice.adapter.output.persistence.profile;

import com.devwinter.memberservice.application.port.output.LoadProfilePort;
import com.devwinter.memberservice.domain.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProfilePersistenceAdapter implements LoadProfilePort {

    private final ProfileJpaEntityRepository profileRepository;
    private final ProfileMapper profileMapper;

    @Override
    public Optional<Profile> getRandomProfile() {
        long totalCount = profileRepository.count();
        Long randomImageNumber = (long) (Math.random() * totalCount) + 1;
        ProfileJpaEntity profileJpaEntity = profileRepository.findById(randomImageNumber).orElse(null);
        return Optional.ofNullable(profileMapper.entityToDomain(profileJpaEntity));
    }
}
