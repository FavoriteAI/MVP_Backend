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

    private final OpenAiService openAiService;

    public Picture createPictureByAi(String prompt){
       String pictureUrl = openAiService.generateImageFromPrompt(prompt);
       Picture picture = createPictureEntity(pictureUrl);
       return picture;
    }

    public Picture createPictureEntity(String pictureUrl){
        return Picture.builder()
                .picture(pictureUrl)
                .build();
    }

}
