package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "fairytale")
@Getter
@Slf4j
public class Fairytale {

    @Id
    @GeneratedValue
    private Long fairytaleId;

    @Column(name = "content")
    private String content;

    @OneToOne
    @JoinColumn(name = "picture_id", nullable = false)
    private Picture picture;

    @Builder
    public Fairytale(String content, Picture picture){
        this.content = content;
        this.picture = picture;
    }
}
