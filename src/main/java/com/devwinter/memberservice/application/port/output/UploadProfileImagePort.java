package com.devwinter.memberservice.application.port.output;

import com.devwinter.memberservice.domain.Profile;
import org.springframework.web.multipart.MultipartFile;

public interface UploadProfileImagePort {
    void upload(Profile profile,  MultipartFile multipartFile);
}
