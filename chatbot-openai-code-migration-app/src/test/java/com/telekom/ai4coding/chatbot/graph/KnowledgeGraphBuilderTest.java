package com.telekom.ai4coding.chatbot.graph;

import com.telekom.ai4coding.chatbot.BaseIntegrationTest;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This test uses recursion for extracting information from the graph.
 *
 * Using recursion for information extraction is a deliberate decision to differentiate the test algorithm from the
 * implementation algorithm in {@link KnowledgeGraphBuilder} thus providing more reliable unit testing.
 */
class KnowledgeGraphBuilderTest extends BaseIntegrationTest {

    @TempDir
    static File tempDir;

    @MockBean
    KnowledgeGraph knowledgeGraph;

    @Autowired
    KnowledgeGraphBuilder knowledgeGraphBuilder;

    @BeforeEach
    void beforeEach() throws IOException {
        EmbeddingModel embeddingModel = mock(EmbeddingModel.class);
        when(embeddingModel.dimension()).thenReturn(2);
        Embedding mockEmbedding = Embedding.from(new float[]{0.5f, 0.5f});
        Response<Embedding> mockResponse = Response.from(mockEmbedding);
        when(embeddingModel.embed(anyString())).thenReturn(mockResponse);
        knowledgeGraph = KnowledgeGraphSetup.setup(knowledgeGraphBuilder, tempDir);
    }

    @Test
    public void testFileNodesAreCorrect() {
        List<FileNode> fileNodes = knowledgeGraph.getAllFileNodes();
        // 7 files:
        // {tempDir}, foo, bar, baz, test.py, test.java, test.c, test.txt and test.pdf
        assertEquals(9, fileNodes.size());

        assertThat(fileNodes, hasItem(hasProperty("basename", equalTo("foo"))));
        assertThat(fileNodes, hasItem(hasProperty("basename", equalTo("bar"))));
        assertThat(fileNodes, hasItem(hasProperty("basename", equalTo("baz"))));
        assertThat(fileNodes, hasItem(hasProperty("basename", equalTo("test.py"))));
        assertThat(fileNodes, hasItem(hasProperty("basename", equalTo("test.java"))));
        assertThat(fileNodes, hasItem(hasProperty("basename", equalTo("test.c"))));
        assertThat(fileNodes, hasItem(hasProperty("basename", equalTo("test.txt"))));
        assertThat(fileNodes, hasItem(hasProperty("basename", equalTo("test.pdf"))));

        String sep = File.separator;

        assertThat(fileNodes, hasItem(hasProperty("relativePath", equalTo("foo"))));
        assertThat(fileNodes, hasItem(hasProperty("relativePath", equalTo("foo" + sep + "bar"))));
        assertThat(fileNodes, hasItem(hasProperty("relativePath", equalTo("foo" + sep + "baz"))));
        assertThat(fileNodes, hasItem(hasProperty("relativePath", equalTo("test.py"))));
        assertThat(fileNodes, hasItem(hasProperty("relativePath", equalTo("foo" + sep + "bar" + sep + "test.java"))));
        assertThat(fileNodes, hasItem(hasProperty("relativePath", equalTo("foo" + sep + "bar" + sep + "test.c"))));
        assertThat(fileNodes, hasItem(hasProperty("relativePath", equalTo("foo" + sep + "bar" + sep + "test.txt"))));
        assertThat(fileNodes, hasItem(hasProperty("relativePath", equalTo("foo" + sep + "bar" + sep + "test.pdf"))));
    }

    @Test
    public void testASTNodesAreCorrect() {
        List<ASTNode> astNodes = knowledgeGraph.getAllAstNodes();
        // test.py has 11 AST nodes, test.java has 44 AST nodes and test.c has 29 AST nodes.
        assertEquals(84, astNodes.size());

        // Check if some of the ASTNodes exists.
        assertThat(astNodes, hasItem(allOf(
                hasProperty("type", equalTo("expression_statement")),
                hasProperty("text", equalTo("print(\"Hello world\")")),
                hasProperty("startLine", equalTo(1)),
                hasProperty("endLine", equalTo(1))
        )));
        assertThat(astNodes, hasItem(allOf(
                hasProperty("type", equalTo("expression_statement")),
                hasProperty("text", equalTo("System.out.println(\"Hello world\");")),
                hasProperty("startLine", equalTo(3)),
                hasProperty("endLine", equalTo(3))
        )));
        assertThat(astNodes, hasItem(allOf(
                hasProperty("type", equalTo("expression_statement")),
                hasProperty("text", equalTo("printf(\"Hello world\");")),
                hasProperty("startLine", equalTo(3)),
                hasProperty("endLine", equalTo(3))
        )));
    }

    @Test
    public void testTextNodesAreCorrect() {
        List<TextNode> textNodes = knowledgeGraph.getAllTextNodes();
        assertEquals(2, textNodes.size());

        // Check if some of the TextNode exists.
        assertThat(textNodes, hasItem(allOf(
                hasProperty("text", containsString(KnowledgeGraphSetup.PDF_FILE_CONTENT))
        )));
        assertThat(textNodes, hasItem(allOf(
                hasProperty("text", containsString(KnowledgeGraphSetup.TXT_FILE_CONTENT.strip()))
        )));
    }

    @Test
    public void testHasASTEdgesAreCorrect() {
        List<FileNode> fileNodes = knowledgeGraph.getAllFileNodes();

        List<FileNode> fileNodesWithAstNodes = fileNodes.stream()
                .filter(fileNode -> fileNode.getAstNodes() != null)
                .toList();

        // test.py, test.java and test.c should have hasASTEdge to their root file node.
        assertEquals(3, fileNodesWithAstNodes.size());

        FileNode testPyFileNode = fileNodesWithAstNodes.stream()
                .filter(fileNode -> fileNode.getBasename().equals("test.py"))
                .findFirst()
                .get();

        Optional<ASTNode> moduleASTNode = testPyFileNode.getAstNodes()
                .stream()
                .filter(astNode -> astNode.getType().equals("module"))
                .findFirst();

        Assertions.assertTrue(moduleASTNode.isPresent());

        FileNode testJavaFileNode = fileNodesWithAstNodes.stream()
                .filter(fileNode -> fileNode.getBasename().equals("test.java"))
                .findFirst()
                .get();

        Optional<ASTNode> programASTNode = testJavaFileNode.getAstNodes()
                .stream()
                .filter(astNode -> astNode.getType().equals("program"))
                .findFirst();

        Assertions.assertTrue(programASTNode.isPresent());

        FileNode testCFileNode = fileNodesWithAstNodes.stream()
                .filter(fileNode -> fileNode.getBasename().equals("test.c"))
                .findFirst()
                .get();

        Optional<ASTNode> translationUnitASTNode = testCFileNode.getAstNodes()
                .stream()
                .filter(astNode -> astNode.getType().equals("translation_unit"))
                .findFirst();

        Assertions.assertTrue(translationUnitASTNode.isPresent());
    }

    @Test
    public void testHasFileEdgesAreCorrect() {
        List<FileNode> fileNodes = knowledgeGraph.getAllFileNodes();

        List<FileNode> allChildFileNodes = fileNodes.stream()
                .filter(fileNode -> fileNode.getChildFileNodes() != null)
                .flatMap(fileNode -> fileNode.getChildFileNodes().stream())
                .toList();
        // We have 6 parent-child relation between FileNode:
        // {tempDir} -> foo
        // {tempDir} -> test.py
        // foo -> bar
        // foo -> baz
        // bar -> test.c
        // bar -> test.java
        // bar -> test.txt
        // bar -> test.pdf
        assertEquals(8, allChildFileNodes.size());

        assertTrue(allChildFileNodes.stream().anyMatch(fileNode -> fileNode.getBasename().equals("foo")));
        List<FileNode> fooNodeChildren = allChildFileNodes.stream()
                .filter(fileNode -> fileNode.getBasename().equals("foo"))
                .flatMap(fileNode -> fileNode.getChildFileNodes().stream())
                .toList();
        assertEquals(2, fooNodeChildren.size());
        Optional<FileNode> barNode = fooNodeChildren.stream()
                .filter(fileNode -> fileNode.getBasename().equals("bar"))
                .findAny();
        assertTrue(barNode.isPresent());
        Optional<FileNode> bazNode = fooNodeChildren.stream()
                .filter(fileNode -> fileNode.getBasename().equals("baz"))
                .findAny();
        assertTrue(bazNode.isPresent());

        assertTrue(allChildFileNodes.stream().anyMatch(fileNode -> fileNode.getBasename().equals("test.py")));
        FileNode testPyNode = allChildFileNodes.stream()
                .filter(fileNode -> fileNode.getBasename().equals("test.py"))
                .findAny()
                .get();
        assertNull(testPyNode.getChildFileNodes());

        assertTrue(allChildFileNodes.stream().anyMatch(fileNode -> fileNode.getBasename().equals("bar")));
        List<FileNode> barNodeChildren = allChildFileNodes.stream()
                .filter(fileNode -> fileNode.getBasename().equals("bar"))
                .flatMap(fileNode -> fileNode.getChildFileNodes().stream())
                .toList();
        assertEquals(4, barNodeChildren.size());
        Optional<FileNode> testCNode = barNodeChildren.stream()
                .filter(fileNode -> fileNode.getBasename().equals("test.c"))
                .findAny();
        assertTrue(testCNode.isPresent());
        Optional<FileNode> testJavaNode = barNodeChildren.stream()
                .filter(fileNode -> fileNode.getBasename().equals("test.java"))
                .findAny();
        assertTrue(testJavaNode.isPresent());
        Optional<FileNode> testTxtNode = barNodeChildren.stream()
                .filter(fileNode -> fileNode.getBasename().equals("test.txt"))
                .findAny();
        assertTrue(testTxtNode.isPresent());
        Optional<FileNode> testPdfNode = barNodeChildren.stream()
                .filter(fileNode -> fileNode.getBasename().equals("test.pdf"))
                .findAny();
        assertTrue(testPdfNode.isPresent());

        assertTrue(allChildFileNodes.stream().anyMatch(fileNode -> fileNode.getBasename().equals("baz")));
        FileNode bazChildNode = allChildFileNodes.stream()
                .filter(fileNode -> fileNode.getBasename().equals("baz"))
                .findAny()
                .get();
        assertNull(bazChildNode.getChildFileNodes());

        assertTrue(allChildFileNodes.stream().anyMatch(fileNode -> fileNode.getBasename().equals("test.c")));
        FileNode testCChildNode = allChildFileNodes.stream()
                .filter(fileNode -> fileNode.getBasename().equals("test.c"))
                .findAny()
                .get();
        assertNull(testCChildNode.getChildFileNodes());

        assertTrue(allChildFileNodes.stream().anyMatch(fileNode -> fileNode.getBasename().equals("test.java")));
        FileNode testJavaChildNode = allChildFileNodes.stream()
                .filter(fileNode -> fileNode.getBasename().equals("test.java"))
                .findAny()
                .get();
        assertNull(testJavaChildNode.getChildFileNodes());

        assertTrue(allChildFileNodes.stream().anyMatch(fileNode -> fileNode.getBasename().equals("test.txt")));
        FileNode testTxtChildNode = allChildFileNodes.stream()
                .filter(fileNode -> fileNode.getBasename().equals("test.txt"))
                .findAny()
                .get();
        assertNull(testTxtChildNode.getChildFileNodes());

        assertTrue(allChildFileNodes.stream().anyMatch(fileNode -> fileNode.getBasename().equals("test.pdf")));
        FileNode testPdfChildNode = allChildFileNodes.stream()
                .filter(fileNode -> fileNode.getBasename().equals("test.pdf"))
                .findAny()
                .get();
        assertNull(testPdfChildNode.getChildFileNodes());
    }


    @Test
    public void testParentOfEdgesAreCorrect() {
        List<ASTNode> astNodes = knowledgeGraph.getAllAstNodes();
        List<ASTNode> allChildASTNodes = astNodes.stream()
                .filter(astNode -> astNode.getChildASTNodes() != null)
                .flatMap(astNode -> astNode.getChildASTNodes().stream())
                .toList();

        assertEquals(81, allChildASTNodes.size());

        // Check if some of the parentOfEdge exists.
        Optional<ASTNode> printASTChildNode = allChildASTNodes.stream()
                .filter(astNode -> astNode.getText().equals("print(\"Hello world\")"))
                .filter(astNode -> astNode.getType().equals("call"))
                .findAny()
                .get()
                .getChildASTNodes()
                .stream()
                .filter(astNode -> astNode.getText().equals("print"))
                .findAny();
        assertTrue(printASTChildNode.isPresent());

        Optional<ASTNode> printlnASTChildNode = allChildASTNodes.stream()
                .filter(astNode -> astNode.getText().equals("System.out.println(\"Hello world\")"))
                .findAny()
                .get()
                .getChildASTNodes()
                .stream()
                .filter(astNode -> astNode.getText().equals("println"))
                .findAny();
        assertTrue(printlnASTChildNode.isPresent());

        Optional<ASTNode> printfASTChildNode = allChildASTNodes.stream()
                .filter(astNode -> astNode.getText().equals("printf(\"Hello world\")"))
                .findAny()
                .get()
                .getChildASTNodes()
                .stream()
                .filter(astNode -> astNode.getText().equals("printf"))
                .findAny();
        assertTrue(printfASTChildNode.isPresent());
    }

    @Test
    public void testHasTextEdgesAreCorrect() {
        List<FileNode> fileNodes = knowledgeGraph.getAllFileNodes();
        List<TextNode> allChildTextNodes = fileNodes.stream()
                .filter(fileNode -> fileNode.getTextNodes() != null)
                .flatMap(fileNode -> fileNode.getTextNodes().stream())
                .toList();

        assertEquals(2, allChildTextNodes.size());

        assertTrue(fileNodes.stream()
                .filter(fileNode -> fileNode.getBasename() != null)
                .anyMatch(fileNode -> "test.txt".equals(fileNode.getBasename())));
        FileNode testTxtChildNode = fileNodes.stream()
                .filter(fileNode -> fileNode.getBasename() != null)
                .filter(fileNode -> "test.txt".equals(fileNode.getBasename()))
                .findAny()
                .orElseThrow(() -> new AssertionError("FileNode with basename 'test.txt' not found"));
        assertEquals(1, testTxtChildNode.getTextNodes().size());

        assertTrue(fileNodes.stream()
                .filter(fileNode -> fileNode.getBasename() != null)
                .anyMatch(fileNode -> "test.pdf".equals(fileNode.getBasename())));
        FileNode testPDFChildNode = fileNodes.stream()
                .filter(fileNode -> fileNode.getBasename() != null)
                .filter(fileNode -> "test.pdf".equals(fileNode.getBasename()))
                .findAny()
                .orElseThrow(() -> new AssertionError("FileNode with basename 'test.pdf' not found"));
        assertEquals(1, testPDFChildNode.getTextNodes().size());
    }
}
