package com.example.backend.code;


public enum FairytaleCode implements ResponseCode{

    FAIRY_TALE_GENERATED(200, "동화가 생성 되었습니다."),
    GET_FAIRY_TALE_SUCCESS(200, "동화 조회 성공");

    private final int code;
    private final String message;

    FairytaleCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
