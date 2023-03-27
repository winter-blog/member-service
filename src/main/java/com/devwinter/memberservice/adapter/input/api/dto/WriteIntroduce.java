package com.devwinter.memberservice.adapter.input.api.dto;

import com.devwinter.memberservice.adapter.input.api.valid.Introduce;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class WriteIntroduce {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        @Introduce
        private String introduce;
    }

    public static class Response {

        public static BaseResponse<WriteIntroduce.Response> success() {
            return BaseResponse.success();
        }
    }
}
