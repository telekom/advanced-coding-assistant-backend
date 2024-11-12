package com.telekom.ai4coding.chatbot.service;

import dev.langchain4j.model.chat.ChatLanguageModel;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LLMCypherExtractionServiceTest {

    @Mock
    private ChatLanguageModel chatLanguageModel;

    @Mock
    private SearchDatabaseService searchDatabaseService;

    @InjectMocks
    private LLMCypherExtractionService llmCypherExtractionService;

    @Test
    void testSearchByLLMCypher_Success() {
        // Prepare
        String allRelativePaths = "path1,path2";
        String allASTTypes = "type1,type2";
        String userQueryText = "test query";
        String cypherQuery = "MATCH (n) RETURN n";
        String queryResult = "Result 1";

        when(chatLanguageModel.generate(anyString())).thenReturn(cypherQuery);
        when(searchDatabaseService.executeCypherQuery(cypherQuery)).thenReturn(Pair.of(queryResult, null));

        // Do Work
        String result = llmCypherExtractionService.searchByLLMCypher(allRelativePaths, allASTTypes, userQueryText);

        // Test
        assertNotNull(result);
        assertTrue(result.contains(queryResult));
        verify(chatLanguageModel, times(3)).generate(anyString());
        verify(searchDatabaseService, times(3)).executeCypherQuery(cypherQuery);
    }

    @Test
    void testSearchByLLMCypher_MultipleResults() {
        // Prepare
        String allRelativePaths = "path1,path2";
        String allASTTypes = "type1,type2";
        String userQueryText = "test query";
        String cypherQuery = "MATCH (n) RETURN n";
        String queryResult1 = "Result 1";
        String queryResult2 = "Result 2";
        String queryResult3 = "Result 3";

        when(chatLanguageModel.generate(anyString())).thenReturn(cypherQuery);
        when(searchDatabaseService.executeCypherQuery(cypherQuery))
                .thenReturn(Pair.of(queryResult1, null))
                .thenReturn(Pair.of(queryResult2, null))
                .thenReturn(Pair.of(queryResult3, null));

        // Do Work
        String result = llmCypherExtractionService.searchByLLMCypher(allRelativePaths, allASTTypes, userQueryText);

        // Test
        assertNotNull(result);
        assertTrue(result.contains(queryResult1));
        assertTrue(result.contains(queryResult2));
        assertTrue(result.contains(queryResult3));
        assertTrue(result.contains("-----"));
        verify(chatLanguageModel, times(3)).generate(anyString());
        verify(searchDatabaseService, times(3)).executeCypherQuery(cypherQuery);
    }

    @Test
    void testSearchByLLMCypher_EmptyResults() {
        // Prepare
        String allRelativePaths = "path1,path2";
        String allASTTypes = "type1,type2";
        String userQueryText = "test query";
        String cypherQuery = "MATCH (n) RETURN n";

        when(chatLanguageModel.generate(anyString())).thenReturn(cypherQuery);
        when(searchDatabaseService.executeCypherQuery(cypherQuery)).thenReturn(Pair.of("", null));

        // Do Work
        String result = llmCypherExtractionService.searchByLLMCypher(allRelativePaths, allASTTypes, userQueryText);

        // Test
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(chatLanguageModel, times(3)).generate(anyString());
        verify(searchDatabaseService, times(3)).executeCypherQuery(cypherQuery);
    }

    @Test
    void testSearchByLLMCypher_LLMFailure() {
        // Prepare
        String allRelativePaths = "path1,path2";
        String allASTTypes = "type1,type2";
        String userQueryText = "test query";

        when(chatLanguageModel.generate(anyString())).thenThrow(new RuntimeException("LLM failed"));

        // Do work and test
        assertThrows(RuntimeException.class, () ->
                llmCypherExtractionService.searchByLLMCypher(allRelativePaths, allASTTypes, userQueryText)
        );
        verify(chatLanguageModel, times(1)).generate(anyString());
        verify(searchDatabaseService, never()).executeCypherQuery(anyString());
    }

    @Test
    void testSearchByLLMCypher_QueryExecutionError() {
        // Prepare
        String allRelativePaths = "path1,path2";
        String allASTTypes = "type1,type2";
        String userQueryText = "test query";
        String cypherQuery = "MATCH (n) RETURN n";
        RuntimeException queryExecutionException = new RuntimeException("Query execution failed");

        when(chatLanguageModel.generate(anyString())).thenReturn(cypherQuery);
        when(searchDatabaseService.executeCypherQuery(cypherQuery)).thenReturn(Pair.of("", queryExecutionException));

        // Do Work
        String result = llmCypherExtractionService.searchByLLMCypher(allRelativePaths, allASTTypes, userQueryText);

        // Test
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(chatLanguageModel, times(3)).generate(anyString());
        verify(searchDatabaseService, times(3)).executeCypherQuery(cypherQuery);
    }

    @Test
    void testSearchByLLMCypher_MixedResults() {
        // Prepare
        String allRelativePaths = "path1,path2";
        String allASTTypes = "type1,type2";
        String userQueryText = "test query";
        String cypherQuery = "MATCH (n) RETURN n";
        String queryResult1 = "Result 1";
        RuntimeException queryExecutionException = new RuntimeException("Query execution failed");
        String queryResult3 = "Result 3";

        when(chatLanguageModel.generate(anyString())).thenReturn(cypherQuery);
        when(searchDatabaseService.executeCypherQuery(cypherQuery))
                .thenReturn(Pair.of(queryResult1, null))
                .thenReturn(Pair.of("", queryExecutionException))
                .thenReturn(Pair.of(queryResult3, null));

        // Do Work
        String result = llmCypherExtractionService.searchByLLMCypher(allRelativePaths, allASTTypes, userQueryText);

        // Test
        assertNotNull(result);
        assertTrue(result.contains(queryResult1));
        assertFalse(result.contains(queryExecutionException.getMessage()));
        assertTrue(result.contains(queryResult3));
        assertTrue(result.contains("-----"));
        verify(chatLanguageModel, times(3)).generate(anyString());
        verify(searchDatabaseService, times(3)).executeCypherQuery(cypherQuery);
    }
}