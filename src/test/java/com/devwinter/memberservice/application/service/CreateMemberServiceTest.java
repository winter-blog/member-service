package com.devwinter.memberservice.application.service;

import com.devwinter.memberservice.application.port.input.CreateMemberUseCase;
import com.devwinter.memberservice.application.port.output.LoadMemberPort;
import com.devwinter.memberservice.application.port.output.LoadTemplateProfilePort;
import com.devwinter.memberservice.application.port.output.SaveMemberPort;
import com.devwinter.memberservice.application.service.exception.MemberErrorCode;
import com.devwinter.memberservice.application.service.exception.MemberException;
import com.devwinter.memberservice.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class CreateMemberServiceTest {

    @Mock
    private LoadMemberPort loadMemberPort;
    @Mock
    private SaveMemberPort saveMemberPort;
    @Mock
    private LoadTemplateProfilePort loadTemplateProfilePort;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private CreateMemberService createMemberService;
    
    @Test
    @DisplayName("회원 생성 시 이메일이 등록된 경우 테스트")
    void createMemberDuplicateTest() {
        // given
        CreateMemberUseCase.CreateMemberCommand command = CreateMemberCommandFixture.complete();

        given(loadMemberPort.findByEmail(anyString()))
                .willReturn(Optional.of(mock(Member.class)));

        // when
        MemberException e = assertThrows(MemberException.class, () ->
                createMemberService.createMember(command));

        // then
        then(loadMemberPort).should().findByEmail(anyString());
        assertThat(e.getErrorCode()).isEqualTo(MemberErrorCode.MEMBER_DUPLICATE_EXCEPTION);
    }

    public static class CreateMemberCommandFixture {
        public static CreateMemberUseCase.CreateMemberCommand complete() {
            return new CreateMemberUseCase.CreateMemberCommand("nickName", "email", "password");
        }
    }
}