package com.example.backend.service;

import com.example.backend.code.OpenAiCode;
import com.example.backend.dto.GeneratedImageResponse;
import com.example.backend.dto.Response;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${openai.apikey}")
    private String OPENAI_TOKEN;
    public String generateImageFromPrompt(String content){

        String prompt = content + "digital art";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + OPENAI_TOKEN);
        headers.set("Content-Type", "application/json");

        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("prompt", prompt);
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
        if (words.size() < 1) {
            throw new IllegalArgumentException("The list must contain at least 1 words.");
        }

        // Constructing the prompt for the story based on the words
        StringBuilder promptBuilder = new StringBuilder("Please create a children's story for 7-year-old kids using the words: ");

        for (int i = 0; i < words.size(); i++) {
            promptBuilder.append(words.get(i));
            if (i < words.size() - 2) {
                promptBuilder.append(", ");
            } else if (i == words.size() - 2) {
                promptBuilder.append(", and ");
            }
        }

        promptBuilder.append(". The story should have a title followed by 'Title: ', then followed by 3 paragraphs. Each paragraph should start with 'Paragraph [number]: ' and should not exceed 300 characters including spaces. Use basic vocabularies.");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + OPENAI_TOKEN);
        headers.set("Content-Type", "application/json");

        JSONObject jsonRequest = new JSONObject();
        JSONArray messages = new JSONArray();
        messages.put(new JSONObject().put("role", "system").put("content", "You are a storyteller who specializes in creating children's stories."));
        messages.put(new JSONObject().put("role", "user").put("content", promptBuilder));

        jsonRequest.put("model", "gpt-4-0613");
        jsonRequest.put("messages", messages);

        HttpEntity<String> entity = new HttpEntity<>(jsonRequest.toString(), headers);
        ResponseEntity<String> response = restTemplate.exchange(OPENAI_ChatGPT_API_URL, HttpMethod.POST, entity, String.class);

        // Parsing the JSON response to extract the story
        JSONObject jsonResponse = new JSONObject(response.getBody());
        return jsonResponse.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content");
    }



}
