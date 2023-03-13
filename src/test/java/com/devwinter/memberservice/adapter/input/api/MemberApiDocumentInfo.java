package com.devwinter.memberservice.adapter.input.api;

public enum MemberApiDocumentInfo {
    CREATE("회원", "회원가입", "회원가입", "회원가입 API", false),
    CHANGE_PASSWORD("회원", "회원 비밀번호 변경", "회원 비밀번호 변경", "회원 비밀번호 변경 API", true),
    DELETE("회원", "회원 탈퇴", "회원 탈퇴", "회원 탈퇴 API", true),
    EDIT_INFO("회원", "회원 정보 변경", "회원 정보 변경", "회원 정보 변경 API", true),
    UPLOAD_PROFILE("회원", "회원 프로필 업로드", "회원 프로필 업로드", "회원 프로필 업로드 API", true),
    ;

    private final String tag;
    private final String identifier;
    private final String summary;
    private final String description;
    private final boolean certification;

    MemberApiDocumentInfo(String tag, String identifier, String summary, String description, boolean certification) {
        this.tag = tag;
        this.identifier = identifier;
        this.summary = summary;
        this.description = description;
        this.certification = certification;
    }

    public String getTag() {
        return tag;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCertification() {
        return certification;
    }
}
