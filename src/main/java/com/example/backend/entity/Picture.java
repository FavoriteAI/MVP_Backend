package com.example.backend.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "picture")
@Getter
@Slf4j
public class Picture {
    @Id
    @GeneratedValue
    private Long pictureId;

    @Column(name = "picture", length = 500)
    private String picture;

    @ManyToOne
    @JoinColumn(name = "fairytale_id")
    private Fairytale fairytale;

    @Builder
    public Picture(String picture){
        this.picture = picture;
    }

}
