package com.example.backend.dto;

import com.example.backend.entity.Fairytale;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FairyTaleListResponse {
    private Long fairyTaleId;
    private String imageUrl;

    public FairyTaleListResponse(Fairytale fairytale){
        this.fairyTaleId = fairytale.getFairytaleId();
        this.imageUrl = fairytale.getPictureList().get(0).getPicture();
    }

    public static FairyTaleListResponse of(Fairytale fairytale){
        return new FairyTaleListResponse(fairytale);
    }
}
