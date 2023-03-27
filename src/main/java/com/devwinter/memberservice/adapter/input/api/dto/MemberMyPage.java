package com.devwinter.memberservice.adapter.input.api.dto;

import com.devwinter.memberservice.application.port.input.MyPageMemberQuery.MyPageMemberDto;
import com.devwinter.memberservice.application.port.input.MyPageMemberQuery.ProfileDto;
import com.devwinter.memberservice.common.StringConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MemberMyPage {
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {

        private Long memberId;
        private String email;
        private String nickName;
        private String introduce;
        private String createdAt;
        private List<MemberProfileResponse> profiles;

        public static BaseResponse<MemberMyPage.Response> success(MyPageMemberDto memberDto) {

            return BaseResponse.success(new Response(
                    memberDto.memberId(),
                    memberDto.email(),
                    memberDto.nickName(),
                    memberDto.introduce(),
                    StringConverter.localDateTimeToLocalDateString(memberDto.createdAt()),
                    memberDto.profiles().stream().map(MemberProfileResponse::of)
                            .collect(Collectors.toList())
            ));
        }
    }

    @Getter
    @AllArgsConstructor
    private static class MemberProfileResponse {

        private String path;


        public static MemberProfileResponse of(ProfileDto dto) {
            return new MemberProfileResponse(dto.path());
        }

    }
}
