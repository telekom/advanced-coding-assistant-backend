package com.telekom.ai4coding.chatbot.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.telekom.ai4coding.chatbot.graph.FileNode;

import java.util.List;
import java.util.Optional;

public interface FileNodeRepository extends Neo4jRepository<FileNode, Long> {

    Optional<FileNode> findByRelativePath(String relativePath);

    @Query("MATCH (n) WHERE n:ASTNode OR n:FileNode or n:TextNode DETACH DELETE n")
    void deleteCompleteCodeGraph();

    @Query("MATCH (f:FileNode)-[:RELATED_TO]->(c:ConversationNode) WHERE c.id = $conversationNodeId RETURN f")
    Optional<List<FileNode>> getFileNodeByConversationNodeId(@Param("conversationNodeId") String conversationNodeId);

    @Query("MATCH (f:FileNode) WHERE id(f) = $id " + "OPTIONAL MATCH (f)-[:HAS_AST|TEXT_NODE*]->(child) " + "OPTIONAL MATCH (child)-[:NEXT_CHUNK|PARENT_OF*]->(related) " + "DETACH DELETE f, child, related")
    void deleteFileNodeAndItsChildrenById(@Param("id") Long id);

    @Query("MATCH (n:FileNode) RETURN n.relativePath")
    List<String> getAllRelativePaths();
}