package com.devwinter.memberservice.application.service;

import com.devwinter.memberservice.application.port.input.MemberInfoQuery;
import com.devwinter.memberservice.application.port.output.LoadMemberInfoQueryPort;
import com.devwinter.memberservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberInfoFindService implements MemberInfoQuery {

    private final LoadMemberInfoQueryPort loadMemberInfoQueryPort;
    @Value("${cloud.aws.s3.base-url}")
    private String baseUrl;
    @Value("${cloud.aws.s3.profile-prefix}")
    private String basePrefix;
    @Override
    @Transactional(readOnly = true)
    public Map<Long, MemberInfoDto> query(List<Long> memberIds) {
        List<Member> members = loadMemberInfoQueryPort.findByMemberIds(memberIds);

        return members.stream()
                      .collect(Collectors.toMap(
                              obj -> obj.getId().value(),
                              obj -> new MemberInfoDto(
                                      obj.getId().value(),
                                      obj.getNickName(),
                                      baseUrl + basePrefix + obj.getProfiles().getMainProfilePath()))
                      );
    }
}
