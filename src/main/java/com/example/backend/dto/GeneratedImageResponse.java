package com.example.backend.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GeneratedImageResponse {
    private List<String> imageUrls = new ArrayList<>();

    @Builder
    public GeneratedImageResponse(List<String> imageUrls){
        this.imageUrls = imageUrls;
    }

    public void addImageUrl(String imageUrl){
        this.imageUrls.add(imageUrl);
    }
}
