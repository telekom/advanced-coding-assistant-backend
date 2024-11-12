package com.telekom.ai4coding.chatbot.template.cypher;

public class Neo4JDatabaseCleanupTemplates {
    private Neo4JDatabaseCleanupTemplates() {
    }

    public static final String DATABASE_CLEANUP_CYPHER =
            "MATCH (n)" +
            "DETACH DELETE n";
}
