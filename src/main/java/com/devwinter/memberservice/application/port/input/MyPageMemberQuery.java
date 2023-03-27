package com.devwinter.memberservice.application.port.input;

import com.devwinter.memberservice.common.StringConverter;
import com.devwinter.memberservice.domain.Member;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public interface MyPageMemberQuery {

    MyPageMemberDto getMemberInfo(Long memberId);

    record MyPageMemberDto(Long memberId, String email, String nickName, String introduce, LocalDateTime createdAt, List<ProfileDto> profiles) {
        public static MyPageMemberDto of(Member member, String baseUrl) {
            return new MyPageMemberDto(
                    member.getId().value(),
                    member.getEmail(),
                    member.getNickName(),
                    member.getIntroduce(),
                    member.getCreatedAt(),
                    member.getProfiles()
                          .getProfiles()
                          .stream()
                          .map(p -> new ProfileDto(baseUrl + p.getPath(), p.getCreatedAt()))
                          .collect(Collectors.toList())

            );
        }
    }

    record ProfileDto(String path, LocalDateTime createdAt) {

    }
}
