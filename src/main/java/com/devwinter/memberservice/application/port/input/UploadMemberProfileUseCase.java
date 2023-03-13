package com.devwinter.memberservice.application.port.input;

import org.springframework.web.multipart.MultipartFile;

public interface UploadMemberProfileUseCase {
    void uploadProfile(UploadMemberProfileCommand command);

    record UploadMemberProfileCommand(Long memberId, MultipartFile multipartFile) {

    }
}
