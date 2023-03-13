package com.devwinter.memberservice.adapter.input.api;

import com.devwinter.memberservice.adapter.input.api.dto.*;
import com.devwinter.memberservice.application.port.input.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static com.devwinter.memberservice.adapter.input.api.AbstractRestDocs.FieldDescriptorDto.descriptor;
import static com.devwinter.memberservice.adapter.input.api.MemberApiDocumentInfo.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.request.RequestDocumentation.partWithName;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberCommandApiController.class)
class MemberCommandApiControllerTest extends AbstractRestDocs {
    @MockBean
    private CreateMemberUseCase createMemberUseCase;
    @MockBean
    private EditPasswordMemberUseCase editPasswordMemberUseCase;
    @MockBean
    private DeleteMemberUseCase deleteMemberUseCase;
    @MockBean
    private EditInfoMemberUseCase editInfoMemberUseCase;
    @MockBean
    private UploadMemberProfileUseCase uploadMemberProfileUseCase;

    private static final String BASE_URL = "/api/v1/members";

    @Test
    @DisplayName("회원가입 테스트")
    void createMemberApiTest() throws Exception {
        String body = requestToJson(new CreateMember.Request("nickName", "test@gamil.com", "aBcD2FG!"));

        // given
        given(createMemberUseCase.createMember(any())).willReturn(1L);

        // when & then
        mockMvc.perform(post(BASE_URL)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(body))
               .andDo(
                       document(
                               CREATE,
                               CreateMember.Request.class,
                               CreateMember.Response.class,
                               Arrays.asList(
                                       descriptor("email", STRING, "회원가입 하고자 하는 email"),
                                       descriptor("password", STRING, "회원가입 하고자 하는 password"),
                                       descriptor("nickName", STRING, "회원가입 하고자 하는 nickName")
                               ),
                               Arrays.asList(
                                       descriptor("result.status", STRING, "결과"),
                                       descriptor("result.message", STRING, "메세지"),
                                       descriptor("body.memberId", NUMBER, "회원 가입된 회원 ID")
                               )
                       )
               )
               .andExpect(status().isOk());
    }

    @Test
    @DisplayName("비밀번호 변경 테스트")
    void editPasswordApiTest() throws Exception {
        String body = requestToJson(new EditPasswordMember.Request("changePa!!2D"));

        // given
        doNothing().when(editPasswordMemberUseCase)
                   .editPassword(any());

        // when & then
        mockMvc.perform(patch(BASE_URL + "/edit-password")
                       .headers(auth())
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(body)
               )
               .andDo(
                       document(
                               CHANGE_PASSWORD,
                               EditPasswordMember.Request.class,
                               EditPasswordMember.Response.class,
                               List.of(
                                       descriptor("password", STRING, "변경할 패스워드")
                               ),
                               Arrays.asList(
                                       descriptor("result.status", STRING, "결과"),
                                       descriptor("result.message", STRING, "메세지")
                               )
                       )
               )
               .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원 삭제 테스트")
    void deleteMemberApiTest() throws Exception {

        // given
        doNothing().when(deleteMemberUseCase)
                   .delete(anyLong());

        // when & then
        mockMvc.perform(delete(BASE_URL)
                       .headers(auth())
                       .secure(true)
               )
               .andDo(
                       document(
                               DELETE,
                               null,
                               DeleteMember.Response.class,
                               null,
                               Arrays.asList(
                                       descriptor("result.status", STRING, "결과"),
                                       descriptor("result.message", STRING, "메세지")
                               )
                       )
               )
               .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원 정보 변경 테스트")
    void editMemberInfoApiTest() throws Exception {
        String body = requestToJson(new EditInfoMember.Request("nickname"));

        doNothing().when(editInfoMemberUseCase)
                   .edit(any());

        mockMvc.perform(patch(BASE_URL + "/edit-info")
                       .headers(auth())
                       .secure(true)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(body)
               )
               .andDo(
                       document(
                               EDIT_INFO,
                               EditInfoMember.Request.class,
                               EditInfoMember.Response.class,
                               List.of(
                                       descriptor("nickName", STRING, "변경할 닉네임")
                               ),
                               Arrays.asList(
                                       descriptor("result.status", STRING, "결과"),
                                       descriptor("result.message", STRING, "메세지")
                               )
                       )
               )
               .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원 프로필 이미지 업로드 테스트")
    void uploadProfileApiTest() throws Exception {
        String body = requestToJson(new EditInfoMember.Request("nickname"));

        doNothing().when(uploadMemberProfileUseCase)
                   .uploadProfile(any());
        MockMultipartFile profile = new MockMultipartFile("profile",
                "test.png",
                "image/png",
                "test file".getBytes(StandardCharsets.UTF_8));

        mockMvc.perform(
                       multipart(BASE_URL + "/upload-profile")
                               .file(profile)
                               .headers(auth())
                               .secure(true)
               )
               .andDo(
                       document(
                               UPLOAD_PROFILE,
                               AddProfileMember.Response.class,
                               List.of(
                                       partWithName("profile").description("프로필")
                               ),
                               Arrays.asList(
                                       descriptor("result.status", STRING, "결과"),
                                       descriptor("result.message", STRING, "메세지")
                               )
                       )
               )
               .andExpect(status().isOk());
    }
}

