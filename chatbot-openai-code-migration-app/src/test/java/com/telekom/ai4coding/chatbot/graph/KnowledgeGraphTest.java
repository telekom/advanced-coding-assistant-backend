package com.telekom.ai4coding.chatbot.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.telekom.ai4coding.chatbot.BaseIntegrationTest;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class KnowledgeGraphTest extends BaseIntegrationTest {
    @TempDir
    static File tempDir;

    KnowledgeGraph knowledgeGraph;

    @MockBean
    EmbeddingModel embeddingModel;

    @Autowired
    KnowledgeGraphBuilder knowledgeGraphBuilder;


    @BeforeEach
    void beforeEach() throws IOException {
        when(embeddingModel.dimension()).thenReturn(2);
        Embedding mockEmbedding = Embedding.from(new float[]{0.5f, 0.5f});
        Response<Embedding> mockResponse = Response.from(mockEmbedding);
        when(embeddingModel.embed(anyString())).thenReturn(mockResponse);
        knowledgeGraph = KnowledgeGraphSetup.setup(knowledgeGraphBuilder, tempDir);
    }

    @Test
    public void testGetAllFileNodes() {
        assertEquals(9, knowledgeGraph.getAllFileNodes().size());
    }

    @Test public void testGetAllAstNodes() {
        assertEquals(84, knowledgeGraph.getAllAstNodes().size());
    }

    @Test public void testGetAllTextNodes() {
        assertEquals(2, knowledgeGraph.getAllTextNodes().size());
    }

    @Test
    public void testGetFileTree() {
        String fileTree = knowledgeGraph.getFileTree();
        String expectedFileTree = """
                ├── foo
                │   ├── bar
                │   │   ├── test.c
                │   │   ├── test.java
                │   │   ├── test.pdf
                │   │   └── test.txt
                │   └── baz
                └── test.py""";
        // We remove the first line because it contains the root directory name,
        // which is can be anything because we are using a temporary directory
        // in the test.
        fileTree = fileTree.substring(fileTree.indexOf('\n')+1);
        assertEquals(expectedFileTree, fileTree);
    }
}
