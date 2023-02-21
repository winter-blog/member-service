package com.devwinter.memberservice.adapter.input.api;

import com.devwinter.memberservice.adapter.input.api.dto.*;
import com.devwinter.memberservice.adapter.output.aws.DeleteProfileImageAdapter;
import com.devwinter.memberservice.application.port.input.CreateMemberUseCase;
import com.devwinter.memberservice.application.port.input.DeleteMemberUseCase;
import com.devwinter.memberservice.application.port.input.EditInfoMemberUseCase;
import com.devwinter.memberservice.application.port.input.EditInfoMemberUseCase.EditInfoMemberCommand;
import com.devwinter.memberservice.application.port.input.EditPasswordMemberUseCase;
import com.devwinter.memberservice.application.port.input.EditPasswordMemberUseCase.EditPasswordMemberCommand;
import com.devwinter.memberservice.domain.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberApiController {

    private final CreateMemberUseCase memberUseCase;
    private final EditPasswordMemberUseCase editPasswordMemberUseCase;
    private final DeleteMemberUseCase deleteMemberUseCase;
    private final EditInfoMemberUseCase editInfoMemberUseCase;

    @PostMapping
    public BaseResponse<CreateMember.Response> createMember(@Valid @RequestBody CreateMember.Request request) {
        Long memberId = memberUseCase.createMember(request.toCommand());
        return CreateMember.Response.success(memberId);
    }

    @PatchMapping("/edit-password")
    public BaseResponse<EditPasswordMember.Response> editPassword(
            @RequestHeader("MemberId") Long memberId,
            @Valid @RequestBody EditPasswordMember.Request request) {
        editPasswordMemberUseCase.editPassword(new EditPasswordMemberCommand(memberId, request.getPassword()));
        return EditPasswordMember.Response.success();
    }

    @DeleteMapping
    public BaseResponse<DeleteMember.Response> deleteMember(@RequestHeader("MemberId") Long memberId) {
        deleteMemberUseCase.delete(memberId);
        return DeleteMember.Response.success();
    }

    @PatchMapping("/edit-info")
    public BaseResponse<EditInfoMember.Response> editMemberInfo(
            @RequestHeader("MemberId") Long memberId,
            @Valid @RequestBody EditInfoMember.Request request) {
        editInfoMemberUseCase.edit(new EditInfoMemberCommand(memberId, request.getNickName()));
        return EditInfoMember.Response.success();
    }
}
