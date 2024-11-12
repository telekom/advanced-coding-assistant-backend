package com.telekom.ai4coding.chatbot.service;

import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.telekom.ai4coding.chatbot.BaseIntegrationTest;
import com.telekom.ai4coding.chatbot.configuration.agent.CodeContextVerifyAgent;
import com.telekom.ai4coding.chatbot.configuration.agent.HypotheticalDocumentGenerator;
import com.telekom.ai4coding.chatbot.graph.KnowledgeGraph;
import com.telekom.ai4coding.chatbot.graph.KnowledgeGraphBatchInsertService;
import com.telekom.ai4coding.chatbot.graph.KnowledgeGraphBuilder;
import com.telekom.ai4coding.chatbot.graph.KnowledgeGraphSetup;
import com.telekom.ai4coding.chatbot.repository.FileNodeRepository;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;


public class EmbeddingSearchServiceTest extends BaseIntegrationTest {

    @TempDir
    private File tempDir;

    @MockBean
    private HypotheticalDocumentGenerator hypotheticalDocumentGenerator;

    @MockBean
    private CodeContextVerifyAgent codeContextVerifyAgent;

    @MockBean
    private EmbeddingModel embeddingModel;

    @Autowired
    private EmbeddingSearchService embeddingSearchService;

    @Autowired
    KnowledgeGraphBatchInsertService knowledgeGraphBatchInsertService;

    @Autowired
    FileNodeRepository fileNodeRepository;

    @Autowired
    KnowledgeGraphBuilder knowledgeGraphBuilder;

    @BeforeEach
    void beforeEach() throws IOException {
        when(embeddingModel.dimension()).thenReturn(2);
        Embedding mockEmbedding = Embedding.from(new float[]{0.5f, 0.5f});
        Response<Embedding> mockResponse = Response.from(mockEmbedding);
        when(embeddingModel.embed(anyString())).thenReturn(mockResponse);
        KnowledgeGraph g = KnowledgeGraphSetup.setup(knowledgeGraphBuilder, tempDir);

        knowledgeGraphBatchInsertService.batchInsertFileStructure(g.getRootFileNode(), embeddingModel.dimension());
    }

    @AfterEach
    void clean() {
        fileNodeRepository.deleteCompleteCodeGraph();
    }

    @Test
    void getContextUsingEmbedding_RelevantCodeContext() {
        String query = "hello world";

        when(hypotheticalDocumentGenerator.getFakeCodeSnippet(query)).thenReturn("Fake code");
        when(hypotheticalDocumentGenerator.getFakeCodeDocumentation(query)).thenReturn("Fake doc");
        when(codeContextVerifyAgent.isRelevant(eq(query), any())).thenReturn(true);

        String context = embeddingSearchService.getContextUsingEmbedding(query);

        verify(hypotheticalDocumentGenerator, times(1)).getFakeCodeSnippet(query);
        verify(hypotheticalDocumentGenerator, times(1)).getFakeCodeDocumentation(query);
        assertTrue(context.length() != 0);
        assertTrue(context.contains("Related ASTNode"));
    }

    @Test
    void getContextUsingEmbedding_IrrelevantCodeContext() {
        String query = "hello world";

        when(hypotheticalDocumentGenerator.getFakeCodeSnippet(query)).thenReturn("Fake code");
        when(hypotheticalDocumentGenerator.getFakeCodeDocumentation(query)).thenReturn("Fake doc");
        when(codeContextVerifyAgent.isRelevant(eq(query), any())).thenReturn(false);

        String context = embeddingSearchService.getContextUsingEmbedding(query);

        verify(hypotheticalDocumentGenerator, times(1)).getFakeCodeSnippet(query);
        verify(hypotheticalDocumentGenerator, times(1)).getFakeCodeDocumentation(query);
        assertTrue(context.length() != 0);
        assertFalse(context.contains("Related ASTNode"));
    }
  
}
