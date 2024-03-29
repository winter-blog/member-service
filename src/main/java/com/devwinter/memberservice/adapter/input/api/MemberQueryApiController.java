package com.devwinter.memberservice.adapter.input.api;

import com.devwinter.memberservice.adapter.input.api.dto.*;
import com.devwinter.memberservice.application.port.input.JoinDuplicateUseCase.EmailDuplicateCommand;
import com.devwinter.memberservice.application.port.input.MyPageMemberQuery;
import com.devwinter.memberservice.application.port.input.MyPageMemberQuery.MyPageMemberDto;
import com.devwinter.memberservice.application.port.input.JoinDuplicateUseCase;
import com.devwinter.memberservice.application.port.input.JoinDuplicateUseCase.NicknameDuplicateCommand;
import com.devwinter.memberservice.application.port.input.PasswordCheckSameUseCase;
import com.devwinter.memberservice.application.port.input.PasswordCheckSameUseCase.PasswordCheckCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberQueryApiController {

    private final MyPageMemberQuery myPageMemberQuery;
    private final JoinDuplicateUseCase joinDuplicateUseCase;
    private final PasswordCheckSameUseCase passwordCheckSameUseCase;

    @GetMapping("/my-page")
    public BaseResponse<MemberMyPage.Response> myPage(
            @RequestHeader("MemberId") Long memberId) {
        MyPageMemberDto memberInfo = myPageMemberQuery.getMemberInfo(memberId);
        return MemberMyPage.Response.success(memberInfo);
    }

    @PostMapping("/valid/password")
    public BaseResponse<Void> checkPassword(
            @RequestHeader("MemberId") Long memberId,
            @Valid @RequestBody ValidPassword.Request request) {
        passwordCheckSameUseCase.check(new PasswordCheckCommand(memberId, request.getPassword()));
        return BaseResponse.success();
    }

    @PostMapping("/check/nickname")
    public BaseResponse<Void> checkNickname(@Valid @RequestBody NicknameDuplicate.Request request) {
        joinDuplicateUseCase.nickNameCheck(new NicknameDuplicateCommand(request.getNickName()));
        return BaseResponse.success();
    }

    @PostMapping("/check/email")
    public BaseResponse<Void> checkEmail(@Valid @RequestBody EmailDuplicate.Request request) {
        joinDuplicateUseCase.emailCheck(new EmailDuplicateCommand(request.getEmail()));
        return BaseResponse.success();
    }
}
