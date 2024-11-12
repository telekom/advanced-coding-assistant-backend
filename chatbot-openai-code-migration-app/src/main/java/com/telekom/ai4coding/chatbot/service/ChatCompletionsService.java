package com.telekom.ai4coding.chatbot.service;

import com.telekom.ai4coding.chatbot.configuration.agent.GeneralAgent;
import com.telekom.ai4coding.chatbot.configuration.agent.OpenAiAgent;
import com.telekom.ai4coding.chatbot.mapper.MessageMapper;
import com.telekom.ai4coding.chatbot.repository.conversation.MessageNode;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import dev.ai4j.openai4j.chat.AssistantMessage;
import dev.ai4j.openai4j.chat.ChatCompletionChoice;
import dev.ai4j.openai4j.chat.ChatCompletionResponse;
import dev.ai4j.openai4j.shared.Usage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.telekom.ai4coding.chatbot.mapper.ChatResponseMapper.mapResponseToResult;
import static com.telekom.ai4coding.chatbot.utils.TokenCalculation.calculateTokens;

@Service
@RequiredArgsConstructor
public class ChatCompletionsService {

    private final GeneralAgent generalAgent;
    private final OpenAiAgent openAiAgent;
    private final ConversationService conversationService;
    private final EmbeddingSearchService embeddingSearchService;

    public Pair<ChatCompletionResult, String> chatWithPersistence(ChatCompletionRequest createChatCompletionRequest,
                                                         String conversationId){

        ChatCompletionResponse chatCompletionResponse;

        String userMessage = createChatCompletionRequest.getMessages().getLast().getContent();
        String existingConversationId = conversationId ==
                null ? conversationService.createNewConversation(userMessage) : conversationId;

        // Use embedding search to find similar TextNode and ASTNode, and append it to the
        // userMessage before sending it to generalAgent.
        String context = embeddingSearchService.getContextUsingEmbedding(userMessage);
        if(context.length() > 0) {
            userMessage = (userMessage + System.getProperty("line.separator") +
                           "Context found using embedding search:" + System.getProperty("line.separator") +
                           context);
        }
        generalAgent.chat(existingConversationId, userMessage);

        List<MessageNode> responseMessageNodes = conversationService.getMessagesNodesAfterLastUserMessage(
                existingConversationId);
        List<ChatCompletionChoice> choices = new ArrayList<>();
        for (MessageNode responseMessageNode : responseMessageNodes) {
            choices.add(MessageMapper.mapToChatCompletionChoice(responseMessageNode));
        }


        int promptTokens = calculateTokens(userMessage);
        // TODO: Calculate the tool calling cost.
        // Because the last message is only the AI response. The AI might used tokens
        // to call tools.
        int completionTokens = calculateTokens(choices.getLast().message().content());
        int totalTokens = promptTokens + completionTokens;
        chatCompletionResponse = ChatCompletionResponse.builder()
                .id(LocalDate.now()+createChatCompletionRequest.getUser())
                .created((int) System.currentTimeMillis())
                .model(createChatCompletionRequest.getModel())
                .choices(choices)
                .usage(Usage.builder()
                        .promptTokens(promptTokens)
                        .completionTokens(completionTokens)
                        .totalTokens(totalTokens)
                        .build())
                .build();

        return Pair.of(mapResponseToResult(chatCompletionResponse), existingConversationId);
    }



    public ChatCompletionResult chatWithoutPersistence(ChatCompletionRequest createChatCompletionRequest) {
        StringBuilder messages = new StringBuilder();
        createChatCompletionRequest.getMessages().forEach(message -> messages.append(message.getContent()).append("\n"));
        String answer = openAiAgent.chat(messages.toString());
        ChatCompletionChoice choices = ChatCompletionChoice.builder()
                .message(AssistantMessage.from(answer))
                .build();

        int  promptTokens = calculateTokens(messages.toString());
        int  completionTokens = calculateTokens(choices.message().content());
        int totalTokens = promptTokens + completionTokens;
       ChatCompletionResponse response =ChatCompletionResponse.builder()
                .id(LocalDate.now() + createChatCompletionRequest.getUser())
                .created((int) System.currentTimeMillis())
                .model(createChatCompletionRequest.getModel())
                .choices(List.of(choices))
                .usage(Usage.builder()
                        .promptTokens(promptTokens)
                        .completionTokens(completionTokens)
                        .totalTokens(totalTokens)
                        .build())
                .build();
        return mapResponseToResult(response);
    }
}