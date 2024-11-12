package com.telekom.ai4coding.chatbot.tools.embedding;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.telekom.ai4coding.chatbot.BaseIntegrationTest;
import com.telekom.ai4coding.chatbot.graph.ASTNode;
import com.telekom.ai4coding.chatbot.graph.FileNode;
import com.telekom.ai4coding.chatbot.graph.KnowledgeGraphBatchInsertService;
import com.telekom.ai4coding.chatbot.graph.TextNode;
import com.telekom.ai4coding.chatbot.repository.FileNodeRepository;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Neo4jEmbeddingStoreTest extends BaseIntegrationTest {

    private ACAEmbeddingStore embeddingStore;

    @Autowired
    private Driver neo4jDriver;

    @Autowired
    KnowledgeGraphBatchInsertService knowledgeGraphBatchInsertService;

    @Autowired
    FileNodeRepository fileNodeRepository;

    private ASTNode astNode1;
    private ASTNode astNode2;
    private TextNode textNode1;
    private TextNode textNode2;


    @BeforeEach
    void beforeEach() {
        astNode2 = new ASTNode(0L, "type2", "text2", -1, -1, new double[]{1, 0}, null);
        astNode1 = new ASTNode(1L, "type1", "text1", -1, -1, new double[]{0, 1}, List.of(astNode2));

        FileNode JavaFileNode = new FileNode(2L, "test.java", "src", null, List.of(astNode1), null, null);

        textNode2 = new TextNode(3L, "text2", "metadata2", new double[]{1, 0}, null);
        textNode1 = new TextNode(4L, "text1", "metadata1", new double[]{0, 1}, textNode2);

        FileNode TextFileNode = new FileNode(5L, "test.txt", "src", null, null, List.of(textNode1, textNode2), null);

        FileNode rootFileNode = new FileNode(6L, "src", ".", List.of(JavaFileNode, TextFileNode), null, null, null);

        knowledgeGraphBatchInsertService.batchInsertFileStructure(rootFileNode, 2);

        embeddingStore = ACAEmbeddingStore.fromExistingIndices(neo4jDriver, "ASTNodeEmbedding", "TextNodeEmbedding");
    }

    @AfterEach
    void clean() {
        fileNodeRepository.deleteCompleteCodeGraph();
    }

    @Test
    public void testSearchOneASTNodes(){
        Embedding queryEmbdding = Embedding.from(new float[] {0.1f, 0f});
        EmbeddingSearchRequest embeddingSearchRequest = EmbeddingSearchRequest.builder()
            .queryEmbedding(queryEmbdding)
            .maxResults(1)
            .build();
        List<ASTNode> astNodes = embeddingStore.searchASTNodes(embeddingSearchRequest);
        assertEquals(1, astNodes.size());

        assertEquals(astNode2, astNodes.get(0));
    }

    @Test
    public void testSearchTwoASTNodes(){
        Embedding queryEmbdding = Embedding.from(new float[] {0.1f, 0f});
        EmbeddingSearchRequest embeddingSearchRequest = EmbeddingSearchRequest.builder()
            .queryEmbedding(queryEmbdding)
            .maxResults(2)
            .build();
        List<ASTNode> astNodes = embeddingStore.searchASTNodes(embeddingSearchRequest);
        assertEquals(2, astNodes.size());

        assertEquals(astNode2, astNodes.get(0));
        assertEquals(astNode1, astNodes.get(1));
    }

    @Test
    public void testSearchASTNodesWithMinScore(){
        Embedding queryEmbdding = Embedding.from(new float[] {0.1f, 0f});
        // [0.1, 0.0] has 1.0 as consine similarity with [1.0, 0.0]
        // Therefore even with maxResults=2, it only returns 1 result
        EmbeddingSearchRequest embeddingSearchRequest = EmbeddingSearchRequest.builder()
            .queryEmbedding(queryEmbdding)
            .maxResults(2)
            .minScore(0.99)
            .build();
        List<ASTNode> astNodes = embeddingStore.searchASTNodes(embeddingSearchRequest);
        assertEquals(1, astNodes.size());

        assertEquals(astNode2, astNodes.get(0));
    }

    @Test
    public void testSearchOneTextNodes(){
        Embedding queryEmbdding = Embedding.from(new float[] {0.1f, 0f});
        EmbeddingSearchRequest embeddingSearchRequest = EmbeddingSearchRequest.builder()
            .queryEmbedding(queryEmbdding)
            .maxResults(1)
            .build();
        List<TextNode> textNodes = embeddingStore.searchTextNodes(embeddingSearchRequest);
        assertEquals(1, textNodes.size());

        assertEquals(textNode2, textNodes.get(0));
    }

    @Test
    public void testSearchTwoTextNodes(){
        Embedding queryEmbdding = Embedding.from(new float[] {0.1f, 0f});
        EmbeddingSearchRequest embeddingSearchRequest = EmbeddingSearchRequest.builder()
            .queryEmbedding(queryEmbdding)
            .maxResults(2)
            .build();
        List<TextNode> textNodes = embeddingStore.searchTextNodes(embeddingSearchRequest);
        assertEquals(2, textNodes.size());

        assertEquals(textNode2, textNodes.get(0));
        assertEquals(textNode1, textNodes.get(1));
    }

    @Test
    public void testSearchTextNodesWithMinScore(){
        Embedding queryEmbdding = Embedding.from(new float[] {0.1f, 0f});
        // [0.1, 0.0] has 1.0 as consine similarity with [1.0, 0.0]
        // Therefore even with maxResults=2, it only returns 1 result
        EmbeddingSearchRequest embeddingSearchRequest = EmbeddingSearchRequest.builder()
            .queryEmbedding(queryEmbdding)
            .maxResults(2)
            .minScore(0.99)
            .build();
        List<TextNode> textNodes = embeddingStore.searchTextNodes(embeddingSearchRequest);
        assertEquals(1, textNodes.size());

        assertEquals(textNode2, textNodes.get(0));
    }

}
