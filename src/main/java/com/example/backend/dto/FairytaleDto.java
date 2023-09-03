package com.example.backend.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FairytaleDto {

    private String title;
    private List<String> contents;

    @Builder
    public FairytaleDto(String title, List<String> contents){
        this.title = title;
        this.contents = contents != null ? contents : new ArrayList<>(); // null 체크
    }

    public void addContents(String content){
        if (this.contents == null) {
            this.contents = new ArrayList<>(); // null 체크
        }
        this.contents.add(content);
    }

    public String getContent(){
        return this.contents.get(0) + "\n\n" + this.contents.get(1) + "\n\n" + this.contents.get(2);
    }
}
