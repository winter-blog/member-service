package com.devwinter.memberservice.domain.event;

import lombok.Getter;

@Getter
public class PasswordChangeEvent {
    private final Long id;
    private final String email;

    public PasswordChangeEvent(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
