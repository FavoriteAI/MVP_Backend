package com.example.backend.controller;

import com.example.backend.dto.Response;
import com.example.backend.dto.VocabularyListRequest;
import com.example.backend.service.VocabularyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vocabularies")
@RequiredArgsConstructor
public class VocabularyController {

    private final VocabularyService vocabularyService;

    @PostMapping
    public ResponseEntity<Response> getVocabulariesByLevel(@RequestBody VocabularyListRequest vocabularyListRequest){
        int page = vocabularyListRequest.getPage();
        int level = vocabularyListRequest.getLevel();
        Response response = vocabularyService.getVocabularyList(page, level);
        return ResponseEntity.ok(response);
    }
}
