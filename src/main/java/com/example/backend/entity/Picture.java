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
@Table(name = "picture")
@Getter
@Slf4j
public class Picture {
    @Id
    @GeneratedValue
    private Long pictureId;

    @Column(name = "picture")
    private String picture;

    @OneToMany(mappedBy = "picture", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<VocabularyList> vocabularyLists = new ArrayList<>();

    @Builder
    public Picture(String picture){
        this.picture = picture;
    }

    public void addVocabulary(Vocabulary vocabulary){
        this.vocabularyLists.add(buildVocabularyList(vocabulary));
    }
    public VocabularyList buildVocabularyList(Vocabulary vocabulary){
        return VocabularyList.builder()
                .vocabulary(vocabulary)
                .picture(this)
                .build();
    }

}
