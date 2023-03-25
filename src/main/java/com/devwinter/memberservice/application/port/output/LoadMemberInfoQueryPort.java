package com.devwinter.memberservice.application.port.output;

import com.devwinter.memberservice.domain.Member;

import java.util.List;
import java.util.Set;

public interface LoadMemberInfoQueryPort {
    List<Member> findByMemberIds(List<Long> memberIds);
}
