package com.example.backend.service;

import com.example.backend.code.VocabularyCode;
import com.example.backend.dto.Response;
import com.example.backend.dto.VocabularyListResponse;
import com.example.backend.entity.Vocabulary;
import com.example.backend.entity.VocabularyList;
import com.example.backend.exception.EntityNotFoundException;
import com.example.backend.repository.VocabularyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VocabularyService {

    private final VocabularyRepository vocabularyRepository;

    // 레벨에 따른 단어 검색 (20개씩)
    public Response getVocabularyList(int page, int level){
        List<Vocabulary> vocabularies = vocabularyRepository.findByLevel(level, PageRequest.of(page, 20)).getContent();
        VocabularyListResponse vocabularyListResponse = VocabularyListResponse.builder()
                .vocabularies(vocabularies)
                .build();
        return Response.of(VocabularyCode.VOCABULARY_LIST_GENERATED, vocabularyListResponse);
    }

    public Vocabulary findVocabularyById(Long id){

        Vocabulary vocabulary = vocabularyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("단어를 찾을 수 없습니다."));
        return vocabulary;
    }

}
