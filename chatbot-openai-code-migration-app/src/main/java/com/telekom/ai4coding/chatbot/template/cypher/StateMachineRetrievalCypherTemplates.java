package com.telekom.ai4coding.chatbot.template.cypher;

public class StateMachineRetrievalCypherTemplates {
    private StateMachineRetrievalCypherTemplates() {
    }

    public static final String AST_KEYWORD_SEARCH_CYPHER =
            "UNWIND $keywords AS keyword " +
                    "MATCH (file:FileNode)-[:HAS_AST*]->(ast:ASTNode) " +
                    "WHERE ast.type IN ['program'] " +
                    "  AND toLower(ast.text) CONTAINS toLower(keyword) " +
                    "RETURN keyword, file.relativePath as FilePath, ast.text as FileContent";

    public static final String TEXT_KEYWORD_SEARCH_CYPHER =
            "UNWIND $keywords AS keyword " +
                    "MATCH (file:FileNode)-[:HAS_TEXT]->(textNode:TextNode) " +
                    "WHERE toLower(textNode.text) CONTAINS toLower(keyword) " +
                    "RETURN " +
                    "file.relativePath as FilePath, textNode.text as FileContent";
}
