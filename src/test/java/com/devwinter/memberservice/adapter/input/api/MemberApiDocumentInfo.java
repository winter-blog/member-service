package com.devwinter.memberservice.adapter.input.api;

public enum MemberApiDocumentInfo {
    CREATE("회원", "회원가입", "회원가입", "회원가입 API", false),
    CHANGE_PASSWORD("회원", "회원 비밀번호 변경", "회원 비밀번호 변경", "회원 비밀번호 변경 API", true),
    DELETE("회원", "회원 탈퇴", "회원 탈퇴", "회원 탈퇴 API", true),
    EDIT_INFO("회원", "회원 정보 변경", "회원 정보 변경", "회원 정보 변경 API", true),
    UPLOAD_PROFILE("회원", "회원 프로필 업로드", "회원 프로필 업로드", "회원 프로필 업로드 API", true),
    CHECK_EMAIL("회원", "이메일 중복 검사", "이메일 중복 검사", "이메일 중복 검사 API", false),
    CHECK_NICKNAME("회원", "닉네임 중복 검사", "닉네임 중복 검사", "닉네임 중복 검사 API", false),
    CHECK_PASSWORD("회원", "비밀번호 중복 검사", "비밀번호 중복 검사", "비밀번호 중복 검사 API", true),
    MY_PAGE("회원", "마이페이지", "마이페이지", "마이페이지 API", true),
    WRITE_INTRODUCE("회원", "자기소개 작성", "자기소개 작성", "자기소개 작성 API", true),
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
