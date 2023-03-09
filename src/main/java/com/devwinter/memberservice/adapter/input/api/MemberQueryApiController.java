package com.devwinter.memberservice.adapter.input.api;

import com.devwinter.memberservice.adapter.input.api.dto.BaseResponse;
import com.devwinter.memberservice.adapter.input.api.dto.MemberMyPage;
import com.devwinter.memberservice.application.port.input.JoinDuplicateUseCase.EmailDuplicateCommand;
import com.devwinter.memberservice.application.port.input.MyPageMemberQuery;
import com.devwinter.memberservice.application.port.input.MyPageMemberQuery.MyPageMemberDto;
import com.devwinter.memberservice.application.port.input.JoinDuplicateUseCase;
import com.devwinter.memberservice.application.port.input.JoinDuplicateUseCase.NicknameDuplicateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberQueryApiController {

    private final MyPageMemberQuery myPageMemberQuery;
    private final JoinDuplicateUseCase joinDuplicateUseCase;

    @GetMapping("/my-page")
    public BaseResponse<MemberMyPage.Response> myPage(
            @RequestHeader("MemberId") Long memberId) {
        MyPageMemberDto memberInfo = myPageMemberQuery.getMemberInfo(memberId);
        return MemberMyPage.Response.success(memberInfo);
    }

    @GetMapping("/check/{nickName}/nickname")
    public BaseResponse<Void> checkNickname(@PathVariable String nickName) {
        joinDuplicateUseCase.nickNameCheck(new NicknameDuplicateCommand(nickName));
        return BaseResponse.success();
    }

    @GetMapping("/check/{email}/email")
    public BaseResponse<Void> checkEmail(@PathVariable String email) {
        joinDuplicateUseCase.emailCheck(new EmailDuplicateCommand(email));
        return BaseResponse.success();
    }
}
