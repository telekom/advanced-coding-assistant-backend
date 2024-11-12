package com.telekom.ai4coding.chatbot.repository.conversation;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Node
public class ConversationNode {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private final String id;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private final String title;

    @Relationship(type = "HAS_MESSAGE", direction = Relationship.Direction.OUTGOING)
    private final List<MessageNode> childMessageNodes; 

    private ConversationNode(String id, String title, LocalDateTime createdAt, LocalDateTime updatedAt, List<MessageNode> childMessageNodes) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.childMessageNodes = childMessageNodes;
    }

    public List<MessageNode> getChildMessageNodes() {
        if(childMessageNodes == null) {
            return null;
        }

        // We have to sort the childMessageNodes becuase Neo4j does not
        // keep the relationship in order. But the order in childMessageNodes
        // is curcial since it is used for tools to dertermine if a tool is
        // executed or not. Also it is important to dertermine the order
        // between user and AI messages.
        return childMessageNodes.stream()
                .sorted((n1, n2) -> Integer.compare(n1.getIndex(), n2.getIndex()))
                .toList();
    }

    public static ConversationNode of(String title) {
        return new ConversationNode(null, title, null, null, null);
    }

    public static ConversationNode of(String title, List<MessageNode> childMessageNodes) {
        return new ConversationNode(null, title, null, null, childMessageNodes);
    }

    public static ConversationNode of(ConversationNode oldNode, List<MessageNode> newChildMessageNodes) {
        return new ConversationNode(
            oldNode.getId(), oldNode.getTitle(), oldNode.getCreatedAt(), oldNode.getUpdatedAt(), newChildMessageNodes);
    }

}
