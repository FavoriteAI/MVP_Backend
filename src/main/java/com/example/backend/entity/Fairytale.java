package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "fairytale")
@Getter
@Slf4j
public class Fairytale {

    @Id
    @GeneratedValue
    private Long fairytaleId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @OneToMany(mappedBy = "fairytale", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Picture> pictureList = new ArrayList<>();



    @Builder
    public Fairytale(String content, String title){
        this.content = content;
        this.title = title;
    }

    public void addPicture(Picture picture){
        this.pictureList.add(picture);
    }
    public VocabularyList buildVocabularyList(Vocabulary vocabulary){
        VocabularyList vocabularyList = VocabularyList.builder()
                .vocabulary(vocabulary)
                .fairytale(this)
                .build();
        return vocabularyList;
    }

    public void addVocabularyList(Picture picture){
        this.pictureList.add(picture);
    }

    public Fairytale toEntity() {
        return Fairytale.builder()
                .content(this.content)
                .title(this.title)
                .build();
    }
}
