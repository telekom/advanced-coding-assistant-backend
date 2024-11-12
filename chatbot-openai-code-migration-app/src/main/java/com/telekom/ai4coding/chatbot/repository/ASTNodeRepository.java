package com.telekom.ai4coding.chatbot.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.telekom.ai4coding.chatbot.graph.ASTNode;

import java.util.List;

public interface ASTNodeRepository extends Neo4jRepository<ASTNode, Long> {

    List<ASTNode> findByText(String text);

    @Query("MATCH (n:ASTNode) RETURN DISTINCT n.type")
    List<String> getAllDistinctTypes();
}
