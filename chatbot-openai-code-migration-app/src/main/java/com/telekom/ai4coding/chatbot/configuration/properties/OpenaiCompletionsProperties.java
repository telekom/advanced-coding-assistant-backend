package com.telekom.ai4coding.chatbot.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "openai.completions")
public record OpenaiCompletionsProperties(String endpoint, String openaiApiKey, String azureApiKey) { }
