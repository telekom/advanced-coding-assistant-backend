package com.telekom.ai4coding.chatbot.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "local")
public record LocalChatbotProperties(String endpoint) {}
