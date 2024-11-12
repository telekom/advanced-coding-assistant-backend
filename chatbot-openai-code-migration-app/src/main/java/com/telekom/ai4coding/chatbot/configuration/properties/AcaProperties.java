package com.telekom.ai4coding.chatbot.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.Nullable;

@ConfigurationProperties(prefix = "aca")
public record AcaProperties(GitlabProperties gitlab,
                            KnowledgeGraphConstructionProperties knowledgeGraphConstruction,
                            EmbeddingModelProperties embeddingModel,
                            int fillingVesselAlgorithmMaxLength,
                            int toolResultMaxToken) {

    public record GitlabProperties(String token) {}

    public record KnowledgeGraphConstructionProperties(int astMaxDepth,
                                                       int textMaxChar,
                                                       int textMaxOverlappingChar) {}

    public record EmbeddingModelProperties(boolean useCustomModel,
                                           @Nullable String modelClassPath,
                                           @Nullable String tokenizerClassPath,
                                           @Nullable String poolingMode) {}
}
