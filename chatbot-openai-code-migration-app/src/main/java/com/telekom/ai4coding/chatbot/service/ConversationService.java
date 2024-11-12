package com.telekom.ai4coding.chatbot.service;

import com.telekom.ai4coding.chatbot.repository.ConversationNodeRepository;
import com.telekom.ai4coding.chatbot.repository.conversation.ConversationNode;
import com.telekom.ai4coding.chatbot.repository.conversation.MessageNode;
import dev.langchain4j.data.message.ChatMessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.telekom.ai4coding.chatbot.utils.TitleGenerator.generateTitle;

@Service
@RequiredArgsConstructor
public class ConversationService {

    private final ConversationNodeRepository conversationNodeRepository;

    public String createNewConversation(String prompt) {
        ConversationNode newConversationNode = ConversationNode.of(generateTitle(prompt));
        ConversationNode savedConversationNode = conversationNodeRepository.save(newConversationNode);
        return savedConversationNode.getId();
    }

    public Map<String, String> getConversation(String conversationId) {
        ConversationNode conversationNode = conversationNodeRepository.findById(conversationId)
                .orElseThrow(() -> new IllegalStateException("Conversation with id " + conversationId + " not found"));

        return createConversationDto(conversationNode);
    }

    public List<Map<String, String>> getAllConversations() {
        List<ConversationNode> conversationNodes = conversationNodeRepository.findAll();

        return conversationNodes
                .stream()
                .sorted(Comparator.comparing(ConversationNode::getUpdatedAt))
                .map(this::createConversationDto)
                .toList().reversed();
    }

    private Map<String, String> createConversationDto(ConversationNode conversationNode) {
        return Map.of("id", conversationNode.getId(),
                "title", conversationNode.getTitle());
    }

    public List<Map<String, String>> getConversationMessages(String conversationId) {
        ConversationNode conversationNode = conversationNodeRepository.findById(conversationId)
            .orElseThrow(() ->
                    new IllegalArgumentException("Invalid conversation id: " + conversationId + "."));

        return conversationNode.getChildMessageNodes().stream()
                .map(messageNode -> createMessageDto(conversationId, messageNode))
                .toList();
    }

    public List<MessageNode> getMessagesNodesAfterLastUserMessage(String conversationId) {
        ConversationNode conversationNode = conversationNodeRepository.findById(conversationId)
            .orElseThrow(() ->
                    new IllegalArgumentException("Invalid conversation id: " + conversationId + "."));

        List<MessageNode> childMessageNodes = conversationNode.getChildMessageNodes();
        int lastUserMessageIndex = -1;
        for(int i = childMessageNodes.size()-1; i >= 0; i--) {
            if(childMessageNodes.get(i).getChatMessageType().equals(ChatMessageType.USER)) {
                lastUserMessageIndex = i;
                break;
            }
        }

        if(lastUserMessageIndex == -1) {
            return List.of();
        }

        return childMessageNodes.subList(lastUserMessageIndex+1, childMessageNodes.size());
    }


    public void deleteConversation(String conversationId) {
        conversationNodeRepository.deleteAllByConversationId(conversationId);
    }

    private Map<String, String> createMessageDto(String conversationId, MessageNode messageNode) {
        return switch(messageNode.getChatMessageType()) {
            case ChatMessageType.SYSTEM -> createSystemMessageDto(conversationId, messageNode);
            case ChatMessageType.USER -> createUserMessageDto(conversationId, messageNode);
            case ChatMessageType.AI -> createAiMessageDto(conversationId, messageNode);
            case ChatMessageType.TOOL_EXECUTION_RESULT -> createToolExecutionResultDto(conversationId, messageNode);
            default -> null;
        };
    }

    private Map<String, String> createSystemMessageDto(String conversationId, MessageNode messageNode) {
        return Map.of(
                "id", messageNode.getId(),
                "conversationId", conversationId,
                "index", String.valueOf(messageNode.getIndex()),
                "chatMessageType", messageNode.getChatMessageType().toString(),
                "text", messageNode.getText()
        );
    }

    private Map<String, String> createUserMessageDto(String conversationId, MessageNode messageNode) {
        String text = messageNode.getText();

        //TODO this is a quickfix and shouldn't be done at home (https://gitlab.devops.telekom.de/ai4developers/ai4c/ai4coding-chatbot/-/issues/4)
        // To properly fix this issue, follow this task: https://gitlab.devops.telekom.de/ai4developers/ai4c/ai4coding-chatbot/-/issues/21
        if (text.contains("Answer using the following information:")) {
            text = text.split("Answer using the following information:")[0];
        }
        if (text.contains("Context found using embedding search:")) {
            text = text.split("Context found using embedding search:")[0];
        }

        return Map.of(
                "id", messageNode.getId(),
                "conversationId", conversationId,
                "index", String.valueOf(messageNode.getIndex()),
                "chatMessageType", messageNode.getChatMessageType().toString(),
                "text", text
        );
    }

    private Map<String, String> createAiMessageDto(String conversationId, MessageNode messageNode) {
        if(messageNode.hasToolExecutionRequests()){
            String toolExecutionRequestsStr = messageNode.getToolExecutionRequests().stream()
                                                            .map(toolExecutionRequest -> (
                                                                toolExecutionRequest.id() + ";" +
                                                                toolExecutionRequest.name() + ";"+
                                                                toolExecutionRequest.arguments()))
                                                            .collect(Collectors.joining(":"));
            return Map.of(
                "id", messageNode.getId(),
                "conversationId", conversationId,
                "index", String.valueOf(messageNode.getIndex()),
                "chatMessageType", messageNode.getChatMessageType().toString(),
                "toolExecutionRequests", toolExecutionRequestsStr
            );
        }else{
            return Map.of(
                "id", messageNode.getId(),
                "conversationId", conversationId,
                "index", String.valueOf(messageNode.getIndex()),
                "chatMessageType", messageNode.getChatMessageType().toString(),
                "text", messageNode.getText()
            );
        }
    }

    private Map<String, String> createToolExecutionResultDto(String conversationId, MessageNode messageNode) {
        return Map.of(
            "id", messageNode.getId(),
            "conversationId", conversationId,
            "index", String.valueOf(messageNode.getIndex()),
            "chatMessageType", messageNode.getChatMessageType().toString(),
            "toolId", messageNode.getToolExecutionResult().id(),
            "toolName", messageNode.getToolExecutionResult().toolName(),
            "toolText", messageNode.getToolExecutionResult().text()
        );
    }

    public String renameConversation(String conversationId, String newTitle) {
        ConversationNode conversationNode = conversationNodeRepository.updateTitleByConversationId(conversationId, newTitle);
        if (conversationNode == null) {
            throw new IllegalArgumentException("Conversation with id " + conversationId + " not found.");
        }
        return conversationNode.getTitle();
    }
}
