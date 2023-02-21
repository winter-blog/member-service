package com.devwinter.memberservice.adapter.output.persistence.member.command;

import com.devwinter.memberservice.adapter.output.persistence.member.entity.MemberJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaEntityRepository extends JpaRepository<MemberJpaEntity, Long> {
    Optional<MemberJpaEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
