package com.telekom.ai4coding.chatbot.configuration.agent;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;


public interface HypotheticalDocumentGenerator {

    @SystemMessage({"""
      You are a software engineer. Generate a hypothetical code snippet
      that the user's query is looking for. Do not include text that are
      not code comments.
    """})
    String getFakeCodeSnippet(@UserMessage String userMessage);

    @SystemMessage({"""
      You are a technical writer specializing in code documentation.
      Write a possible code documentation that could answer the user's query.
    """})
    String getFakeCodeDocumentation(@UserMessage String userMessage);
}
