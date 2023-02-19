package com.devwinter.memberservice.application.service;

import com.devwinter.memberservice.application.port.input.CreateMemberUseCase;
import com.devwinter.memberservice.application.port.output.LoadMemberPort;
import com.devwinter.memberservice.application.port.output.LoadTemplateProfilePort;
import com.devwinter.memberservice.application.port.output.SaveMemberPort;
import com.devwinter.memberservice.application.service.exception.MemberException;
import com.devwinter.memberservice.domain.Member;
import com.devwinter.memberservice.domain.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.devwinter.memberservice.application.service.exception.MemberErrorCode.*;

@Service
@RequiredArgsConstructor
public class CreateMemberService implements CreateMemberUseCase {

    private final LoadMemberPort loadMemberPort;
    private final SaveMemberPort saveMemberPort;
    private final LoadTemplateProfilePort loadTemplateProfilePort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long createMember(CreateMemberCommand command) {
        loadMemberPort.findByEmail(command.email()).ifPresent(m -> {
            throw new MemberException(MEMBER_DUPLICATE_EXCEPTION);
        });

        Profile profile = loadTemplateProfilePort.getRandomDefaultProfile()
                                                 .orElseThrow(() -> new MemberException(TEMPLATE_PROFILE_IMAGE_NOT_FOUND));

        String encrypt = passwordEncoder.encode(command.password());
        Member member = Member.withoutId(command.nickName(), command.email(), encrypt, profile);
        return saveMemberPort.save(member).getId().value();
    }
}
