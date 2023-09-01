package com.example.backend.service;

import com.example.backend.code.OpenAiCode;
import com.example.backend.dto.GeneratedImageResponse;
import com.example.backend.dto.Response;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenAiService {
    private final String OPENAI_Image_API_URL = "https://api.openai.com/v1/images/generations"; // DALL·E-2의 실제 엔드포인트 URL로 교체해야 합니다.
    private final String OPENAI_ChatGPT_API_URL = "https://api.openai.com/v1/chat/completions"; // ChatGPT의 실제 엔드포인트

    private String OPENAI_TOKEN = "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";

    private final VocabularyService vocabularyService;
    public String generateImageFromPrompt(String content){

        String prompt = content + "digital art";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + OPENAI_TOKEN);
        headers.set("Content-Type", "application/json");

        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("prompt", content);
        jsonRequest.put("n", 1);
        jsonRequest.put("size", "512x512");
        jsonRequest.put("response_format", "url");

        HttpEntity<String> entity = new HttpEntity<>(jsonRequest.toString(), headers);
        ResponseEntity<String> response = restTemplate.exchange(OPENAI_Image_API_URL, HttpMethod.POST, entity, String.class);

        JSONObject jsonResponse = new JSONObject(response.getBody());
        JSONArray jsonArray = jsonResponse.getJSONArray("data");
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        String imageUrl = jsonObject.getString("url");
        return imageUrl;
    }

    public String generateStoryFromWords(List<String> words) {
        if (words.size() != 3) {
            throw new IllegalArgumentException("The list must contain exactly 3 words.");
        }

        // Constructing the prompt for the story based on the words
        String prompt = "Please create a children's story for 7-year-old kids using the words: "
                + words.get(0) + ", " + words.get(1) + ", and " + words.get(2) + ". "
                + "The story should be 3 paragraphs long, and each paragraph should not exceed 300 characters including spaces. "
                + "Use basic vocabularies.";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + OPENAI_TOKEN);
        headers.set("Content-Type", "application/json");

        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("prompt", prompt);
        jsonRequest.put("max_tokens", 500); // Adjust this if needed

        HttpEntity<String> entity = new HttpEntity<>(jsonRequest.toString(), headers);
        ResponseEntity<String> response = restTemplate.exchange(OPENAI_ChatGPT_API_URL, HttpMethod.POST, entity, String.class);

        // Parsing the JSON response to extract the story
        JSONObject jsonResponse = new JSONObject(response.getBody());
        return jsonResponse.getJSONObject("choices").getJSONArray("text").getString(0);
    }


}
