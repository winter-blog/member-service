package com.devwinter.memberservice.application.port.output;

import com.devwinter.memberservice.domain.Profile;

import java.util.Optional;

public interface LoadTemplateProfilePort {
    Optional<Profile> getRandomDefaultProfile();
}