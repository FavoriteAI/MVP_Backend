package com.example.backend.service;

import com.example.backend.entity.Vocabulary;
import com.example.backend.exception.EntityNotFoundException;
import com.example.backend.repository.VocabularyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VocabularyService {

    private final VocabularyRepository vocabularyRepository;

    public Vocabulary findVocabularyById(Long id){

        Vocabulary vocabulary = vocabularyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("단어를 찾을 수 없습니다."));

        return vocabulary;
    }
}
