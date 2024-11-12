package com.telekom.ai4coding.chatbot.repository.conversation;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageType;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.ToolExecutionResultMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.agent.tool.ToolExecutionRequest;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.List;

/**
 * LIFO - last Message in, first Message out
 * MessageNode <-IS_AFTER- MessageNode <-IS_AFTER- MessageNode ...
 * 
 * Each MessageNode is a representation of the Langchain4J ChatMessage. For all There are
 * four ChatMessage types and id, createdAt, updatedAt, chatMessageType attributes
 * are set for all MessageNode. The unique attributes for each type are:
 * * SYSTEM: Represents system message, the text attribute is set.
 * * USER: Represents user message, the text attribute is set.
 * * AI: Represents response from AI, either text or toolExecutionRequests is set.
 * * TOOL_EXECUTION_RESULT: Represents execution result from AI, currently not yet
 *      supported.
 */
@Getter
@Node
public class MessageNode {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private final String id;

    // The index in the parent ConversationNode, denotes the order within the
    // list of MessageNode.
    private final int index;

    // Can only be one of SYSTEM, USER, AI or TOOL_EXECUTION_RESULT.
    private final ChatMessageType chatMessageType;

    // Only set by SYSTEM and USER. AI may set this is if toolExecutionRequests
    // is not null.
    private final String text;

    // Only set by AI if text is null.
    private List<ToolExecutionRequest> toolExecutionRequests;

    // Only set by TOOL_EXECUTION_RESULT
    private ToolExecutionResultMessage toolExecutionResult;

    @Relationship(type = "IS_AFTER", direction = Relationship.Direction.OUTGOING)
    private final MessageNode previous;

    private MessageNode(String id,
                        int index,
                        ChatMessageType chatMessageType,
                        String text,
                        MessageNode previous,
                        List<ToolExecutionRequest> toolExecutionRequests,
                        ToolExecutionResultMessage toolExecutionResult) {
        this.id = id;
        this.index = index;
        this.chatMessageType = chatMessageType;
        this.text = text;
        this.previous = previous;
        this.toolExecutionRequests = toolExecutionRequests;
        this.toolExecutionResult = toolExecutionResult;
    }

    public boolean hasToolExecutionRequests(){
        return toolExecutionRequests!= null && !toolExecutionRequests.isEmpty();
    }

    public static MessageNode of(ChatMessage chatMessage, MessageNode previous) {
        int currentIndex = previous == null ? 0 : previous.getIndex()+1;
        return switch (chatMessage) {
            case SystemMessage systemMessage ->
                new MessageNode(
                    null, currentIndex,
                    ChatMessageType.SYSTEM, systemMessage.text(), previous,
                    null, null);
            case UserMessage userMessage ->
                new MessageNode(
                    null, currentIndex,
                    ChatMessageType.USER, userMessage.singleText(), previous,
                    null, null);
            case AiMessage aiMessage ->
                new MessageNode(
                    null, currentIndex,
                    ChatMessageType.AI, aiMessage.text(), previous,
                    aiMessage.toolExecutionRequests(), null);
            case ToolExecutionResultMessage toolExecutionResultMessage ->
                new MessageNode(
                    null, currentIndex,
                    ChatMessageType.TOOL_EXECUTION_RESULT, null, previous,
                    null, toolExecutionResultMessage);
    
            default -> throw new UnsupportedOperationException("Unsupported message type: " + chatMessage.type());
        };
    }

    public static MessageNode of(ChatMessage chatMessage) {
        return of(chatMessage, null);
    }

    public static MessageNode of(ChatMessageType chatMessageType,
                                 String text,
                                 MessageNode previous) {
        int currentIndex = previous == null ? 0 : previous.getIndex()+1;
        return new MessageNode(null, currentIndex,
                chatMessageType, text, previous, null, null);
    }

    public static MessageNode of(ChatMessageType chatMessageType, String text) {
        return new MessageNode(null, 0, chatMessageType, text, null, null, null);
    }
}
