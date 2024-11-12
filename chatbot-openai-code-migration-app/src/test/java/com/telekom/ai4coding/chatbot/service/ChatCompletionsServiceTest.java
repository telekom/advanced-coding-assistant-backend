package com.telekom.ai4coding.chatbot.service;

import com.telekom.ai4coding.chatbot.configuration.agent.GeneralAgent;
import com.telekom.ai4coding.chatbot.configuration.agent.OpenAiAgent;
import com.telekom.ai4coding.chatbot.repository.conversation.MessageNode;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;

import com.theokanning.openai.completion.chat.ChatMessage;
import dev.langchain4j.data.message.ChatMessageType;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.Pair;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class ChatCompletionsServiceTest {

    @Mock
    private GeneralAgent generalAgent;

    @Mock
    private OpenAiAgent openAiAgent;

    @Mock
    private ConversationService conversationService;

    @Mock
    private EmbeddingSearchService embeddingSearchService;

    @InjectMocks
    private ChatCompletionsService chatCompletionsService;

    @Test
    void chatWithPersistence_existingConversation() {
        String conversationId = "existingId";
        String userMessage = "Hello";
        ChatCompletionRequest request = mock(ChatCompletionRequest.class, RETURNS_DEEP_STUBS);
        when(request.getMessages().getLast().getContent()).thenReturn(userMessage);

        String agentResponse = "Hi there!";
        MessageNode messageNode = MessageNode.of(ChatMessageType.AI, agentResponse);
        when(conversationService.getMessagesNodesAfterLastUserMessage(conversationId)).thenReturn(Collections.singletonList(messageNode));

        String context = "Say hello";
        when(embeddingSearchService.getContextUsingEmbedding(userMessage)).thenReturn(context);

        Pair<ChatCompletionResult, String> result = chatCompletionsService.chatWithPersistence(request, conversationId);

        assertNotNull(result);
        assertEquals(conversationId, result.getSecond());
        assertNotNull(result.getFirst());
        assertEquals(agentResponse, result.getFirst().getChoices().get(0).getMessage().getContent());

        String extendedUserMessage = userMessage = (
                userMessage + System.getProperty("line.separator") +
                "Context found using embedding search:" + System.getProperty("line.separator") +
                context);

        verify(generalAgent, times(1)).chat(conversationId, extendedUserMessage);
        verify(conversationService, times(1)).getMessagesNodesAfterLastUserMessage(conversationId);
    }

    @Test
    void chatWithPersistence_newConversation() {
        String newConversationId = "newId";
        String userMessage = "Hello";
        ChatCompletionRequest request = mock(ChatCompletionRequest.class, RETURNS_DEEP_STUBS);
        when(request.getMessages().getLast().getContent()).thenReturn(userMessage);

        String agentResponse = "Hi there!";
        when(conversationService.createNewConversation(userMessage)).thenReturn(newConversationId);
        MessageNode messageNode = MessageNode.of(ChatMessageType.AI, agentResponse);
        when(conversationService.getMessagesNodesAfterLastUserMessage(newConversationId)).thenReturn(Collections.singletonList(messageNode));

        when(embeddingSearchService.getContextUsingEmbedding(userMessage)).thenReturn("");

        Pair<ChatCompletionResult, String> result = chatCompletionsService.chatWithPersistence(request, null);

        assertNotNull(result);
        assertEquals(newConversationId, result.getSecond());
        assertNotNull(result.getFirst());
        assertEquals(agentResponse, result.getFirst().getChoices().get(0).getMessage().getContent());

        verify(conversationService, times(1)).createNewConversation(userMessage);
        verify(generalAgent, times(1)).chat(newConversationId, userMessage);
        verify(conversationService, times(1)).getMessagesNodesAfterLastUserMessage(newConversationId);
    }

    @Test
    void chatWithoutPersistenceConversation() {
    String userMessage = "Hello";
    ChatMessage chatMessage = new ChatMessage();
    chatMessage.setContent(userMessage);
    ChatCompletionRequest request = mock(ChatCompletionRequest.class, RETURNS_DEEP_STUBS);
    when(request.getMessages()).thenReturn(Collections.singletonList(chatMessage));
    String agentResponse = "Hi there!";
    when(openAiAgent.chat(userMessage+"\n")).thenReturn(agentResponse);

    ChatCompletionResult result = chatCompletionsService.chatWithoutPersistence(request);

    assertNotNull(result);
    assertEquals(agentResponse, result.getChoices().get(0).getMessage().getContent());

    verify(openAiAgent, times(1)).chat(userMessage+"\n");

    }

}