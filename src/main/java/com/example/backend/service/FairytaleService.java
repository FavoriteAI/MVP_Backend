package com.example.backend.service;

import com.example.backend.dto.FairytaleDto;
import com.example.backend.dto.Response;
import com.example.backend.entity.Fairytale;
import com.example.backend.entity.Picture;
import com.example.backend.repository.FairytaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FairytaleService {

    private final FairytaleRepository fairytaleRepository;
    private final OpenAiService openAiService;

    public Response createFairytaleByAi(List<String> words){
        String aiResponse = openAiService.generateStoryFromWords(words);
        FairytaleDto fairytaleDto = parseStoryResponse(aiResponse);
        Fairytale fairytale = createFairytaleEntity(fairytaleDto.getTitle(), fairytaleDto.getContent());
        for (String word : words){
            fairytale.add
        }
    }

    private FairytaleDto parseStoryResponse(String storyResponse) {
        // Extracting title and content from the response
        String storyTitle = storyResponse.split("Title: ")[1].split(" Paragraph 1: ")[0];
        String paragraph1 = storyResponse.split("Paragraph 1: ")[1].split(" Paragraph 2: ")[0];
        String paragraph2 = storyResponse.split("Paragraph 2: ")[1].split(" Paragraph 3: ")[0];
        String paragraph3 = storyResponse.split("Paragraph 3: ")[1];

        String storyContent = paragraph1 + "\n\n" + paragraph2 + "\n\n" + paragraph3;

        return FairytaleDto.builder()
                .title(storyTitle)
                .content(storyContent)
                .build();
    }
    public Fairytale createFairytaleEntity(String title, String content){
        Fairytale fairytale = Fairytale
                .builder()
                .title(title)
                .content(content)
                .build();
        return fairytale;
    }
}
