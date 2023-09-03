package com.example.backend.dto;

import com.example.backend.entity.Vocabulary;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VocabularyListResponse {
    private List<VocabularyResponse> vocabularyResponseList;

    public VocabularyListResponse(List<Vocabulary> vocabularies){
        this.vocabularyResponseList = vocabularies.stream()
                .map(VocabularyResponse::new)
                .collect(Collectors.toList());
    }

    public static VocabularyListResponse of(List<Vocabulary> vocabularies){
        return new VocabularyListResponse(vocabularies);
    }

}
