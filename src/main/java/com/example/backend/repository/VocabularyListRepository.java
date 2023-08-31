package com.example.backend.repository;

import com.example.backend.entity.Picture;
import com.example.backend.entity.VocabularyList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VocabularyListRepository extends JpaRepository<VocabularyList, Long> {
}
