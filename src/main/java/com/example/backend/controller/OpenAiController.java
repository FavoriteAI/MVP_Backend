package com.example.backend.controller;

import com.example.backend.dto.PromptRequest;
import com.example.backend.dto.Response;
import com.example.backend.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/openai")
@RequiredArgsConstructor
public class OpenAiController {

    private final OpenAiService openAiService;

    @PostMapping("/image")
    public ResponseEntity<Response> generateImageFromPrompt(@RequestBody PromptRequest promptRequest){
        List<Long> vocabularyIds = promptRequest.getVocabularyIds();
        Response response = openAiService.generateImageFromPrompt(vocabularyIds);
        return ResponseEntity.ok(response);
    }


}
