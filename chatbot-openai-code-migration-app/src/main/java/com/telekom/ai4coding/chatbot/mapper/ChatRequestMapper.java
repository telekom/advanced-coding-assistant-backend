package com.telekom.ai4coding.chatbot.mapper;

import com.theokanning.openai.completion.chat.ChatMessage;
import dev.ai4j.openai4j.chat.Message;

import java.util.ArrayList;
import java.util.List;

/*
All messages are mapped from Theokanning -> OpenAi4J
 */
public class ChatRequestMapper {

    private ChatRequestMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static dev.ai4j.openai4j.chat.ChatCompletionRequest mapRequest(
            com.theokanning.openai.completion.chat.ChatCompletionRequest theokanningRequest) {
        return dev.ai4j.openai4j.chat.ChatCompletionRequest.builder()
                .model(theokanningRequest.getModel())
                .messages(createMessages(theokanningRequest.getMessages()))
                .temperature(theokanningRequest.getTemperature())
                .topP(theokanningRequest.getTopP())
                .n(theokanningRequest.getN())
                .stream(theokanningRequest.getStream())
                .stop(theokanningRequest.getStop())
                .maxTokens(theokanningRequest.getMaxTokens())
                .presencePenalty(theokanningRequest.getPresencePenalty())
                .frequencyPenalty(theokanningRequest.getFrequencyPenalty())
                .logitBias(theokanningRequest.getLogitBias())
                .user(theokanningRequest.getUser())
                .build();
    }

    private static List<Message> createMessages(List<ChatMessage> messagesToMap) {
        List<Message> openAI4JMessages = new ArrayList<>();

        for (ChatMessage message : messagesToMap) {
            openAI4JMessages.add(createMessage(message));
        }

        return openAI4JMessages;
    }

    private static Message createMessage(ChatMessage messageToMap) {

        if (messageToMap.getRole().equals("system")) {
            return dev.ai4j.openai4j.chat.SystemMessage.builder()
                    .content(messageToMap.getContent())
                    .name(messageToMap.getName())
                    .build();
        } else if (messageToMap.getRole().equals("assistant")) {
            return dev.ai4j.openai4j.chat.AssistantMessage.builder()
                    .content(messageToMap.getContent())
                    .name(messageToMap.getName())
                    .build();
        } else if (messageToMap.getRole().equals("user")) {
            return dev.ai4j.openai4j.chat.UserMessage.builder()
                    .content(messageToMap.getContent())
                    .name(messageToMap.getName())
                    .build();
        } else {
            return null;
        }
    }

}