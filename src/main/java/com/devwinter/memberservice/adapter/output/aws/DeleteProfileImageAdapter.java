package com.devwinter.memberservice.adapter.output.aws;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.devwinter.memberservice.domain.Profile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteProfileImageAdapter {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    @Value("${cloud.aws.s3.profile-prefix}")
    private String profilePrefix;
    private final AmazonS3Client amazonS3Client;

    public void deleteProfileImage(Profile editProfile) {
        if (Objects.nonNull(editProfile)) {
            try {
                if(editProfile.getType().equals(Profile.ProfileType.CUSTOM)) {
                    amazonS3Client.deleteObject(bucket, profilePrefix + editProfile.getPath());
                }
            } catch (AmazonServiceException e) {
                log.error("AWS S3 Profile File Delete fail: ", e);
            }
        }
    }
}
