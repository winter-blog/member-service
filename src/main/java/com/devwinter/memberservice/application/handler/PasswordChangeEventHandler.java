package com.devwinter.memberservice.application.handler;

import com.devwinter.memberservice.application.port.output.PasswordChangeEventPort;
import com.devwinter.memberservice.domain.event.PasswordChangeEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class PasswordChangeEventHandler {
    private final PasswordChangeEventPort passwordChangeEventPort;

    @TransactionalEventListener(classes = PasswordChangeEvent.class, phase = TransactionPhase.AFTER_COMMIT)
    public void handle(PasswordChangeEvent event) {
        passwordChangeEventPort.send(event);
    }
}