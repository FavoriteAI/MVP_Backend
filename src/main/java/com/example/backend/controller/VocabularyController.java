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
        Response response = vocabularyService.getVocabularyListByLevel(page, level);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Response> getVocabularies(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size) {

        Response response = vocabularyService.getVocabularyListByRandom(page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{partOfSpeech}")
    public ResponseEntity<Response> getVocabulariesByPartOfSpeech(
            @PathVariable String partOfSpeech,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size){
        Response response = vocabularyService.getVocabularyListByPartOfSpeech(partOfSpeech, page, size);
        return ResponseEntity.ok(response);
    }

}
