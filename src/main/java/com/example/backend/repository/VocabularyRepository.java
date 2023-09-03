package com.example.backend.repository;

import com.example.backend.entity.Vocabulary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VocabularyRepository extends JpaRepository<Vocabulary, Long> {
    Page<Vocabulary> findByLevel(int level, Pageable pageable);
    Page<Vocabulary> findByPartOfSpeech(String partOfSpeech, Pageable pageable);
}
