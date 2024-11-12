package com.telekom.ai4coding.chatbot.mapper;

import com.telekom.ai4coding.chatbot.repository.conversation.MessageNode;

import dev.ai4j.openai4j.chat.ChatCompletionChoice;
import dev.ai4j.openai4j.chat.Role;
import dev.langchain4j.agent.tool.ToolExecutionRequest;
import dev.langchain4j.data.message.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class MessageMapperTest {

    @Test
    void mapMessageNodeToChatMessage_system() {
        MessageNode messageNode = MessageNode.of(ChatMessageType.SYSTEM, "System message");
        ChatMessage chatMessage = MessageMapper.mapToChatMessage(messageNode);

        assertInstanceOf(SystemMessage.class, chatMessage);
        assertEquals("System message", chatMessage.text());
    }

    @Test
    void mapMessageNodeToChatMessage_user() {
        MessageNode messageNode = MessageNode.of(ChatMessageType.USER, "User message");
        ChatMessage chatMessage = MessageMapper.mapToChatMessage(messageNode);

        assertInstanceOf(UserMessage.class, chatMessage);
        assertEquals("User message", chatMessage.text());
    }

    @Test
    void mapMessageNodeToChatMessage_ai() {
        MessageNode messageNode = MessageNode.of(ChatMessageType.AI, "AI message");
        ChatMessage chatMessage = MessageMapper.mapToChatMessage(messageNode);

        assertInstanceOf(AiMessage.class, chatMessage);
        assertEquals("AI message", chatMessage.text());
    }

    @Test
    void mapMessageNodeToChatMessage_tool() {
        ToolExecutionResultMessage toolExecutionResultMessage = new ToolExecutionResultMessage(
            "id", "toolName", "text");
        MessageNode messageNode = MessageNode.of(toolExecutionResultMessage);
        ChatMessage chatMessage = MessageMapper.mapToChatMessage(messageNode);

        assertInstanceOf(ToolExecutionResultMessage.class, chatMessage);
        assertEquals("id", ((ToolExecutionResultMessage) chatMessage).id());
        assertEquals("toolName", ((ToolExecutionResultMessage) chatMessage).toolName());
        assertEquals("text", ((ToolExecutionResultMessage) chatMessage).text());
    }

    @Test
    void mapChatMessageToMessageNode_system() {
        SystemMessage systemMessage = new SystemMessage("System message");
        MessageNode messageNode = MessageMapper.mapToMessageNode(systemMessage, null);

        assertEquals(ChatMessageType.SYSTEM, messageNode.getChatMessageType());
        assertEquals("System message", messageNode.getText());
    }

    @Test
    void mapChatMessageToMessageNode_user() {
        UserMessage userMessage = new UserMessage("User message");
        MessageNode messageNode = MessageMapper.mapToMessageNode(userMessage, null);

        assertEquals(ChatMessageType.USER, messageNode.getChatMessageType());
        assertEquals("User message", messageNode.getText());
    }

    @Test
    void mapChatMessageToMessageNode_ai() {
        AiMessage aiMessage = new AiMessage("AI message");
        MessageNode messageNode = MessageMapper.mapToMessageNode(aiMessage, null);

        assertEquals(ChatMessageType.AI, messageNode.getChatMessageType());
        assertEquals("AI message", messageNode.getText());
    }

    @Test
    void mapChatMessageToMessageNode_tool() {
        ToolExecutionResultMessage toolExecutionResultMessage = new ToolExecutionResultMessage(
            "id", "toolName", "text");
        MessageNode messageNode = MessageMapper.mapToMessageNode(toolExecutionResultMessage, null);

        assertEquals(ChatMessageType.TOOL_EXECUTION_RESULT, messageNode.getChatMessageType());
        assertEquals(toolExecutionResultMessage, messageNode.getToolExecutionResult());
    }


    @Test
    void mapChatMessageToMessageNodeWithoutFrom_system() {
        SystemMessage systemMessage = new SystemMessage("System message");
        MessageNode messageNode = MessageMapper.mapToMessageNode(systemMessage);

        assertEquals(ChatMessageType.SYSTEM, messageNode.getChatMessageType());
        assertEquals("System message", messageNode.getText());
    }

    @Test
    void mapChatMessageToMessageNodeWithoutFrom_user() {
        UserMessage userMessage = new UserMessage("User message");
        MessageNode messageNode = MessageMapper.mapToMessageNode(userMessage);

        assertEquals(ChatMessageType.USER, messageNode.getChatMessageType());
        assertEquals("User message", messageNode.getText());
    }

    @Test
    void mapChatMessageToMessageNodeWithoutFrom_ai() {
        AiMessage aiMessage = new AiMessage("AI message");
        MessageNode messageNode = MessageMapper.mapToMessageNode(aiMessage);

        assertEquals(ChatMessageType.AI, messageNode.getChatMessageType());
        assertEquals("AI message", messageNode.getText());
    }

    @Test
    void mapChatMessageToMessageNodeWithoutFrom_tool() {
        ToolExecutionResultMessage toolExecutionResultMessage = new ToolExecutionResultMessage(
            "id", "toolName", "text");
        MessageNode messageNode = MessageMapper.mapToMessageNode(toolExecutionResultMessage);

        assertEquals(ChatMessageType.TOOL_EXECUTION_RESULT, messageNode.getChatMessageType());
        assertEquals(toolExecutionResultMessage, messageNode.getToolExecutionResult());
    }

    @Test
    void mapToChatCompletionChoiceFrom_ai() {
        MessageNode messageNode = MessageNode.of(ChatMessageType.AI, "Hello world");

        ChatCompletionChoice choice = MessageMapper.mapToChatCompletionChoice(messageNode);
        assertNull(choice.delta());
        assertNotNull(choice.message());
        assertNull(choice.message().toolCalls());
        assertEquals("Hello world", choice.message().content());
    }

    @Test
    void mapToChatCompletionChoiceFrom_toolRequest() {
        String tool1Id = "1";
        String tool1Name = "tool1";
        String tool1Arguments = "argument1";
        ToolExecutionRequest toolExecutionRequest1 = ToolExecutionRequest.builder()
                .id(tool1Id)
                .name(tool1Name)
                .arguments(tool1Arguments)
                .build();
        String tool2Id = "2";
        String tool2Name = "tool2";
        String tool2Arguments = "argument2";
        ToolExecutionRequest toolExecutionRequest2 = ToolExecutionRequest.builder()
                .id(tool2Id)
                .name(tool2Name)
                .arguments(tool2Arguments)
                .build();
        List<ToolExecutionRequest> toolExecutionRequests = new ArrayList<>();
        toolExecutionRequests.add(toolExecutionRequest1);
        toolExecutionRequests.add(toolExecutionRequest2);
        MessageNode messageNode = MessageNode.of(new AiMessage(toolExecutionRequests));

        ChatCompletionChoice choice = MessageMapper.mapToChatCompletionChoice(messageNode);
        assertNull(choice.delta());
        assertNotNull(choice.message());
        assertNotNull(choice.message().toolCalls());
        assertFalse(choice.message().toolCalls().isEmpty());
        assertEquals(2, choice.message().toolCalls().size());
        assertEquals(tool1Id, choice.message().toolCalls().get(0).id());
        assertEquals(tool1Name, choice.message().toolCalls().get(0).function().name());
        assertEquals(tool1Arguments, choice.message().toolCalls().get(0).function().arguments());

        assertEquals(tool2Id, choice.message().toolCalls().get(1).id());
        assertEquals(tool2Name, choice.message().toolCalls().get(1).function().name());
        assertEquals(tool2Arguments, choice.message().toolCalls().get(1).function().arguments());
    }

    @Test
    void mapToChatCompletionChoiceFrom_toolResult() {
        String toolId = "id";
        String toolName = "toolName";
        String toolExecutionResult = "toolExecutionResult";
        ToolExecutionResultMessage toolExecutionResultMessage = ToolExecutionResultMessage.from(
            toolId, toolName, toolExecutionResult);
        
        MessageNode messageNode = MessageNode.of(toolExecutionResultMessage);
        ChatCompletionChoice choice = MessageMapper.mapToChatCompletionChoice(messageNode);
        assertNull(choice.message());
        assertNotNull(choice.delta());
        assertEquals(Role.TOOL, choice.delta().role());
        assertEquals(toolExecutionResult, choice.delta().content());
        assertNotNull(choice.delta().toolCalls());
        assertEquals(1, choice.delta().toolCalls().size());
        assertEquals(toolId, choice.delta().toolCalls().get(0).id());
        assertEquals(toolName, choice.delta().toolCalls().get(0).function().name());
    }


    @Test
    void mapToChatCompletionChoiceFrom_user() {
        MessageNode messageNode = MessageNode.of(ChatMessageType.USER, "hello");
        ChatCompletionChoice choice = MessageMapper.mapToChatCompletionChoice(messageNode);
        assertNull(choice);
    }


}