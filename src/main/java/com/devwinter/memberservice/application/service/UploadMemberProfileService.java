package com.devwinter.memberservice.application.service;

import com.devwinter.memberservice.application.port.input.UploadMemberProfileUseCase;
import com.devwinter.memberservice.application.port.output.LoadMemberQueryPort;
import com.devwinter.memberservice.application.port.output.UpdateMemberProfilePort;
import com.devwinter.memberservice.application.port.output.UploadProfileImagePort;
import com.devwinter.memberservice.domain.Member;
import com.devwinter.memberservice.domain.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UploadMemberProfileService implements UploadMemberProfileUseCase {

    private final LoadMemberQueryPort loadMemberQueryPort;
    private final UpdateMemberProfilePort updateMemberProfilePort;
    private final UploadProfileImagePort uploadProfileImagePort;

    @Override
    @Transactional
    public void addProfile(UploadMemberProfileCommand command) {
        Member member = loadMemberQueryPort.findByMemberId(command.memberId());

        Profile profile = new Profile(command.multipartFile().getOriginalFilename(), Profile.ProfileType.CUSTOM);
        member.addProfile(profile);
        updateMemberProfilePort.addMemberProfile(member.getId().value(), profile);

        uploadProfileImagePort.upload(profile, command.multipartFile());
    }
}
