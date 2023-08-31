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

    @JoinColumn(name = "picture_id", nullable = false)
    @ManyToOne
    private Picture picture;

    @Builder
    public VocabularyList(Picture picture, Vocabulary vocabulary){
        this.picture = picture;
        this.vocabulary = vocabulary;
    }
}
