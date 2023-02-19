package com.devwinter.memberservice.adapter.input.api.dto;

public class EditInfoMember {
    public static class Request {
        private String nickName;
        private String profile;

        public void setProfile(String profile) {
            this.profile = profile;
        }
    }

    public static class Response {

    }
}
