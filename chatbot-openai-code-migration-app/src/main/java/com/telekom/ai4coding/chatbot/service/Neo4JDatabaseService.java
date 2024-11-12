package com.telekom.ai4coding.chatbot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.stereotype.Service;

import static com.telekom.ai4coding.chatbot.template.cypher.Neo4JDatabaseCleanupTemplates.DATABASE_CLEANUP_CYPHER;

@Slf4j
@RequiredArgsConstructor
@Service
public class Neo4JDatabaseService {

    private final Driver neo4jDriver;

    /**
     * Executes a delete operation for all nodes and relationships in the Neo4j database.
     *
     */
    public void cleanupDatabase() {
        try (Session session = neo4jDriver.session()) {
            session.executeWrite(tx -> {
               tx.run(DATABASE_CLEANUP_CYPHER);
               return null;
            });
            System.out.println("All nodes and relationships have been deleted.");
        }
    }
}
