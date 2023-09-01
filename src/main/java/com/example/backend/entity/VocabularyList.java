package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "vocabulary_list")
@Getter
@Slf4j
public class VocabularyList {

    @Id
    @GeneratedValue
    private Long vocabularyListId;

    @JoinColumn(name = "vocabulary_id", nullable = false)
    @ManyToOne
    private Vocabulary vocabulary;

    @JoinColumn(name = "fairytale_id", nullable = false)
    @ManyToOne
    private Fairytale fairytale;

    @Builder
    public VocabularyList(Fairytale fairytale, Vocabulary vocabulary){
        this.fairytale = fairytale;
        this.vocabulary = vocabulary;
    }
}
