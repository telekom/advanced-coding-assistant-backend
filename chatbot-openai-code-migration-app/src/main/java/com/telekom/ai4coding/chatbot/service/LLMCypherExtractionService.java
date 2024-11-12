package com.telekom.ai4coding.chatbot.service;

import dev.langchain4j.model.chat.ChatLanguageModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.telekom.ai4coding.chatbot.template.prompt.StateMachineRetrievalPromptTemplates.CREATE_CYPHER_TEMPLATE;
import static com.telekom.ai4coding.chatbot.template.prompt.StateMachineRetrievalPromptTemplates.CYPHER_PREVIOUS_ATTEMPT;
import static com.telekom.ai4coding.chatbot.template.prompt.StateMachineRetrievalPromptTemplates.EXCEPTION_OCCURRED_BY_LLM_MESSAGE;

/**
 * Service responsible for generating and executing Cypher queries using a Language Learning Model (LLM).
 * This service interacts with a Neo4j database to retrieve information based on user queries.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class LLMCypherExtractionService {

    private static final int MAX_RETRY_ATTEMPTS = 3;

    private final ChatLanguageModel chatLanguageModel;
    private final SearchDatabaseService searchDatabaseService;

    /**
     * Searches for information using LLM-generated Cypher queries based on the provided parameters.
     *
     * @param allRelativePaths A string containing all relative paths, comma-separated, from {@link com.telekom.ai4coding.chatbot.graph.FileNode}.
     * @param allASTTypes A string containing all AST types, comma-separated, from {@link com.telekom.ai4coding.chatbot.graph.ASTNode}.
     * @param userQueryText The user's query text.
     * @return A string containing the shuffled search results from multiple query attempts.
     */
    public String searchByLLMCypher(String allRelativePaths, String allASTTypes, String userQueryText) {
        List<String> searchResults = new ArrayList<>();
        String previousCypherQuery = "";
        String errorMessage = "";

        for (int attempt = 0; attempt < MAX_RETRY_ATTEMPTS; attempt++) {
            String cypherQuery = generateCypherQuery(allRelativePaths, allASTTypes, userQueryText,
                    previousCypherQuery, errorMessage);
            Pair<String, RuntimeException> queryResult = searchDatabaseService.executeCypherQuery(cypherQuery);
            errorMessage = ""; // Reset error message for next iteration

            if (Strings.isNotBlank(queryResult.getLeft())) {
                searchResults.add(queryResult.getLeft());
            } else if (queryResult.getRight() != null) {
                errorMessage = queryResult.getRight().toString();
            }

            previousCypherQuery = cypherQuery;
        }

        return shuffleAndJoinResults(searchResults);
    }

    private String generateCypherQuery(String allRelativePaths, String allASTTypes, String userQueryText,
                                       String previousCypherQuery, String errorMessage) {
        StringBuilder promptBuilder = new StringBuilder(String.format(CREATE_CYPHER_TEMPLATE,
                allRelativePaths, allASTTypes, userQueryText));

        if (Strings.isNotBlank(errorMessage)) {
            promptBuilder.append("\n").append(String.format(EXCEPTION_OCCURRED_BY_LLM_MESSAGE, errorMessage));
        }

        if (Strings.isNotBlank(previousCypherQuery)) {
            promptBuilder.append("\n").append(String.format(CYPHER_PREVIOUS_ATTEMPT, previousCypherQuery));
        }

        return chatLanguageModel.generate(promptBuilder.toString());
    }

    private String shuffleAndJoinResults(List<String> results) {
        Collections.shuffle(results);
        return String.join("\n-----", results);
    }
}