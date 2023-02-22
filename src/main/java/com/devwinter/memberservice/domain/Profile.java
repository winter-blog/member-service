package com.devwinter.memberservice.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Profile {
    private final Long id;
    private final String path;
    private final ProfileType type;
    private final LocalDateTime createdAt;

    public Profile(String path, ProfileType type) {
        this.id = null;
        this.path = UUID.randomUUID() + "_" + path;
        this.type = type;
        this.createdAt = null;
    }

    public Profile(Long id, String path, ProfileType type, LocalDateTime createdAt) {
        this.id = id;
        this.path = UUID.randomUUID() + "_" + path;
        this.type = type;
        this.createdAt = createdAt;
    }

    public enum ProfileType {
        DEFAULT,
        CUSTOM
    }
}
