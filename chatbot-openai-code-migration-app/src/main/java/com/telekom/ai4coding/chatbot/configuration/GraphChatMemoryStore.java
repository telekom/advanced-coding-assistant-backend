package com.telekom.ai4coding.chatbot.configuration;

import com.telekom.ai4coding.chatbot.mapper.MessageMapper;
import com.telekom.ai4coding.chatbot.repository.ConversationNodeRepository;
import com.telekom.ai4coding.chatbot.repository.conversation.ConversationNode;
import com.telekom.ai4coding.chatbot.repository.conversation.MessageNode;
import com.telekom.ai4coding.chatbot.service.ConversationService;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GraphChatMemoryStore implements ChatMemoryStore {

    private final ConversationNodeRepository conversationNodeRepository;
    private final ConversationService conversationService;

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        if (memoryId instanceof String conversationId) {

            ConversationNode conversationNode = conversationNodeRepository.findById(conversationId)
                    .orElseThrow(() ->
                            new IllegalArgumentException("Invalid conversation id: " + conversationId + "."));

            List<MessageNode> allMessagesByConversationIdOrderedByNewest = conversationNode.getChildMessageNodes();
            List<ChatMessage> list = allMessagesByConversationIdOrderedByNewest
                    .stream()
                    .map(MessageMapper::mapToChatMessage)
                    .toList();

            return list;
        } else {
            throw new IllegalArgumentException("Invalid memory id: " + memoryId + ". memory id must be a Long.");
        }
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> messages) {
        if (memoryId instanceof String conversationId) {
            ConversationNode conversationNode = conversationNodeRepository.findById(conversationId)
                    .orElseThrow(() ->
                            new IllegalArgumentException("Invalid conversation id: " + conversationId + "."));
            conversationNodeRepository.deleteConversationAndMessagesById(conversationId);

            List<MessageNode> newChildMessageNodes = new LinkedList<>();
            MessageNode previous = null;
            MessageNode current;
            for(ChatMessage message : messages){
                current = MessageNode.of(message, previous);
                newChildMessageNodes.add(current);
                previous = current;
            }
            ConversationNode updatedConversationNode = ConversationNode.of(conversationNode, newChildMessageNodes);

            conversationNodeRepository.save(updatedConversationNode);
        } else {
            throw new IllegalArgumentException("Invalid memory id: " + memoryId + ". Memory id must be a Long.");
        }
    }

    @Override
    @Transactional
    public void deleteMessages(Object memoryId) {
        if (memoryId instanceof String conversationId) {
            conversationService.deleteConversation(conversationId);
        } else {
            throw new IllegalArgumentException("Invalid memory id: " + memoryId + ". Memory id must be a Long.");
        }
    }

}
