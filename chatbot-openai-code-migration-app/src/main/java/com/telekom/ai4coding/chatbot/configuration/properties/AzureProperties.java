package com.telekom.ai4coding.chatbot.configuration.properties;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "azure")
public record AzureProperties(
        String endpoint,
        String apiKey,
        String model,
        Integer timeout,
        Boolean logRequestsAndResponses,
        Integer maxTokens,
        Double temperature,
        Double topP,
        Double presencePenalty,
        String user,
        Integer n,
        List<String> stop,
        Double frequencyPenalty,
        Long seed,
        String proxy
) {}


















