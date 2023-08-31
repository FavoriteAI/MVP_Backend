package com.example.backend.service;

import com.example.backend.code.OpenAiCode;
import com.example.backend.dto.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DallEService {
    private final String OPENAI_DallE_API_URL = "https://api.openai.com/v1/engines/davinci-codex/completions"; // DALL·E-2의 실제 엔드포인트 URL로 교체해야 합니다.
    private final String OPENAI_ChatGPT_API_URL = ""
    public Response generateImageFromPrompt(List<Long> vocabularyIds){

        return Response.of(OpenAiCode.IMAGE_GENERATED, null);
    }
}
