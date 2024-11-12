package com.telekom.ai4coding.chatbot.configuration;


import com.telekom.ai4coding.chatbot.configuration.agent.OpenAiAgent;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiConfig {


    @Bean
    OpenAiAgent openAiAgent(ChatLanguageModel chatLanguageModel) {
        return AiServices.builder(OpenAiAgent.class).chatLanguageModel(chatLanguageModel).build();
    }


}
