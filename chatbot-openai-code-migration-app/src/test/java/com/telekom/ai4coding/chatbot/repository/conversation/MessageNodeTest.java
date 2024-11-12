package com.telekom.ai4coding.chatbot.repository.conversation;

import dev.langchain4j.data.message.*;
import dev.langchain4j.agent.tool.ToolExecutionRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageNodeTest {

    private MessageNode userMessageNode;
    private MessageNode aiMessageNode;
    private MessageNode systemMessageNode;
    private MessageNode toolExecutionResultMessageNode;

    @BeforeEach
    void setUp() {
        // Given a User message and an AI message as part of the conversation
        userMessageNode = MessageNode.of(ChatMessageType.USER, "Hello, this is a user message", null);
        aiMessageNode = MessageNode.of(ChatMessageType.AI, "Hello, I am AI", userMessageNode);

        // Given a system message and tool execution result message
        systemMessageNode = MessageNode.of(new SystemMessage("System initialization"), null);
        ToolExecutionResultMessage toolExecutionResultMessage = new ToolExecutionResultMessage(
                "12",
                "ToolTest",
                "Tool Execution Result"
        );
        toolExecutionResultMessageNode = MessageNode.of(toolExecutionResultMessage, aiMessageNode);
    }

    @Test
    void givenUserMessage_whenCreatingNode_thenCorrectIndexAndTypeShouldBeSet() {
        // Given a User message with no previous message

        // When creating the node for the user message
        // Then the index should be 0, and the message type should be USER
        assertEquals(0, userMessageNode.getIndex());
        assertEquals(ChatMessageType.USER, userMessageNode.getChatMessageType());

        // And the text should match the given user message
        assertEquals("Hello, this is a user message", userMessageNode.getText());

        // And there should be no previous message
        assertNull(userMessageNode.getPrevious());
    }

    @Test
    void givenAiMessage_whenCreatingNode_thenCorrectIndexAndTypeShouldBeSet() {
        // Given an AI message with a previous User message

        // When creating the node for the AI message
        // Then the index should increment by 1
        assertEquals(1, aiMessageNode.getIndex());

        // And the message type should be AI
        assertEquals(ChatMessageType.AI, aiMessageNode.getChatMessageType());

        // And the AI message text should be set correctly
        assertEquals("Hello, I am AI", aiMessageNode.getText());

        // And the previous message should be the user message
        assertNotNull(aiMessageNode.getPrevious());
        assertEquals(userMessageNode, aiMessageNode.getPrevious());
    }

    @Test
    void givenSystemMessage_whenCreatingNode_thenCorrectTypeAndNoPrevious() {
        // Given a system message with no previous message

        // When creating the node for the system message
        // Then the index should be 0
        assertEquals(0, systemMessageNode.getIndex());

        // And the message type should be SYSTEM
        assertEquals(ChatMessageType.SYSTEM, systemMessageNode.getChatMessageType());

        // And the text should be set as the system message
        assertEquals("System initialization", systemMessageNode.getText());

        // And there should be no previous message
        assertNull(systemMessageNode.getPrevious());
    }

    @Test
    void givenToolExecutionResultMessage_whenCreatingNode_thenCorrectTypeAndPreviousSet() {
        // Given a tool execution result message with a previous AI message

        // When creating the node for the tool execution result message
        // Then the index should be 2, as it follows the AI message
        assertEquals(2, toolExecutionResultMessageNode.getIndex());

        // And the message type should be TOOL_EXECUTION_RESULT
        assertEquals(ChatMessageType.TOOL_EXECUTION_RESULT, toolExecutionResultMessageNode.getChatMessageType());

        // And there should be no text in the tool execution result message

        assertNull(toolExecutionResultMessageNode.getText());

        // And the previous message should be the AI message
        assertNotNull(toolExecutionResultMessageNode.getPrevious());
        assertEquals(aiMessageNode, toolExecutionResultMessageNode.getPrevious());
    }

    @Test
    void givenAiMessageWithToolExecutionRequests_whenCreatingNode_thenToolRequestsShouldBeSet() {
        // Given tool execution requests for an AI message
        ToolExecutionRequest toolRequest1 = ToolExecutionRequest.builder()
                .id("1")
                .name("ToolA")
                .arguments("arg1")
                .build();

        ToolExecutionRequest toolRequest2 = ToolExecutionRequest.builder()
                .id("2")
                .name("ToolB")
                .arguments("arg2")
                .build();

        List<ToolExecutionRequest> toolExecutionRequests = List.of(toolRequest1, toolRequest2);
        AiMessage aiMessageWithTools = new AiMessage("AI response", toolExecutionRequests);

        // When creating the node for the AI message with tool requests
        MessageNode aiNodeWithTools = MessageNode.of(aiMessageWithTools, userMessageNode);

        // Then the AI message node should contain the tool execution requests
        assertTrue(aiNodeWithTools.hasToolExecutionRequests());
        assertEquals(2, aiNodeWithTools.getToolExecutionRequests().size());

        // And the tool execution requests should match the given data
        ToolExecutionRequest firstRequest = aiNodeWithTools.getToolExecutionRequests().get(0);
        ToolExecutionRequest secondRequest = aiNodeWithTools.getToolExecutionRequests().get(1);

        assertEquals("1", firstRequest.id());
        assertEquals("ToolA", firstRequest.name());
        assertEquals("arg1", firstRequest.arguments());

        assertEquals("2", secondRequest.id());
        assertEquals("ToolB", secondRequest.name());
        assertEquals("arg2", secondRequest.arguments());
    }

    @Test
    void givenAiMessageWithoutToolExecutionRequests_whenCreatingNode_thenShouldReturnFalse() {
        // Given an AI message with no tool execution requests
        AiMessage aiMessageWithoutTools = new AiMessage("AI response");

        // When creating the node for the AI message without tool requests
        MessageNode aiNodeWithoutTools = MessageNode.of(aiMessageWithoutTools, userMessageNode);

        // Then the node should indicate no tool execution requests
        assertFalse(aiNodeWithoutTools.hasToolExecutionRequests());
        assertNull(aiNodeWithoutTools.getToolExecutionRequests());
    }

    @Test
    void whenPreviousMessageIsNull_thenIndexShouldBeZero() {
        // Given a message with no previous message

        // When creating the node
        MessageNode firstMessage = MessageNode.of(ChatMessageType.USER, "First message");

        // Then the index should be 0, and there should be no previous message
        assertEquals(0, firstMessage.getIndex());
        assertNull(firstMessage.getPrevious());
    }

    @Test
    void whenPreviousMessageIsNotNull_thenIndexShouldIncrement() {
        // Given a user message followed by an AI message

        // When creating the AI message node
        MessageNode secondMessage = MessageNode.of(ChatMessageType.AI, "AI response", userMessageNode);

        // Then the index should be incremented to 1
        assertEquals(1, secondMessage.getIndex());
    }
}
