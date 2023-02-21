package com.devwinter.memberservice.domain;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Profile {
    private final String path;
    private final ProfileType type;

    public Profile(String path, ProfileType type) {
        this.path = UUID.randomUUID() + "_" + path;
        this.type = type;
    }

    public enum ProfileType {
        DEFAULT,
        CUSTOM
    }
}
