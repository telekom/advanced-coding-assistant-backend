package com.telekom.ai4coding.chatbot.graph;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.telekom.ai4coding.chatbot.BaseIntegrationTest;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;

import java.io.File;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * This is a smoke test. Main integration test which tests the DB structure after graph creation
 * is in the {@link com.telekom.ai4coding.chatbot.tools.graph.GraphRetrievalTest}
 */
class KnowledgeGraphBatchInsertServiceTest extends BaseIntegrationTest {

    @TempDir
    static File tempDir;

    @MockBean
    private Driver mockDriver;

    @Mock
    private Session mockSession;

    @MockBean
    private EmbeddingModel embeddingModel;

    @Autowired
    private KnowledgeGraphBatchInsertService batchInsertService;

    @Autowired
    KnowledgeGraphBuilder knowledgeGraphBuilder;

    @SneakyThrows
    @Test
    void testBatchInsertFileStructure() {
        when(mockDriver.session()).thenReturn(mockSession).thenReturn(mockSession);
        when(mockSession.executeWrite(any())).thenReturn(null);
        Embedding mockEmbedding = Embedding.from(new float[]{0.5f, 0.5f});
        Response<Embedding> mockResponse = Response.from(mockEmbedding);
        when(embeddingModel.embed(anyString())).thenReturn(mockResponse);

        batchInsertService.batchInsertFileStructure(KnowledgeGraphSetup.setup(knowledgeGraphBuilder, tempDir).getRootFileNode(), 1);

        verify(mockDriver, times(2)).session();
        verify(mockSession, times(2)).executeWrite(any());
    }

}