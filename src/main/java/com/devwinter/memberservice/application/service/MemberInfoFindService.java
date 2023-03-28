package com.devwinter.memberservice.application.service;

import com.devwinter.memberservice.application.port.input.MemberInfoQuery;
import com.devwinter.memberservice.application.port.output.LoadMemberQueryPort;
import com.devwinter.memberservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberInfoFindService implements MemberInfoQuery {
    private final LoadMemberQueryPort loadMemberQueryPort;
    @Value("${cloud.aws.s3.base-url}")
    private String baseUrl;
    @Value("${cloud.aws.s3.profile-prefix}")
    private String basePrefix;
    @Override
    @Transactional(readOnly = true)
    public Map<Long, MemberInfoDto> query(List<Long> memberIds) {
        List<Member> members = loadMemberQueryPort.findByMemberIds(memberIds);

        return members.stream()
                      .collect(Collectors.toMap(
                              obj -> obj.getId().value(),
                              obj -> new MemberInfoDto(
                                      obj.getId().value(),
                                      obj.getNickName(),
                                      getProfileFullPath(obj.getProfiles().getMainProfilePath())))
                      );
    }

    @Override
    public MemberInfoDto query(Long memberId) {
        Member member = loadMemberQueryPort.findByMemberId(memberId);
        return new MemberInfoDto(member.getId()
                                       .value(), member.getNickName(), getProfileFullPath(member.getProfiles().getMainProfilePath()));
    }

    private String getProfileFullPath(String profilePath) {
        return baseUrl + basePrefix + profilePath;
    }
}
