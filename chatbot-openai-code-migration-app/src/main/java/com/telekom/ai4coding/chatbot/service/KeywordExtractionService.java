package com.telekom.ai4coding.chatbot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telekom.ai4coding.chatbot.template.prompt.StateMachineRetrievalPromptTemplates;
import dev.langchain4j.model.chat.ChatLanguageModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

import static com.telekom.ai4coding.chatbot.template.prompt.StateMachineRetrievalPromptTemplates.EXCEPTION_OCCURRED_KEYWORDS_MESSAGE;

/**
 * Service responsible for extracting keywords from user queries and searching the database
 * based on these keywords. This service uses a language model to extract keywords and then
 * performs both AST and text-based searches in the database.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class KeywordExtractionService {

    private static final int MAX_RETRY_ATTEMPTS = 3;

    private final ChatLanguageModel chatLanguageModel;
    private final ObjectMapper objectMapper;
    private final SearchDatabaseService searchDatabaseService;

    /**
     * Extracts keywords from the user query and performs a database search based on these keywords.
     *
     * @param allRelativePaths A string containing all relative paths, comma-separated, from {@link com.telekom.ai4coding.chatbot.graph.FileNode}.
     * @param allASTTypes A string containing all AST types, comma-separated, from {@link com.telekom.ai4coding.chatbot.graph.ASTNode}.
     * @param userQueryText The user's query text.
     * @return A string containing the search results, formatted as "filePath: fileContent" pairs separated by semicolons.
     * @throws RuntimeException if keyword extraction fails after maximum retry attempts or if no keywords are extracted.
     */
    public String searchByExtractedKeywords(String allRelativePaths,
                                            String allASTTypes,
                                            String userQueryText) {
        String[] keywords = extractKeywords(allRelativePaths, allASTTypes, userQueryText);
        return searchDatabase(keywords);
    }

    private String[] extractKeywords(String allRelativePaths, String allASTTypes, String userQueryText) {
        Map<String, String[]> keywordsJson = null;
        String lastExceptionMessage = null;

        for (int attempt = 0; attempt < MAX_RETRY_ATTEMPTS; attempt++) {
            try {
                String extractKeywordsQuery = buildExtractKeywordsQuery(allRelativePaths, allASTTypes,
                        userQueryText, lastExceptionMessage);
                String extractedKeywordsString = chatLanguageModel.generate(extractKeywordsQuery);
                keywordsJson = parseKeywordsJson(extractedKeywordsString);
                break;
            } catch (JsonProcessingException e) {
                log.warn("Chat language model failed to extract proper json: {}", e.getMessage());
                lastExceptionMessage = e.getMessage();
            }
        }

        if (keywordsJson == null) {
            throw new RuntimeException("Failed to extract keywordsJson after " + MAX_RETRY_ATTEMPTS + " attempts");
        }

        String[] keywords = keywordsJson.get("keywords");

        if (keywords == null || keywords.length == 0) {
            throw new RuntimeException("LLM failed to extract correct JSON, 'keywords' property is not present");
        }

        return keywords;
    }

    private String buildExtractKeywordsQuery(String allRelativePaths,
                                             String allASTTypes,
                                             String userQueryText,
                                             String lastExceptionMessage) {
        String extractKeywordsQuery = String.format(
                StateMachineRetrievalPromptTemplates.EXTRACT_KEYWORDS_TEMPLATE,
                allRelativePaths,
                allASTTypes,
                userQueryText
        );

        if (lastExceptionMessage != null) {
            String exceptionInfo = String.format(EXCEPTION_OCCURRED_KEYWORDS_MESSAGE, lastExceptionMessage);
            extractKeywordsQuery = exceptionInfo + extractKeywordsQuery;
        }

        return extractKeywordsQuery;
    }

    private Map<String, String[]> parseKeywordsJson(String extractedKeywordsString) throws JsonProcessingException {
        return objectMapper.readValue(extractedKeywordsString, new TypeReference<Map<String, String[]>>() {});
    }

    private String searchDatabase(String[] keywords) {
        return searchDatabaseService.executeAstAndTextKeywordSearch(keywords)
                .entrySet()
                .stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining("; "));
    }
}