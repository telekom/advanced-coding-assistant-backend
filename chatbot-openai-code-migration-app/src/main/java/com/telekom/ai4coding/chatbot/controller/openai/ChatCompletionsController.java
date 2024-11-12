package com.telekom.ai4coding.chatbot.controller.openai;

import com.telekom.ai4coding.chatbot.service.ChatCompletionsService;
import com.telekom.ai4coding.openai.completions.ChatApi;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(exposedHeaders = "Conversation-Id")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class ChatCompletionsController implements ChatApi {

    private final ChatCompletionsService chatCompletionsService;

    @Override
    public ResponseEntity<ChatCompletionResult> createChatCompletion(
            @Valid @RequestBody ChatCompletionRequest createChatCompletionRequest,
            @RequestHeader(value = "Persist-Conversation", required = false) Boolean persistConversation,
            @RequestHeader(value = "Conversation-Id", required = false) String conversationId) {

        ChatCompletionResult result;
        ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.ok();

        if ((persistConversation != null && persistConversation) || conversationId != null) {
            Pair<ChatCompletionResult, String> chatCompletionResultLongPair = chatCompletionsService
                    .chatWithPersistence(createChatCompletionRequest, conversationId);
            result = chatCompletionResultLongPair.getFirst();

            String persistedConversationId = chatCompletionResultLongPair.getSecond();

            responseBuilder.header("Conversation-Id", persistedConversationId);
        } else {
            return ResponseEntity.ok(chatCompletionsService.chatWithoutPersistence(createChatCompletionRequest));
        }

        return responseBuilder.body(result);
    }
}