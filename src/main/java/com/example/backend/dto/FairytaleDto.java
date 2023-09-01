package com.example.backend.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FairytaleDto {

    private String title;
    private String content;

    @Builder
    public FairytaleDto(String title, String content){
        this.title = title;
        this.content = content;
    }

}
