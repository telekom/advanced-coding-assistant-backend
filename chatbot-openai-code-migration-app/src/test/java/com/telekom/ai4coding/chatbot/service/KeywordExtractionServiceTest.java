package com.telekom.ai4coding.chatbot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.langchain4j.model.chat.ChatLanguageModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KeywordExtractionServiceTest {

    @Mock
    private ChatLanguageModel chatLanguageModel;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private SearchDatabaseService searchDatabaseService;

    @InjectMocks
    private KeywordExtractionService keywordExtractionService;

    @Test
    void testSearchByExtractedKeywords_Success() throws Exception {
        // Prepare
        String allRelativePaths = "path1,path2";
        String allASTTypes = "type1,type2";
        String userQueryText = "test query";
        String llmResponse = "{\"keywords\":[\"key1\",\"key2\"]}";
        Map<String, String[]> keywordsMap = new HashMap<>();
        keywordsMap.put("keywords", new String[]{"key1", "key2"});

        when(chatLanguageModel.generate(anyString())).thenReturn(llmResponse);
        when(objectMapper.readValue(eq(llmResponse), any(TypeReference.class))).thenReturn(keywordsMap);

        Map<String, String> searchResults = new HashMap<>();
        searchResults.put("file1", "content1");
        searchResults.put("file2", "content2");

        when(searchDatabaseService.executeAstAndTextKeywordSearch(any())).thenReturn(searchResults);

        // Do Work
        String result = keywordExtractionService.searchByExtractedKeywords(allRelativePaths, allASTTypes, userQueryText);

        // Test
        assertNotNull(result);
        assertTrue(result.contains("file1: content1"));
        assertTrue(result.contains("file2: content2"));
        verify(chatLanguageModel, times(1)).generate(anyString());
        verify(objectMapper, times(1)).readValue(eq(llmResponse), any(TypeReference.class));
        verify(searchDatabaseService, times(1)).executeAstAndTextKeywordSearch(any());
    }

    @Test
    void testSearchByExtractedKeywords_LLMFailure() throws Exception {
        // Prepare
        String allRelativePaths = "path1,path2";
        String allASTTypes = "type1,type2";
        String userQueryText = "test query";

        when(chatLanguageModel.generate(anyString())).thenThrow(new RuntimeException("LLM failed"));

        // Do work and test
        assertThrows(RuntimeException.class, () ->
                keywordExtractionService.searchByExtractedKeywords(allRelativePaths, allASTTypes, userQueryText)
        );
        verify(chatLanguageModel, times(1)).generate(anyString());
    }

    @Test
    void testSearchByExtractedKeywords_EmptyKeywords() throws Exception {
        // Prepare
        String allRelativePaths = "path1,path2";
        String allASTTypes = "type1,type2";
        String userQueryText = "test query";
        String llmResponse = "{\"keywords\":[]}";
        Map<String, String[]> keywordsMap = new HashMap<>();
        keywordsMap.put("keywords", new String[]{});

        when(chatLanguageModel.generate(anyString())).thenReturn(llmResponse);
        when(objectMapper.readValue(eq(llmResponse), any(TypeReference.class))).thenReturn(keywordsMap);

        // Do work and test
        assertThrows(RuntimeException.class, () ->
                keywordExtractionService.searchByExtractedKeywords(allRelativePaths, allASTTypes, userQueryText)
        );
        verify(chatLanguageModel, times(1)).generate(anyString());
        verify(objectMapper, times(1)).readValue(eq(llmResponse), any(TypeReference.class));
    }

    @Test
    void testSearchByExtractedKeywords_JsonProcessingException() throws Exception {
        // Arrange
        String allRelativePaths = "path1,path2";
        String allASTTypes = "type1,type2";
        String userQueryText = "test query";
        String llmResponse = "{\"keywords\":[\"key1\",\"key2\"]}";

        when(chatLanguageModel.generate(anyString())).thenReturn(llmResponse);
        when(objectMapper.readValue(eq(llmResponse), any(TypeReference.class)))
                .thenThrow(new JsonProcessingException("JSON processing failed") {});

        // Act & Assert
        assertThrows(RuntimeException.class, () ->
                keywordExtractionService.searchByExtractedKeywords(allRelativePaths, allASTTypes, userQueryText)
        );
        verify(chatLanguageModel, times(3)).generate(anyString());
        verify(objectMapper, times(3)).readValue(eq(llmResponse), any(TypeReference.class));
    }

}