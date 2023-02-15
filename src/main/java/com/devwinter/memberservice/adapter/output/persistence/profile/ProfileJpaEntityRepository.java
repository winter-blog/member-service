package com.devwinter.memberservice.adapter.output.persistence.profile;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileJpaEntityRepository extends JpaRepository<ProfileJpaEntity, Long> {
}
