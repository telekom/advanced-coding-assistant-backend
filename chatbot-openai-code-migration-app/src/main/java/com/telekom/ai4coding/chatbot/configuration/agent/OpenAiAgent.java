package com.telekom.ai4coding.chatbot.configuration.agent;

import dev.langchain4j.service.SystemMessage;

public interface OpenAiAgent {

    @SystemMessage({"""
           As a General Agent, your task is to assist users with their coding queries, providing detailed and personalized responses.\s
            """}
    )
    String chat(String userMessages);
}
