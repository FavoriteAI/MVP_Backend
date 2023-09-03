package com.example.backend.service;

import com.example.backend.code.FairytaleCode;
import com.example.backend.dto.*;
import com.example.backend.entity.Fairytale;
import com.example.backend.entity.Picture;
import com.example.backend.entity.Vocabulary;
import com.example.backend.exception.EntityNotFoundException;
import com.example.backend.repository.FairytaleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FairytaleService {

    private final FairytaleRepository fairytaleRepository;
    private final OpenAiService openAiService;
    private final PictureService pictureService;
    private final VocabularyService vocabularyService;

    public Response createFairytale(FairytaleRequest fairytaleRequest){
        List<Vocabulary> vocabularies = new ArrayList<>();
        for(Long vocabularyId : fairytaleRequest.getFairytaleIds()){
           vocabularies.add(vocabularyService.findVocabularyById(vocabularyId));
        }
        String aiResponse = createFairytaleByAi(vocabularies);
        FairytaleDto fairytaleDto = parseStoryResponse(aiResponse);
        Fairytale fairytale = createFairytaleEntity(fairytaleDto.getTitle(), fairytaleDto.getContent());
        for (Vocabulary vocabulary : vocabularies){
            fairytale.addVocabulary(vocabulary);
        }
        for (String prompt : fairytaleDto.getContents()){
            fairytale.addPicture(createPictureByFairyTale(prompt));
        }
        fairytaleRepository.save(fairytale);
        FairytaleResponse fairytaleResponse = FairytaleResponse.of(fairytale);
        for (String prompt : fairytaleDto.getContents()){
            fairytaleResponse.getParagraphList().add(prompt);
        }
        return Response.of(FairytaleCode.FAIRY_TALE_GENERATED, fairytaleResponse);
    }

    public Picture createPictureByFairyTale(String content){
        Picture picture = pictureService.createPictureByAi(content);
        return picture;
    }

    public String createFairytaleByAi(List<Vocabulary> vocabularies){
        List<String> words = new ArrayList<>();
        for (Vocabulary vocabulary : vocabularies){
            words.add(vocabulary.getVocabulary());
        }
        String aiResponse = openAiService.generateStoryFromWords(words);
        return aiResponse;
    }

    private FairytaleDto parseStoryResponse(String storyResponse) {
        // Extracting title and content from the response
        log.info(storyResponse);
        String title = storyResponse.split("Title: ")[0].split(" Paragraph 1: ")[0];
        FairytaleDto fairytaleDto = FairytaleDto.builder()
                .title(title)
                .build();

        fairytaleDto.addContents(storyResponse.split("Paragraph 1: ")[1].split(" Paragraph 2: ")[0]);
        fairytaleDto.addContents(storyResponse.split("Paragraph 2: ")[1].split(" Paragraph 3: ")[0]);
        fairytaleDto.addContents(storyResponse.split("Paragraph 3: ")[1]);

        return fairytaleDto;
    }

    public Fairytale createFairytaleEntity(String title, String content){
        Fairytale fairytale = Fairytale
                .builder()
                .title(title)
                .content(content)
                .build();
        return fairytale;
    }

    public Response getFairytales(Pageable pageable){
        Page<Fairytale> fairytales = fairytaleRepository.findAll(pageable);
        Response response = Response.of(FairytaleCode.GET_FAIRY_TALE_SUCCESS, fairytales.map(FairyTaleListResponse::new));
        return response;
    }

    public Response getFairytale(Long fairytaleId){
        Fairytale fairytale = findById(fairytaleId);
        FairytaleResponse fairytaleResponse = FairytaleResponse.of(fairytale);
        return Response.of(FairytaleCode.GET_FAIRY_TALE_SUCCESS, fairytaleResponse);
    }

    public Fairytale findById(Long targetId){
        Fairytale fairytale = fairytaleRepository.findById(targetId)
                .orElseThrow(() -> new EntityNotFoundException("해당 동화를 찾을 수 없습니다."));
        return fairytale;
    }
}
