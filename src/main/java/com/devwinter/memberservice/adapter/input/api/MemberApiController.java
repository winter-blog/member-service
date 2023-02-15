package com.devwinter.memberservice.adapter.input.api;

import com.devwinter.memberservice.adapter.input.api.dto.BaseResponse;
import com.devwinter.memberservice.adapter.input.api.dto.CreateMember;
import com.devwinter.memberservice.application.port.input.CreateMemberUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberApiController {

    private final CreateMemberUseCase memberUseCase;

    @PostMapping
    public BaseResponse<CreateMember.Response> createMember(@Valid @RequestBody CreateMember.Request request) {
        Long memberId = memberUseCase.createMember(request.toCommand());
        return CreateMember.Response.success(memberId);
    }
}
