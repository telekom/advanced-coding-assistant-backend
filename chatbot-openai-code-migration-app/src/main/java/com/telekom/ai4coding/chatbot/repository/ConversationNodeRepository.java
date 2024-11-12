package com.telekom.ai4coding.chatbot.repository;

import com.telekom.ai4coding.chatbot.repository.conversation.ConversationNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface ConversationNodeRepository extends Neo4jRepository<ConversationNode, String> {

    @Query("MATCH (c:ConversationNode {id: $id}) " +
            "OPTIONAL MATCH (c)-[r:HAS_MESSAGE]->(m:MessageNode) " +
            "DETACH DELETE c, m")
    void deleteConversationAndMessagesById(@Param("id") String id);

    @Query("MATCH (c:ConversationNode {id: $id}) " +
            "OPTIONAL MATCH (m:MessageNode)-[r:BELONGS_TO]->(c) " +
            "OPTIONAL MATCH (f:FileNode)-[:RELATED_TO]->(c) " +
            "OPTIONAL MATCH (f)-[:HAS_AST|TEXT_NODE*]->(child) " +
            "OPTIONAL MATCH (child)-[:NEXT_CHUNK|PARENT_OF*]->(related) " +
            "DETACH DELETE m, c, f, child, related")
    void deleteAllByConversationId(@Param("id") String id);

    @Query("MATCH (c:ConversationNode {id: $id}) SET c.title = $title RETURN c")
    ConversationNode updateTitleByConversationId(@Param("id") String id, @Param("title") String title);

}
