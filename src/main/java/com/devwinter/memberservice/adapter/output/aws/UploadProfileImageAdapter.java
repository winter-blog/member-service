package com.devwinter.memberservice.adapter.output.aws;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.devwinter.memberservice.application.port.output.UploadProfileImagePort;
import com.devwinter.memberservice.domain.Profile;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class UploadProfileImageAdapter implements UploadProfileImagePort {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.profile-prefix}")
    private String profilePrefix;
    private final AmazonS3Client amazonS3Client;

    @Override
    public void upload(Profile profile, MultipartFile multipartFile) {
        if (multipartFile != null) {
            try {
                ObjectMetadata objectMetadata = getObjectMetadata(multipartFile);
                try (InputStream inputStream = multipartFile.getInputStream()) {
                    amazonS3Client.putObject(new PutObjectRequest(bucket, profilePrefix + profile.getPath(), inputStream, objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead));
                } catch (IOException e) {
                    throw new FileUploadException();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private ObjectMetadata getObjectMetadata(MultipartFile multipartFile) throws IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getInputStream()
                                                     .available());
        String fileExtension = multipartFile.getOriginalFilename()
                                            .substring(multipartFile.getOriginalFilename()
                                                                    .lastIndexOf(".") + 1)
                                            .toLowerCase();
        String contentTypeTail = "jpeg";
        if (fileExtension.equals("gif")) contentTypeTail = "gif";
        else if (fileExtension.equals("png")) contentTypeTail = "png";
        objectMetadata.setContentType("image/" + contentTypeTail);
        return objectMetadata;
    }
}
