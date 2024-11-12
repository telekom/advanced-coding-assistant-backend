package com.telekom.ai4coding.chatbot.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.Usage;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatFunctionCall;
import com.theokanning.openai.completion.chat.ChatMessage;
import dev.ai4j.openai4j.chat.ChatCompletionResponse;
import dev.ai4j.openai4j.chat.Delta;
import dev.ai4j.openai4j.chat.ToolCall;

import java.util.ArrayList;
import java.util.List;

public class ChatResponseMapper {

    private ChatResponseMapper() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Maps from OpenAI4J ChatCompletionResult to OpenAI ChatCompletionResult
     *
     * @param response
     * @return completionResult
     */
    public static ChatCompletionResult mapResponseToResult(ChatCompletionResponse response) {

        ChatCompletionResult chatCompletionResult = new ChatCompletionResult();
        chatCompletionResult.setId(response.id());
        chatCompletionResult.setCreated(response.created());
        chatCompletionResult.setModel(response.model());
        chatCompletionResult.setChoices(createChoices(response.choices()));
        chatCompletionResult.setUsage(createUsage(response.usage()));
        return chatCompletionResult;
    }

    private static Usage createUsage(dev.ai4j.openai4j.shared.Usage usage) {
        Usage theokanningUsage = new Usage();
        theokanningUsage.setPromptTokens(usage.promptTokens());
        theokanningUsage.setCompletionTokens(usage.completionTokens());
        theokanningUsage.setTotalTokens(usage.totalTokens());
        return theokanningUsage;
    }

    private static List<ChatCompletionChoice> createChoices(List<dev.ai4j.openai4j.chat.ChatCompletionChoice> openAIChoices) {
        List<ChatCompletionChoice> theokanningChoices = new ArrayList<>();

        for (dev.ai4j.openai4j.chat.ChatCompletionChoice openaiChoice : openAIChoices) {
            try {
                theokanningChoices.addAll(createChoice(openaiChoice));
            } catch (JsonProcessingException e) {
                System.err.println("Error parsing string to json");
                System.err.println(e);
            }

        }
        return theokanningChoices;
    }


    private static List<ChatCompletionChoice> createChoice(dev.ai4j.openai4j.chat.ChatCompletionChoice openAIChoice) throws JsonProcessingException {
        List<ChatMessage> messages = null;
        if(openAIChoice.delta() != null) {
            messages = createToolResultMessages(openAIChoice.delta());
        }else if (openAIChoice.message() != null && openAIChoice.message().content() != null){
            messages = createAiMessages(openAIChoice.message().content());
        }else if(openAIChoice.message() != null && openAIChoice.message().toolCalls() != null && !openAIChoice.message().toolCalls().isEmpty()){
            messages = createToolRequestMessages(openAIChoice.message().toolCalls());
        }

        List<ChatCompletionChoice> chatCompletionChoics = new ArrayList<>();
        for(ChatMessage message : messages) {
            ChatCompletionChoice theokanningChoice = new ChatCompletionChoice();
            theokanningChoice.setIndex(openAIChoice.index());
            theokanningChoice.setMessage(message);
            theokanningChoice.setFinishReason(openAIChoice.finishReason());
            chatCompletionChoics.add(theokanningChoice);
        }
        return chatCompletionChoics;
    }

    private static List<ChatMessage> createToolResultMessages(Delta delta){
        ChatFunctionCall chatFunctionCall = new ChatFunctionCall();
        chatFunctionCall.setName(delta.toolCalls().get(0).function().name());

        ChatMessage theokanningMessage = new ChatMessage();
        theokanningMessage.setRole("function");
        theokanningMessage.setContent(delta.content());
        theokanningMessage.setFunctionCall(chatFunctionCall);
        return List.of(theokanningMessage);
    }

    private static List<ChatMessage> createAiMessages(String content) {
        ChatMessage theokanningMessage = new ChatMessage();
        theokanningMessage.setRole("assistant");
        theokanningMessage.setContent(content);
        return List.of(theokanningMessage);
    }

    private static List<ChatMessage> createToolRequestMessages(List<ToolCall> toolCalls) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        List<ChatMessage> theokanningMessages = new ArrayList<>();
        for(ToolCall toolCall : toolCalls){
            ChatMessage theokanningMessage = new ChatMessage();
            theokanningMessage.setRole("assistant");

            ChatFunctionCall chatFunctionCall = new ChatFunctionCall();
            chatFunctionCall.setName(toolCall.function().name());
            JsonNode arguments = mapper.readTree(toolCall.function().arguments());
            chatFunctionCall.setArguments(arguments);
            theokanningMessages.add(theokanningMessage);
        }
        return theokanningMessages;
    }
}