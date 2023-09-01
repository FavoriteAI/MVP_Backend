package com.example.backend.service;

import com.example.backend.entity.Picture;
import com.example.backend.entity.Vocabulary;
import com.example.backend.entity.VocabularyList;
import com.example.backend.repository.VocabularyListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class VocabularyListService {

    private final VocabularyListRepository vocabularyListRepository;

}
