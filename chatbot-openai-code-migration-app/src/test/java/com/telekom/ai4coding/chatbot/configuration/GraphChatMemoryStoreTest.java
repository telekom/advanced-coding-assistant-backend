package com.telekom.ai4coding.chatbot.configuration;

import com.telekom.ai4coding.chatbot.repository.ConversationNodeRepository;
import com.telekom.ai4coding.chatbot.repository.conversation.ConversationNode;
import com.telekom.ai4coding.chatbot.repository.conversation.MessageNode;
import com.telekom.ai4coding.chatbot.service.ConversationService;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageType;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GraphChatMemoryStoreTest {

    @Mock
    private ConversationNodeRepository conversationNodeRepository;

    @Mock
    private ConversationService conversationService;

    @InjectMocks
    private GraphChatMemoryStore graphChatMemoryStore;

    @Test
    void getMessages_validMemoryId() {
        String conversationId = "validId";
        List<MessageNode> messageNodes = List.of(
                MessageNode.of(ChatMessageType.SYSTEM, "System"),
                MessageNode.of(ChatMessageType.USER, "User"));
        ConversationNode conversationNode = ConversationNode.of(
            "title", messageNodes);
        when(conversationNodeRepository.findById(conversationId))
                .thenReturn(Optional.of(conversationNode));

        List<ChatMessage> chatMessages = graphChatMemoryStore.getMessages(conversationId);

        assertNotNull(chatMessages);
        assertEquals(messageNodes.size(), chatMessages.size());
        verify(conversationNodeRepository, times(1))
                .findById(conversationId);
    }

    @Test
    void getMessages_invalidMemoryId() {
        assertThrows(IllegalArgumentException.class, () -> graphChatMemoryStore.getMessages(123L));
    }

    @Test
    void updateMessages_validMemoryId() {
        String conversationId = "validId";
        List<ChatMessage> messages = List.of(new SystemMessage("System"), new UserMessage("User"));
        ConversationNode conversationNodeMock = mock(ConversationNode.class);
        when(conversationNodeRepository.findById(conversationId))
                .thenReturn(Optional.of(conversationNodeMock));

        graphChatMemoryStore.updateMessages(conversationId, messages);

        verify(conversationNodeRepository, times(1)).findById(conversationId);
        verify(conversationNodeRepository, times(1)).save(any(ConversationNode.class));
    }

    @Test
    void updateMessages_invalidMemoryId() {
        assertThrows(IllegalArgumentException.class, () -> graphChatMemoryStore.updateMessages(123L, List.of()));
    }

    @Test
    void deleteMessages_validMemoryId() {
        String conversationId = "validId";

        graphChatMemoryStore.deleteMessages(conversationId);

        verify(conversationService, times(1)).deleteConversation(conversationId);
    }

    @Test
    void deleteMessages_invalidMemoryId() {
        assertThrows(IllegalArgumentException.class, () -> graphChatMemoryStore.deleteMessages(123L));
    }
}