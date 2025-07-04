package com.lg2.qna_service.ai.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

public class AIRequest {

    @Data
    @Builder
    public static class OpenAiRequest {
        private String model;
        private List<Map<String, String>> messages;
        private double temperature;
        private int max_tokens;
    }
}
