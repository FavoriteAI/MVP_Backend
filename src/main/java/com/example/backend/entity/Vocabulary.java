package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "vocabulary")
@Getter
@Slf4j
public class Vocabulary {

    @Id
    @GeneratedValue
    private Long vocabularyId;

    @Column(name = "vocabulary", nullable = false)
    private String vocabulary;

    @Column(name = "part_of_speech")
    private String partOfSpeech;

    @Column(name = "mean")
    private String mean;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "level", nullable = false)
    private int level;

    @Builder
    public Vocabulary(String vocabulary, String partOfSpeech, String mean, String category, int level){
        this.vocabulary = vocabulary;
        this.partOfSpeech = partOfSpeech;
        this.mean = mean;
        this.category = category;
        this.level = level;
    }


}
