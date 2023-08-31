package com.example.backend.code;

public enum OpenAiCode implements ResponseCode {
    IMAGE_GENERATED(200, "이미지가 생성되었습니다.");

    private final int code;
    private final String message;

    OpenAiCode(int code, String message) {
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
