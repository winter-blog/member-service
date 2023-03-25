package com.devwinter.memberservice.adapter.input.api;

import com.devwinter.memberservice.adapter.input.api.dto.MemberInfo;
import com.devwinter.memberservice.application.port.input.MemberInfoQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/members/internal")
@RequiredArgsConstructor
public class MemberInfoQueryApiController {

    private final MemberInfoQuery memberInfoQuery;

    @GetMapping("/info/{memberIds}")
    public Map<Long, MemberInfoQuery.MemberInfoDto> info(@PathVariable List<Long> memberIds) {
        return memberInfoQuery.query(memberIds);
    }
}
