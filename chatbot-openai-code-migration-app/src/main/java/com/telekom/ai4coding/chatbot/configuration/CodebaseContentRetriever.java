package com.telekom.ai4coding.chatbot.configuration;

import com.telekom.ai4coding.chatbot.repository.ASTNodeRepository;
import com.telekom.ai4coding.chatbot.repository.FileNodeRepository;
import com.telekom.ai4coding.chatbot.service.KeywordExtractionService;
import com.telekom.ai4coding.chatbot.service.LLMCypherExtractionService;
import com.telekom.ai4coding.chatbot.utils.FillingVesselAlgorithm;
import dev.langchain4j.rag.content.Content;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.query.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * CodebaseContentRetriever is a component of the Advanced Coding Assistant app,
 * responsible for retrieving relevant content from the codebase based on user queries.
 *
 * This component provides the ability to use a State Machine Approach for content
 * retrieval and is suitable for LLMs with lower reasoning capabilities and/or
 * if the user wishes to have more control over the amount of tokens that are spent on
 * data retrieval (see {@link FillingVesselAlgorithm}).
 *
 * Content retrieval:
 *
 * 1. Keyword-based search: Uses {@link KeywordExtractionService} to extract keywords from the
 *    user query and search for relevant content in the codebase.
 *
 * 2. LLM-powered Cypher query generation: Employs {@link LLMCypherExtractionService} to generate
 *    Cypher queries based on the user's input, enabling more context-aware searches.
 *
 * 3. Composite result generation: Combines results from both keyword-based and LLM-powered
 *    searches using the {@link FillingVesselAlgorithm}.
 *
 * The class interacts with {@link FileNodeRepository} and {@link ASTNodeRepository} to gather information
 * about the structure of the codebase, including file paths and AST node types. This
 * information is used to enhance the search context and improve result relevance.
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class CodebaseContentRetriever implements ContentRetriever {

    private final KeywordExtractionService keywordExtractionService;
    private final LLMCypherExtractionService llmCypherExtractionService;
    private final FileNodeRepository fileNodeRepository;
    private final ASTNodeRepository astNodeRepository;
    private final FillingVesselAlgorithm fillingVesselAlgorithm;

    @Override
    public List<Content> retrieve(Query query) {
        String userQueryText = query.text();

        // Collection of results from direct queries to the DB (FileNodes, ASTNodes and TextNodes) and results from
        // queries that the LLM generated
        List<Content> result = new LinkedList<>();

        String allRelativePaths = String.join(", ", fileNodeRepository.getAllRelativePaths());
        String allASTTypes = String.join(", ", astNodeRepository.getAllDistinctTypes());

        String searchResultsByKeywordsAsText =
                keywordExtractionService.searchByExtractedKeywords(allRelativePaths, allASTTypes, userQueryText);

        String fullShuffledSearchByLLM =
                llmCypherExtractionService.searchByLLMCypher(allRelativePaths, allASTTypes, userQueryText);

        String weightedStringResult =
                fillingVesselAlgorithm.fillVessel(fullShuffledSearchByLLM, searchResultsByKeywordsAsText);

        if (Strings.isNotBlank(weightedStringResult)) {
            result.add(Content.from(weightedStringResult));
        }

        return result;
    }
}
