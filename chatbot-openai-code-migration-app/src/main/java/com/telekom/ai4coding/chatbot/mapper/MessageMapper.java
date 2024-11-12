package com.telekom.ai4coding.chatbot.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.telekom.ai4coding.chatbot.repository.conversation.MessageNode;

import dev.ai4j.openai4j.chat.AssistantMessage;
import dev.ai4j.openai4j.chat.ChatCompletionChoice;
import dev.ai4j.openai4j.chat.Delta;
import dev.ai4j.openai4j.chat.FunctionCall;
import dev.ai4j.openai4j.chat.Role;
import dev.ai4j.openai4j.chat.ToolCall;
import dev.ai4j.openai4j.chat.ToolType;
import dev.langchain4j.agent.tool.ToolExecutionRequest;
import dev.langchain4j.data.message.*;

public class MessageMapper {

    public static ChatMessage mapToChatMessage(final MessageNode messageNode) {
        return switch (messageNode.getChatMessageType()) {
            case SYSTEM -> new SystemMessage(messageNode.getText());
            case USER -> new UserMessage(messageNode.getText());
            case AI -> {
                if(messageNode.hasToolExecutionRequests()){
                    yield new AiMessage(messageNode.getToolExecutionRequests());
                }else{
                    yield new AiMessage(messageNode.getText());
                }
            }
            case TOOL_EXECUTION_RESULT -> messageNode.getToolExecutionResult();
            default ->
                    throw new UnsupportedOperationException("Message type: " + messageNode.getChatMessageType().name()
                            + ". Only chat SYSTEM, USER and AI message types are supported.");
        };
    }

    public static MessageNode mapToMessageNode(
        final ChatMessage chatMessage, final MessageNode from) {
        return MessageNode.of(chatMessage, from);
    }

    public static MessageNode mapToMessageNode(final ChatMessage chatMessage) {
        return MessageNode.of(chatMessage);
    }

    public static ChatCompletionChoice mapToChatCompletionChoice(final MessageNode messageNode) {
        if(messageNode.getChatMessageType().equals(ChatMessageType.AI)) {
            if(messageNode.hasToolExecutionRequests()){
                return toolExecutionRequestToChatCompletionChoice(messageNode.getToolExecutionRequests());
            }else{
                return aiTextToChatCompletionChoice(messageNode.getText());
            }
        }else if(messageNode.getChatMessageType().equals(ChatMessageType.TOOL_EXECUTION_RESULT)) {
            return toolExecutionResultToChatCompletionChoice(messageNode.getToolExecutionResult());
        }


        return null;
    }

    private static ChatCompletionChoice toolExecutionRequestToChatCompletionChoice(
            List<ToolExecutionRequest> toolExecutionRequests) {
        List<ToolCall> toolCalls = new ArrayList<>();
        for(ToolExecutionRequest request : toolExecutionRequests) {
            FunctionCall functionCall = FunctionCall.builder()
                    .name(request.name())
                    .arguments(request.arguments())
                    .build();
            ToolCall toolCall = ToolCall.builder()
                    .id(request.id())
                    .type(ToolType.FUNCTION)
                    .function(functionCall)
                    .build();
            toolCalls.add(toolCall);
        }
        AssistantMessage assistantMessage = AssistantMessage.builder()
                .toolCalls(toolCalls)
                .build();
        return ChatCompletionChoice.builder()
                .message(assistantMessage)
                .build();
    }

    private static ChatCompletionChoice aiTextToChatCompletionChoice(String text) {
        AssistantMessage assistantMessage = AssistantMessage.builder()
                .content(text)
                .build();
        return ChatCompletionChoice.builder()
                .message(assistantMessage)
                .build();
    }

    private static ChatCompletionChoice toolExecutionResultToChatCompletionChoice(
        ToolExecutionResultMessage toolExecutionResult) {

        FunctionCall functionCall = FunctionCall.builder()
                .name(toolExecutionResult.toolName())
                .build();
        ToolCall toolCall = ToolCall.builder()
                .id(toolExecutionResult.id())
                .type(ToolType.FUNCTION)
                .function(functionCall)
                .build();
        Delta delta = Delta.builder()
                .role(Role.TOOL)
                .content(toolExecutionResult.text())
                .toolCalls(Collections.singletonList(toolCall))
                .build();
        return ChatCompletionChoice.builder()
                .delta(delta)
                .build();
        
    }
}
