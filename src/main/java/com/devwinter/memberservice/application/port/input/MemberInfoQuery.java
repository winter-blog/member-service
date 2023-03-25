package com.devwinter.memberservice.application.port.input;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MemberInfoQuery {
    Map<Long, MemberInfoDto> query(List<Long> memberIds);

    record MemberInfoDto(
            Long memberId,
            String nickName,
            String profile) {

    }
}
