package com.devwinter.memberservice.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Profile {
    private final Long id;
    private String path;
    private final ProfileType type;
    private final LocalDateTime createdAt;

    public Profile(String path, ProfileType type) {
        this.id = null;
        this.type = type;
        this.createdAt = null;
        setPath(path, type);
    }

    public Profile(Long id, String path, ProfileType type, LocalDateTime createdAt) {
        this.id = id;
        this.type = type;
        this.createdAt = createdAt;
        this.path = path;
    }

    private String generateUUIDPath(String path) {
        return UUID.randomUUID() + "_" + path;
    }

    private void setPath(String path, ProfileType type) {
        if(type == ProfileType.CUSTOM) {
            this.path = generateUUIDPath(path);
        } else {
            this.path = path;
        }
    }

    public enum ProfileType {
        DEFAULT,
        CUSTOM
    }
}
