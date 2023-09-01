package com.example.backend.dto;

import com.example.backend.entity.Vocabulary;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class VocabularyListResponse {
    private List<VocabularyResponse> vocabularyResponseList;

    @Builder
    public VocabularyListResponse(List<Vocabulary> vocabularies){
        this.vocabularyResponseList = vocabularies.stream()
                .map(VocabularyResponse::new)
                .collect(Collectors.toList());
    }

}
