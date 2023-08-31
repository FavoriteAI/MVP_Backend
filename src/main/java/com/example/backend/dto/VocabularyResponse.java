package com.example.backend.dto;

import com.example.backend.entity.Vocabulary;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VocabularyResponse {
    private Long vocabularyId;
    private String english;
    private String korean;
    private String category;
    private String partOfSpeech;
    private int level;
    @Builder
    public VocabularyResponse(Vocabulary vocabulary){
        this.vocabularyId = vocabulary.getVocabularyId();
        this.english = vocabulary.getVocabulary();
        this.korean = vocabulary.getMean();
        this.category = vocabulary.getCategory();
        this.partOfSpeech = vocabulary.getPartOfSpeech();
        this.level = vocabulary.getLevel();
    }

}
