package com.devwinter.memberservice.adapter.input.api;

import com.devwinter.memberservice.adapter.input.api.dto.BaseResponse;
import com.devwinter.memberservice.adapter.input.api.dto.MemberMyPage;
import com.devwinter.memberservice.application.port.input.MyPageMemberQuery;
import com.devwinter.memberservice.application.port.input.MyPageMemberQuery.MyPageMemberDto;
import com.devwinter.memberservice.application.port.input.NicknameDuplicateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberQueryApiController {

    private final MyPageMemberQuery myPageMemberQuery;
    private final NicknameDuplicateUseCase nicknameDuplicateUseCase;

    @GetMapping("/my-page")
    public BaseResponse<MemberMyPage.Response> myPage(
            @RequestHeader("MemberId") Long memberId) {
        MyPageMemberDto memberInfo = myPageMemberQuery.getMemberInfo(memberId);
        return MemberMyPage.Response.success(memberInfo);
    }

    @GetMapping("/check/{nickName}/nickname")
    public BaseResponse<Void> checkNickname(@PathVariable String nickName) {
        nicknameDuplicateUseCase.check(nickName);
        return BaseResponse.success();
    }
}
