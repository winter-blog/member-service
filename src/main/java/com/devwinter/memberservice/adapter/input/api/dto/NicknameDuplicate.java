package com.devwinter.memberservice.adapter.input.api.dto;

import com.devwinter.memberservice.adapter.input.api.valid.Nickname;
import lombok.Getter;

public class NicknameDuplicate {

    @Getter
    public static class Request {
        @Nickname
        private String nickName;
    }
}
