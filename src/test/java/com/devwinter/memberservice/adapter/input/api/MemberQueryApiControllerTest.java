package com.devwinter.memberservice.adapter.input.api;

import com.devwinter.memberservice.adapter.input.api.dto.*;
import com.devwinter.memberservice.application.port.input.JoinDuplicateUseCase;
import com.devwinter.memberservice.application.port.input.MyPageMemberQuery;
import com.devwinter.memberservice.application.port.input.PasswordCheckSameUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.devwinter.memberservice.adapter.input.api.AbstractRestDocs.FieldDescriptorDto.descriptor;
import static com.devwinter.memberservice.adapter.input.api.MemberApiDocumentInfo.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberQueryApiController.class)
class MemberQueryApiControllerTest extends AbstractRestDocs {

    @MockBean
    private MyPageMemberQuery myPageMemberQuery;

    @MockBean
    private JoinDuplicateUseCase joinDuplicateUseCase;

    @MockBean
    private PasswordCheckSameUseCase passwordCheckSameUseCase;

    @Test
    @DisplayName("이메일 중복 검사 테스트")
    void checkEmailApiTest() throws Exception {
        // given
        String body = requestToJson(new EmailDuplicate.Request("test@gamil.com"));


        doNothing().when(joinDuplicateUseCase)
                   .emailCheck(any());

        // when & then
        mockMvc.perform(post(BASE_URL + "/check/email")
                       .contentType(APPLICATION_JSON)
                       .content(body)
               )
               .andDo(
                       document(
                               CHECK_EMAIL,
                               EmailDuplicate.Request.class,
                               null,
                               List.of(
                                       descriptor("email", STRING, "이메일")
                               ),
                               null
                       )
               )
               .andExpect(status().isOk());
    }

    @Test
    @DisplayName("닉네임 중복 검사 테스트")
    void checkNicknameApiTest() throws Exception {
        // given
        String body = requestToJson(new NicknameDuplicate.Request("nickName"));

        doNothing().when(joinDuplicateUseCase)
                   .nickNameCheck(any());

        // when & then
        mockMvc.perform(post(BASE_URL + "/check/nickname")
                       .contentType(APPLICATION_JSON)
                       .content(body)
               )
               .andDo(
                       document(
                               CHECK_NICKNAME,
                               NicknameDuplicate.Request.class,
                               null,
                               List.of(
                                       descriptor("nickName", STRING, "닉네임")
                               ),
                               null
                       )
               )
               .andExpect(status().isOk());
    }

    @Test
    @DisplayName("비밀번호 동일 여부 검사 테스트")
    void checkPasswordApiTest() throws Exception {
        // given
        String body = requestToJson(new ValidPassword.Request("abcd@d2G1&"));

        doNothing().when(passwordCheckSameUseCase)
                   .check(any());

        // when & then
        mockMvc.perform(post(BASE_URL + "/valid/password")
                       .headers(auth())
                       .contentType(APPLICATION_JSON)
                       .content(body)
               )
               .andDo(
                       document(
                               CHECK_PASSWORD,
                               ValidPassword.Request.class,
                               null,
                               List.of(
                                       descriptor("password", STRING, "비밀번호")
                               ),
                               null
                       )
               )
               .andExpect(status().isOk());
    }

    @Test
    @DisplayName("마이페이지 조회 테스트")
    void myPageApiTest() throws Exception {
        // given
        MyPageMemberQuery.MyPageMemberDto myPageMemberDto = new MyPageMemberQuery.MyPageMemberDto(
                1L,
                "test@gmail.com",
                "nickName",
                Arrays.asList(
                        new MyPageMemberQuery.ProfileDto("image1.png", LocalDateTime.now()),
                        new MyPageMemberQuery.ProfileDto("image2.png", LocalDateTime.now())
                )
        );

        given(myPageMemberQuery.getMemberInfo(anyLong()))
                .willReturn(myPageMemberDto);

        // when & then
        mockMvc.perform(get(BASE_URL + "/my-page")
                       .headers(auth())
               )
               .andDo(
                       document(
                               MY_PAGE,
                               null,
                               MemberMyPage.Response.class,
                               null,
                               List.of(
                                       descriptor("result.status", STRING, "결과"),
                                       descriptor("body.memberId", NUMBER, "회원 id"),
                                       descriptor("body.email", STRING, "이메일"),
                                       descriptor("body.nickName", STRING, "닉네임"),
                                       descriptor("body.profiles", ARRAY, "프로필 목록"),
                                       descriptor("body.profiles[].path", STRING, "프로필 경로"),
                                       descriptor("body.profiles[].createdAt", STRING, "프로필 생성 시간")
                               )
                       )
               )
               .andExpect(status().isOk());
    }
}