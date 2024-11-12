package com.telekom.ai4coding.chatbot.tools.graph;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.Encoding;
import com.knuddels.jtokkit.api.EncodingRegistry;
import com.knuddels.jtokkit.api.IntArrayList;
import com.knuddels.jtokkit.api.ModelType;
import com.telekom.ai4coding.chatbot.BaseIntegrationTest;
import com.telekom.ai4coding.chatbot.graph.*;
import com.telekom.ai4coding.chatbot.repository.ASTNodeRepository;
import com.telekom.ai4coding.chatbot.repository.FileNodeRepository;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class GraphRetrievalTest extends BaseIntegrationTest {

    private static final String SEPARATOR = File.separator;

    @TempDir
    private File tempDir;

    private static GraphRetrieval graphRetrievalTools;

    @Autowired
    FileNodeRepository fileNodeRepository;

    @Autowired
    ASTNodeRepository astNodeRepository;

    @Autowired
    KnowledgeGraphBatchInsertService knowledgeGraphBatchInsertService;

    @MockBean
    EmbeddingModel embeddingModel;

    @Value("${spring.neo4j.uri}")
    private String neo4jUri;

    @Value("${spring.neo4j.authentication.username}")
    private String neo4jUsername;

    @Value("${spring.neo4j.authentication.password}")
    private String neo4jPassword;

    @Autowired
    KnowledgeGraphBuilder knowledgeGraphBuilder;

    private static final int MAX_TOKEN = 10_000;

    @BeforeEach
    void beforeEach() throws IOException {
        when(embeddingModel.dimension()).thenReturn(2);
        Embedding mockEmbedding = Embedding.from(new float[]{0.5f, 0.5f});
        Response<Embedding> mockResponse = Response.from(mockEmbedding);
        when(embeddingModel.embed(anyString())).thenReturn(mockResponse);
        KnowledgeGraph g = KnowledgeGraphSetup.setup(knowledgeGraphBuilder, tempDir);

        knowledgeGraphBatchInsertService.batchInsertFileStructure(g.getRootFileNode(), embeddingModel.dimension());

        graphRetrievalTools = new GraphRetrieval(
            neo4jUri,
            neo4jUsername,
            neo4jPassword,
            MAX_TOKEN
        );
    }

    @AfterEach
    void clean() {
        fileNodeRepository.deleteCompleteCodeGraph();
        astNodeRepository.deleteAll();
    }

    @Test
    public void testFindFileNode() {
        String result = graphRetrievalTools.findFileNode("test.py");
        assertFalse(result.isEmpty());
        String expectedResult = ("Result 1:\\R"+
                                 "FileNode: \\{id: \\d+, basename: \"test\\.py\", relativePath: \"test\\.py\"\\}");
        assertTrue(result.matches(expectedResult));

        result = graphRetrievalTools.findFileNode("test.go");
        assertEquals(GraphRetrieval.NO_RESULT_STRING, result);
    }

    @Test
    public void testFindASTNodeByTypeAndCode() {
        String result = graphRetrievalTools.findASTNodeByTypeAndCode(
            "argument_list", "(\"Hello world\")"
        );
        assertFalse(result.isEmpty());
        
        String[] resultLines = result.split(System.getProperty("line.separator"));
        assertTrue(resultLines.length == 9);
        assertEquals(resultLines[0], "Result 1:");
        assertTrue(resultLines[1].contains("type: \"argument_list\""));
        assertTrue(resultLines[1].contains("text: \"(\\\"Hello world\\\")\""));
        assertEquals(resultLines[3], "Result 2:");
        assertTrue(resultLines[4].contains("type: \"argument_list\""));
        assertTrue(resultLines[4].contains("text: \"(\\\"Hello world\\\")\""));
        assertEquals(resultLines[6], "Result 3:");
        assertTrue(resultLines[7].contains("type: \"argument_list\""));
        assertTrue(resultLines[7].contains("text: \"(\\\"Hello world\\\")\""));
    }

    @Test
    public void testFindASTNodeByTypeAndBasename() {
        String result = graphRetrievalTools.findASTNodeByTypeAndBasename(
            "test.py", "argument_list"
        );
        assertFalse(result.isEmpty());
        String expectedResult = ("Result 1:\\R" +
            "ASTNode: \\{endLine: 1, id: \\d+, text: \"\\(\\\\\"Hello world\\\\\"\\)\", type: \"argument_list\", startLine: 1\\}\\R" +
            "FileNode: \\{id: \\d+, basename: \"test\\.py\", relativePath: \"test\\.py\"\\}"
        );
        assertTrue(result.matches(expectedResult));
    }

    @Test
    public void testFindASTNodeByCodeInFileNode() {
        String result = graphRetrievalTools.findASTNodeByCodeInFileNode(
            "test.c", "return 0;"
        );
        assertFalse(result.isEmpty());

        String[] resultLines = result.split(System.getProperty("line.separator"));
        // Because we would find many examples of the code snippet in the file
        // due to the tree structure, ie. the parent will contains the code snippet
        // of the child, we will have multiple results. Here we only check the first one.
        assertTrue(resultLines.length > 3);
        assertEquals("Result 1:", resultLines[0]);
        assertTrue(resultLines[1].matches("ASTNode: \\{endLine: 4, id: \\d+, text: \"return 0;\", type: \"return_statement\", startLine: 4\\}"));
        assertTrue(resultLines[2].matches("FileNode: \\{id: \\d+, basename: \"test\\.c\", relativePath: \"foo[/\\\\\\\\]bar[/\\\\\\\\]test\\.c\"\\}"));
    }

    @Test
    public void testSearchDocumentation() {
        String result = graphRetrievalTools.searchDocumentation("Hello world");

        assertFalse(result.isEmpty());
        String[] resultLines = result.split(System.getProperty("line.separator"));

        assertTrue(resultLines.length == 6);
        assertEquals("Result 1:", resultLines[0]);
        assertTrue(resultLines[1].contains("basename: \"test.pdf\""));
        assertTrue(resultLines[2].contains("text: \"Hello world from PDF file.\""));
        assertEquals("Result 2:", resultLines[3]);
        assertTrue(resultLines[4].contains("basename: \"test.txt\""));
        assertTrue(resultLines[5].contains("text: \"Hello world from txt file.\""));
    }

    @Test
    public void testSearchDocumentationInFile() {
        String result = graphRetrievalTools.searchDocumentationInFile("Hello world", "test.pdf");

        assertFalse(result.isEmpty());
        String[] resultLines = result.split(System.getProperty("line.separator"));

        assertTrue(resultLines.length == 3);
        assertEquals("Result 1:", resultLines[0]);
        assertTrue(resultLines[1].contains("basename: \"test.pdf\""));
        assertTrue(resultLines[2].contains("text: \"Hello world from PDF file.\""));
    }

    @Test
    public void testPreviewFileContent() {
        String result = graphRetrievalTools.previewFileContent("test.c");
        assertFalse(result.isEmpty());

        // We are not testing the result with an exact match as Strings are formatted differently on
        // different platforms (win, unix).
        Assertions.assertTrue(result.contains("Result 1:"));
        Assertions.assertTrue(result.contains("FileNode: {id:"));
        Assertions.assertTrue(result.contains(
                "basename: \"test.c\", relativePath: \"foo%sbar%stest.c\"}".formatted(SEPARATOR, SEPARATOR)));
        Assertions.assertTrue(result.contains("preview: \"#include <stdio.h>"));
        Assertions.assertTrue(result.contains("int main() {"));
        Assertions.assertTrue(result.contains("printf"));
        Assertions.assertTrue(result.contains("Hello world"));
        Assertions.assertTrue(result.contains("return 0;"));
    }

    @Test
    public void testGetParentOfFileNode() {
        Optional<FileNode> optionalCFileNode = fileNodeRepository.findByRelativePath("foo"+ SEPARATOR +"bar"+ SEPARATOR +"test.c");
        Assertions.assertTrue(optionalCFileNode.isPresent());

        FileNode cFileNode = optionalCFileNode.get();

        // Regular expression to remove the id fields
        String regex = "\\s*id:\\s*\\d+,\\s*";
        String result = graphRetrievalTools.getParent(cFileNode.getId()).replaceAll(regex, "");
        String expectedResult = """
                Result 1:
                Parent: {basename: "bar", relativePath: "foo%sbar"}""".formatted(SEPARATOR);

        NormalizedResult normalizedResults = getNormalizedResult(result, expectedResult);

        assertEquals(normalizedResults.normalizedExpectedResult(), normalizedResults.normalizedResult());
    }

    private NormalizedResult getNormalizedResult(String result, String expectedResult) {
        // Normalize line endings for both result and expectedResult
        String normalizedResult = result.replaceAll("\\r\\n", "\n");
        String normalizedExpectedResult = expectedResult.replaceAll("\\r\\n", "\n");
        return new NormalizedResult(normalizedResult, normalizedExpectedResult);
    }

    private record NormalizedResult(String normalizedResult, String normalizedExpectedResult) {
    }

    @Test
    public void testGetParentOfASTNode() {
        // This ASTNode is the return statement in the C file and no other
        // ASTNode has the same text.
        List<ASTNode> astNodes = astNodeRepository.findByText("return 0;");
        assertTrue(astNodes.size() == 1);

        ASTNode astNode = astNodes.get(0);

        // Regular expression to remove the id fields
        String regex = ",?\\s*id:\\s*\\d+";
        String result = graphRetrievalTools.getParent(astNode.getId()).replaceAll(regex, "");
        // The parent node of the return statement is the statement block.
        // The formatting is caused by the text attribute keeping the newlines and whitespaces
        // from the file.
        String expectedResult = """
                Result 1:
                Parent: {endLine: 5, text: "{
                  printf(\\"Hello world\\");
                  return 0;
                }", type: "compound_statement", startLine: 2}""";

        NormalizedResult normalizedResult = getNormalizedResult(result, expectedResult);

        assertEquals(normalizedResult.normalizedExpectedResult, normalizedResult.normalizedResult());
    }

    @Test
    public void testGetParentOfNoneNode() {
        Optional<FileNode> optionalCFileNode = fileNodeRepository.findByRelativePath(".");
        assertTrue(optionalCFileNode.isPresent());

        FileNode cFileNode = optionalCFileNode.get();

        String result = graphRetrievalTools.getParent(cFileNode.getId());
        String expectedResult = GraphRetrieval.NO_RESULT_STRING;
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetChildrenOfFileNode() {
        Optional<FileNode> optionalCFileNode = fileNodeRepository.findByRelativePath("foo");
        assertTrue(optionalCFileNode.isPresent());

        FileNode cFileNode = optionalCFileNode.get();

        // Regular expression to remove the id fields
        String regex = "\\s*id:\\s*\\d+,\\s*";
        String result = graphRetrievalTools.getChildren(cFileNode.getId()).replaceAll(regex, "");;
        String expectedResult = """
                Result 1:
                Children: {basename: "bar", relativePath: "foo%sbar"}
                Result 2:
                Children: {basename: "baz", relativePath: "foo%sbaz"}""".formatted(SEPARATOR, SEPARATOR);

        NormalizedResult normalizedResult = getNormalizedResult(result, expectedResult);

        assertEquals(normalizedResult.normalizedExpectedResult, normalizedResult.normalizedResult());
    }

    @Test
    public void testGetChildrenOfASTNode() {
        String searchedText = """
                {
                  printf("Hello world");
                  return 0;
                }""";
        List<ASTNode> astNodes = astNodeRepository.findByText(searchedText);
        assertTrue(astNodes.size() == 1);

        ASTNode astNode = astNodes.get(0);

        // Regular expression to remove the id fields
        String regex = ",?\\s*id:\\s*\\d+";

        String result = graphRetrievalTools.getChildren(astNode.getId()).replaceAll(regex, "");
        String expectedResult = """
                Result 1:
                Children: {endLine: 2, text: "{", type: "{", startLine: 2}
                Result 2:
                Children: {endLine: 3, text: "printf(\\"Hello world\\");", type: "expression_statement", startLine: 3}
                Result 3:
                Children: {endLine: 4, text: "return 0;", type: "return_statement", startLine: 4}
                Result 4:
                Children: {endLine: 5, text: "}", type: "}", startLine: 5}""";

        NormalizedResult normalizedResult = getNormalizedResult(result, expectedResult);

        assertEquals(normalizedResult.normalizedExpectedResult, normalizedResult.normalizedResult());
    }

    @Test
    public void testTruncateString() {
        int maxToken = 100;
        EncodingRegistry registry = Encodings.newDefaultEncodingRegistry();
        Encoding encoding = registry.getEncodingForModel(ModelType.GPT_4);
    
        // The number of tokens from this request with exceed our maxToken limit.
        String result = graphRetrievalTools.findASTNodeByTypeAndCode(
            "argument_list", "(\"Hello world\")"
        );
        assertFalse(result.isEmpty());
        IntArrayList result_encoded = encoding.encode(result);
        assertTrue(result_encoded.size() > maxToken);

        // We create a new GraphRetrieval and check if it keeps within the limit.
        GraphRetrieval truncatedGraphRetrievalTools = new GraphRetrieval(
            neo4jUri,
            neo4jUsername,
            neo4jPassword,
            maxToken
        );
        String truncatedResult = truncatedGraphRetrievalTools.findASTNodeByTypeAndCode(
            "argument_list", "(\"Hello world\")"
        );
        assertFalse(result.isEmpty());
        IntArrayList truncated_result_encoded = encoding.encode(truncatedResult);
        assertTrue(truncated_result_encoded.size() <= maxToken);
    }


}
