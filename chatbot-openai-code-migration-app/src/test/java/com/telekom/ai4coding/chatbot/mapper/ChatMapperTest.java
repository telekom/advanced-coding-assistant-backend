package com.telekom.ai4coding.chatbot.mapper;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import dev.ai4j.openai4j.chat.*;
import dev.ai4j.openai4j.shared.Usage;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChatMapperTest {
    @Test
    public void testRequestMapping() {
        ChatMessage chatMessage = new ChatMessage("user", "Tell me joke!");
        List<ChatMessage> messages = Collections.singletonList(chatMessage);

        ChatCompletionRequest theokanningRequest = ChatCompletionRequest.builder()
                .model("gpt-3.5")
                .temperature(1.0)
                .topP(1.0)
                .n(1)
                .stream(false)
                .stop(Collections.singletonList("\n"))
                .maxTokens(50)
                .presencePenalty(0.0)
                .frequencyPenalty(0.0)
                .logitBias(Collections.emptyMap())
                .user("user1")
                .messages(messages)
                .build();

        dev.ai4j.openai4j.chat.ChatCompletionRequest translatedRequest = ChatRequestMapper.mapRequest(theokanningRequest);

        assertEquals("user1", translatedRequest.user());
        assertEquals("gpt-3.5", translatedRequest.model());
        assertEquals(Role.USER, translatedRequest.messages().get(0).role());
        assertEquals(50, translatedRequest.maxTokens());
        assertTrue(translatedRequest.messages().get(0) instanceof UserMessage);
    }

    @Test
    public void testResponseMapping() {
        AssistantMessage message = AssistantMessage.builder()
                .name("assistant")
                .content("joke")
                .build();

        dev.ai4j.openai4j.chat.ChatCompletionChoice choice = ChatCompletionChoice.builder()
                .index(1)
                .message(message)
                .build();

        dev.ai4j.openai4j.shared.Usage usage = Usage.builder()
                .totalTokens(10)
                .promptTokens(8)
                .completionTokens(2)
                .build();

        ChatCompletionResponse openAIResponse = ChatCompletionResponse.builder()
                .id("id1")
                .created(1)
                .model("gpt-3.5")
                .choices(Collections.singletonList(choice))
                .usage(usage)
                .systemFingerprint("dummyfingerpring")
                .build();

        ChatCompletionResult chatCompletionResult = ChatResponseMapper.mapResponseToResult(openAIResponse);

        assertEquals("gpt-3.5", chatCompletionResult.getModel());
        assertEquals("joke", chatCompletionResult.getChoices().get(0).getMessage().getContent());
        assertEquals(10, chatCompletionResult.getUsage().getTotalTokens());
    }

}