package com.devwinter.memberservice.adapter.input.api.dto;

import com.devwinter.memberservice.adapter.input.api.valid.Nickname;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class NicknameDuplicate {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @Nickname
        private String nickName;
    }
}
