package com.devwinter.memberservice.application.port.output;

import com.devwinter.memberservice.domain.event.PasswordChangeEvent;

public interface PasswordChangeEventPort {
    void send(PasswordChangeEvent event);
}
