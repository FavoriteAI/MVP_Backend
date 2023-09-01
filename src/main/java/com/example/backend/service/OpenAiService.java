package com.example.backend.service;

import com.example.backend.code.OpenAiCode;
import com.example.backend.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenAiService {
    private final String OPENAI_DallE_API_URL = "https://api.openai.com/v1/engines/davinci-codex/completions"; // DALL·E-2의 실제 엔드포인트 URL로 교체해야 합니다.
    private final String OPENAI_ChatGPT_API_URL = "https://api.openai.com/v1/engines/davinci-codex/completions"; // ChatGPT의 실제 엔드포인트

    private String OPENAI_TOKEN = "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";

    private final VocabularyService vocabularyService;
    public String generateImageFromPrompt(List<Long> vocabularyIds){
        if (vocabularyIds.size() != 3) {
            throw new IllegalArgumentException("Expected exactly 3 vocabulary IDs");
        }

        StringBuilder combinedWords = new StringBuilder();
        for (Long vocabularyId : vocabularyIds){
            String word = vocabularyService.findVocabularyById(vocabularyId).getVocabulary();
            combinedWords.append(word).append(" ");
        }

        String prompt = combinedWords.toString().trim();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + OPENAI_TOKEN);
        headers.set("Content-Type", "application/json");

        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("prompt", prompt);
        jsonRequest.put("max_tokens", 500); // This might need adjustment depending on the requirements

        HttpEntity<String> entity = new HttpEntity<>(jsonRequest.toString(), headers);
        ResponseEntity<String> response = restTemplate.exchange(OPENAI_DallE_API_URL, HttpMethod.POST, entity, String.class);

        JSONObject jsonResponse = new JSONObject(response.getBody());
        String imageUrl = jsonResponse.getJSONObject("choices").getJSONArray("text").getString(0); // Assuming the API response contains the image URL in this format

        return imageUrl;
    }

    public String generateStoryFromWords(String word1, String word2, String word3) {
        // Constructing the prompt for the story based on the words
        String prompt = "Please create a children's story using the words: " + word1 + ", " + word2 + ", and " + word3 + ".";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + OPENAI_TOKEN);
        headers.set("Content-Type", "application/json");

        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("prompt", prompt);
        jsonRequest.put("max_tokens", 500);

        HttpEntity<String> entity = new HttpEntity<>(jsonRequest.toString(), headers);
        ResponseEntity<String> response = restTemplate.exchange(OPENAI_ChatGPT_API_URL, HttpMethod.POST, entity, String.class);

        // Parsing the JSON response to extract the story
        JSONObject jsonResponse = new JSONObject(response.getBody());
        return jsonResponse.getJSONObject("choices").getJSONArray("text").getString(0);
    }


}
