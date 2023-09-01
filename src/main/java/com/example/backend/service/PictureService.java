package com.example.backend.service;

import com.example.backend.entity.Picture;
import com.example.backend.entity.Vocabulary;
import com.example.backend.repository.PictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PictureService {

    private final PictureRepository pictureRepository;

    public Picture selectPicture(String pictureUrl){
        Picture picture = Picture.builder()
                .picture(pictureUrl)
                .build();

        return picture;
    }

    public Picture createPicture(Picture picture){
        return pictureRepository.save(picture);
    }

}
