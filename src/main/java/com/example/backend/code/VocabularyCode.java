package com.example.backend.code;

public enum VocabularyCode implements ResponseCode{

    VOCABULARY_LIST_GENERATED(200, "단어 리스트가 전송되었습니다.");


    private final int code;
    private final String message;

    VocabularyCode(int code, String message) {
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
