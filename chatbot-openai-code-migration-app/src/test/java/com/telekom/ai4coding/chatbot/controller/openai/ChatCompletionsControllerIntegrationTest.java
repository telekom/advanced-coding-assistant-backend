package com.telekom.ai4coding.chatbot.controller.openai;

import com.telekom.ai4coding.chatbot.BaseIntegrationTest;
import com.telekom.ai4coding.chatbot.service.ChatCompletionsService;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import dev.ai4j.openai4j.OpenAiClient;
import dev.ai4j.openai4j.SyncOrAsyncOrStreaming;
import dev.ai4j.openai4j.chat.AssistantMessage;
import dev.ai4j.openai4j.chat.ChatCompletionChoice;
import dev.ai4j.openai4j.chat.ChatCompletionResponse;
import dev.ai4j.openai4j.chat.Delta;
import dev.ai4j.openai4j.shared.Usage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;

import static dev.ai4j.openai4j.chat.Role.USER;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ChatCompletionsControllerIntegrationTest extends BaseIntegrationTest {

    @MockBean
    private OpenAiClient client;

    @MockBean
    private ChatCompletionsService chatCompletionsService;

    @Autowired
    private MockMvc mockMvc;

    public static ChatCompletionRequest createChatCompletionRequest() {
        ChatCompletionRequest chatCompletionRequest = new ChatCompletionRequest();
        chatCompletionRequest.setModel( "davinci" );
        chatCompletionRequest.setMessages(
                Collections.singletonList(new ChatMessage("User", "Tell me a Joke")) );
        chatCompletionRequest.setN( 1 );
        chatCompletionRequest.setStop( null );
        chatCompletionRequest.setTemperature( 0.7 );
        chatCompletionRequest.setMaxTokens( 150 );
        chatCompletionRequest.setTopP( 1.0 );
        chatCompletionRequest.setFrequencyPenalty( 0.0 );
        chatCompletionRequest.setPresencePenalty( 0.0 );
        chatCompletionRequest.setStream( false );
        chatCompletionRequest.setUser( "user" );
        return chatCompletionRequest;
    }

    @BeforeEach
    void setUp() {
        ChatCompletionResponse completionResponse = ChatCompletionResponse.builder()
                .id("some-id")
                .model("davinci")
                .created(13032024)
                .choices( List.of(
                        ChatCompletionChoice.builder()
                                .index( 0 )
                                .message( AssistantMessage.builder()
                                        .name( "Assistant" )
                                        .content( "Hello" )
                                        .build() )
                                .delta( Delta.builder()
                                        .content( "Hello" )
                                        .role( USER )
                                        .build() )
                                .finishReason( "stop" )
                                .build()))
                .usage( Usage.builder()
                        .totalTokens(7)
                        .promptTokens(0)
                        .completionTokens(0)
                        .build())
                .systemFingerprint( "some-fingerprint" )
                .build();

        @SuppressWarnings("unchecked")
        SyncOrAsyncOrStreaming<ChatCompletionResponse> syncOrAsyncOrStreaming = Mockito.mock(SyncOrAsyncOrStreaming.class);
        when(client.chatCompletion(
                any(dev.ai4j.openai4j.chat.ChatCompletionRequest.class))).thenReturn(syncOrAsyncOrStreaming);
        when(syncOrAsyncOrStreaming.execute()).thenReturn(completionResponse);
    }

    @Test
    @Disabled("disabled because the mocking of openai does nor work -> tries to got to real endpoint")
    void shouldReturnOkWhenCreateChatCompletionIsCalledWithValidRequest() throws Exception {

        mockMvc.perform( MockMvcRequestBuilders.post("/v1/chat/completions")
                        .contentType( MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(createChatCompletionRequest())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.created", notNullValue()))
                .andExpect(jsonPath("$.model", notNullValue()))
                .andExpect(jsonPath("$.choices", notNullValue()))
                .andExpect(jsonPath("$.usage", notNullValue()));

        verify(client, times(1))
                .chatCompletion(any(dev.ai4j.openai4j.chat.ChatCompletionRequest.class));
    }

    @Test
    void shouldReturnBadRequestWhenCreateChatCompletionIsCalledWithInvalidRequest() throws Exception {
        String invalidJsonPayload = "{\n" +
                "  \"model\": \"davinci\",\n" +
                "  \"messages\": [],\n" +
                "  \"uer\": \"user\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/chat/completions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJsonPayload))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkWhenPersistConversationIsTrue() throws Exception {
        ChatCompletionRequest request = createChatCompletionRequest();
        ChatCompletionResult result = new ChatCompletionResult();
        String conversationId = "persistedId";

        when(chatCompletionsService.chatWithPersistence(any(), any()))
                .thenReturn(Pair.of(result, conversationId));

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/chat/completions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Persist-Conversation", true)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(header().string("Conversation-Id", conversationId));

        verify(chatCompletionsService, times(1))
                .chatWithPersistence(any(), any());
    }

    @Test
    void shouldReturnOkWhenConversationIdIsProvided() throws Exception {
        ChatCompletionRequest request = createChatCompletionRequest();
        ChatCompletionResult result = new ChatCompletionResult();
        String conversationId = "existingId";

        when(chatCompletionsService.chatWithPersistence(any(), any()))
                .thenReturn(Pair.of(result, conversationId));

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/chat/completions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Conversation-Id", conversationId)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(header().string("Conversation-Id", conversationId));

        verify(chatCompletionsService, times(1))
                .chatWithPersistence(any(), eq(conversationId));
    }

    @Test
    void shouldThrowUnsupportedOperationExceptionWhenNoHeadersProvided() {
        ChatCompletionRequest request = createChatCompletionRequest();

        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/v1/chat/completions")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(request)));
        } catch (Exception e) {
            assertEquals("Request processing failed: java.lang.UnsupportedOperationException: " +
                            "Chat without persistence is not implemented yet",
                    e.getMessage());
        }
    }

}