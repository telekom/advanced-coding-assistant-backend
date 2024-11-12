package com.telekom.ai4coding.chatbot.repository.conversation;

import dev.langchain4j.data.message.ChatMessageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConversationNodeTest {

    private ConversationNode conversationNode;
    private MessageNode userMessageNode;
    private MessageNode aiMessageNode;

    @BeforeEach
    void setUp() {
        // Given a ConversationNode with two MessageNodes, one from User and one from AI
        userMessageNode = MessageNode.of(ChatMessageType.USER, "User Message", null);
        aiMessageNode = MessageNode.of(ChatMessageType.AI, "AI Message", userMessageNode);
        conversationNode = ConversationNode.of("Test Conversation", Arrays.asList(aiMessageNode, userMessageNode));
    }

    @Test
    void givenConversationWithUnorderedMessages_whenGettingChildMessages_thenMessagesShouldBeSorted() {
        // When we get the child messages
        List<MessageNode> sortedMessages = conversationNode.getChildMessageNodes();

        // Then the messages should be sorted by their index
        assertNotNull(sortedMessages);
        assertEquals(2, sortedMessages.size());
        assertEquals(userMessageNode, sortedMessages.get(0));  // User Message has index 0
        assertEquals(aiMessageNode, sortedMessages.get(1));    // AI Message has index 1
    }

    @Test
    void givenConversationWithNoMessages_whenGettingChildMessages_thenReturnEmptyList() {
        // Given a ConversationNode with no messages
        ConversationNode emptyConversation = ConversationNode.of("Empty Conversation", Collections.emptyList());

        // When we get the child messages
        List<MessageNode> childMessages = emptyConversation.getChildMessageNodes();

        // Then an empty list should be returned
        assertNotNull(childMessages);
        assertTrue(childMessages.isEmpty());
    }

    @Test
    void givenConversationNodeWithNullMessages_whenGettingChildMessages_thenReturnNull() {
        // Given a ConversationNode with null message list
        ConversationNode nullMessageNodeConversation = ConversationNode.of("Conversation with null messages", null);

        // When we get the child messages
        List<MessageNode> childMessages = nullMessageNodeConversation.getChildMessageNodes();

        // Then null should be returned
        assertNull(childMessages);
    }

    @Test
    void givenConversationTitle_whenCreatingConversationNode_thenTitleShouldBeSet() {
        // When we create a ConversationNode with a title
        ConversationNode node = ConversationNode.of("New Conversation");

        // Then the title should be set, and child messages should be null
        assertNotNull(node);
        assertEquals("New Conversation", node.getTitle());
        assertNull(node.getChildMessageNodes());
    }

    @Test
    void givenConversationWithMessages_whenCreatingNode_thenMessagesShouldBeSet() {
        // When we create a ConversationNode with a title and messages
        ConversationNode node = ConversationNode.of("New Conversation", Arrays.asList(userMessageNode, aiMessageNode));

        // Then the title and messages should be correctly set
        assertNotNull(node);
        assertEquals("New Conversation", node.getTitle());
        assertEquals(2, node.getChildMessageNodes().size());
    }

    @Test
    void givenExistingConversationNode_whenCreatingNewNodeWithUpdatedMessages_thenMessagesShouldBeUpdated() {
        // Given an existing ConversationNode with messages
        ConversationNode updatedNode = ConversationNode.of(conversationNode, Arrays.asList(userMessageNode));

        // When we create a new ConversationNode with updated child messages
        // Then the id and title should be the same, but child messages should be updated
        assertNotNull(updatedNode);
        assertEquals(conversationNode.getId(), updatedNode.getId());
        assertEquals(conversationNode.getTitle(), updatedNode.getTitle());
        assertEquals(1, updatedNode.getChildMessageNodes().size());
    }
}
