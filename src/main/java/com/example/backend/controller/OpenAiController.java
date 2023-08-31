package com.example.backend.controller;

import com.example.backend.dto.PromptRequest;
import com.example.backend.service.DallEService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/openai")
@RequiredArgsConstructor
public class OpenAiController {

    private final DallEService dallEService;

    @PostMapping("/generate")
    public generateImageFromPrompt(@RequestBody PromptRequest)


}
