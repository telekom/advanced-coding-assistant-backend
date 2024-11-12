package com.telekom.ai4coding.chatbot.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aws")
public record AwsProperties(
        String accessKeyId,
        String secretKey,
        String region,
        Integer maxRetries,
        Integer maxTokens,
        Double temperature,
        Integer topK,
        String anthropicVersion,
        String model,
        Float topP,
        String[] stopSequences
) {}
