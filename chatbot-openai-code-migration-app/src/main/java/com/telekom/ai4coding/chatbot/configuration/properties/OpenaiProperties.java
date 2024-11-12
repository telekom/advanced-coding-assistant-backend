package com.telekom.ai4coding.chatbot.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "openai")
public record OpenaiProperties(
        String baseUrl,
        String apiKey,
        String organizationId,
        String modelName,
        Double temperature,
        Double topP,
        List<String> stop,
        Integer maxTokens,
        Double presencePenalty,
        Double frequencyPenalty,
        Integer seed,
        String user,
        Integer maxRetries,
        Integer timeout,
        Boolean logRequests,
        Boolean logResponses,
        String proxy
) {}
