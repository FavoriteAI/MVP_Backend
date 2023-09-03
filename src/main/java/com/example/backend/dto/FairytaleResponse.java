package com.example.backend.dto;

import com.example.backend.entity.Fairytale;
import com.example.backend.entity.Picture;
import com.example.backend.entity.VocabularyList;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FairytaleResponse {

    private Long fairytaleId;
    private String title;
    private List<String> paragraphList = new ArrayList<>();
    private List<String> pictureList = new ArrayList<>();
    private List<VocabularyResponse> vocabularyLists = new ArrayList<>();

    public FairytaleResponse(Fairytale fairytale){
        this.fairytaleId = fairytale.getFairytaleId();
        this.title = fairytale.getTitle();

        this.pictureList = fairytale.getPictureList().stream()
                .map(Picture::getPicture) // Assuming there's a method in Picture to get the relevant field
                .collect(Collectors.toList());
        this.vocabularyLists = fairytale.getVocabularyLists().stream()
                .map(vocabularyList -> VocabularyResponse.of(vocabularyList.getVocabulary()))
                .collect(Collectors.toList());
    }

    public void addParagraph(String paragraph){
        this.paragraphList.add(paragraph);
    }
    public static FairytaleResponse of(Fairytale fairytale){
        return new FairytaleResponse(fairytale);
    }
}