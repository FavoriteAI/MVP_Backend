package com.example.backend.dto;

import lombok.Getter;
import java.util.List;

@Getter
public class PromptRequest {
    private List<Long> vocabularyIds;
}
