package com.telekom.ai4coding.chatbot.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "google-vertex")
public record VertexProperties(
        String project,
        String location,
        String modelName,
        Float temperature,
        Integer maxOutputTokens,
        Integer topK,
        Float topP,
        Integer maxRetries
) {}