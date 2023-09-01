package com.example.backend.service;

import com.example.backend.code.VocabularyCode;
import com.example.backend.dto.Response;
import com.example.backend.entity.Vocabulary;
import com.example.backend.exception.EntityNotFoundException;
import com.example.backend.repository.VocabularyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VocabularyService {

    private final VocabularyRepository vocabularyRepository;

    // 레벨에 따른 단어 검색 (20개씩)
    public Response getVocabularyList(int page, int level){
        vocabularyRepository.findByLevel(level, PageRequest.of(page, 20));
        return Response.of(VocabularyCode.VOCABULARY_LIST_GENERATED,    null);
    }

    public Vocabulary findVocabularyById(Long id){

        Vocabulary vocabulary = vocabularyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("단어를 찾을 수 없습니다."));
        return vocabulary;
    }

}
