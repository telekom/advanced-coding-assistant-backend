package com.telekom.ai4coding.chatbot.service;

import com.telekom.ai4coding.chatbot.BaseIntegrationTest;
import org.junit.jupiter.api.*;
import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SearchDatabaseServiceIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private SearchDatabaseService searchDatabaseService;

    @Autowired
    private Driver neo4jDriver;

    @BeforeEach
    void setUp() {
        // Setup test data in Neo4j
        try (var session = neo4jDriver.session()) {
            session.run("CREATE (f:FileNode {relativePath: 'test.py'})-[:HAS_AST]->(a:ASTNode {type: 'program', text: 'def test_function(): pass'})");
            session.run("CREATE (f:FileNode {relativePath: 'main.py'})-[:HAS_TEXT]->(t:TextNode {text: 'This is a test file'})");
            session.run("CREATE (f:FileNode {relativePath: 'util.py'})-[:HAS_AST]->(a:ASTNode {type: 'program', text: 'def utility_function(): return True'})");
        }
    }

    @AfterEach
    void tearDown() {
        // Clean up test data
        try (var session = neo4jDriver.session()) {
            session.run("MATCH (n) DETACH DELETE n");
        }
    }

    @Test
    void testExecuteAstAndTextKeywordSearch_SmokeTest() {
        String[] keywords = {"test", "function"};
        Map<String, String> results = searchDatabaseService.executeAstAndTextKeywordSearch(keywords);

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertTrue(results.containsKey("test.py") || results.containsKey("main.py"));

        if (results.containsKey("test.py")) {
            assertEquals("def test_function(): pass", results.get("test.py"));
        }
        if (results.containsKey("main.py")) {
            assertEquals("This is a test file", results.get("main.py"));
        }
    }

    @Test
    void testExecuteAstAndTextKeywordSearch_OnlyASTMatch() {
        String[] keywords = {"def", "function"};
        Map<String, String> results = searchDatabaseService.executeAstAndTextKeywordSearch(keywords);

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertTrue(results.containsKey("test.py"));
        assertEquals("def test_function(): pass", results.get("test.py"));
        assertFalse(results.containsKey("main.py"));
    }

    @Test
    void testExecuteAstAndTextKeywordSearch_OnlyTextMatch() {
        String[] keywords = {"this", "file"};
        Map<String, String> results = searchDatabaseService.executeAstAndTextKeywordSearch(keywords);

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertTrue(results.containsKey("main.py"));
        assertEquals("This is a test file", results.get("main.py"));
        assertFalse(results.containsKey("test.py"));
    }

    @Test
    void testExecuteAstAndTextKeywordSearch_NoMatch() {
        String[] keywords = {"nonexistent", "keyword"};
        Map<String, String> results = searchDatabaseService.executeAstAndTextKeywordSearch(keywords);

        assertNotNull(results);
        assertTrue(results.isEmpty());
    }

    @Test
    void testExecuteAstAndTextKeywordSearch_CaseInsensitivity() {
        String[] keywords = {"DEF", "FUNCTION", "THIS", "FILE"};
        Map<String, String> results = searchDatabaseService.executeAstAndTextKeywordSearch(keywords);

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertTrue(results.containsKey("test.py"));
        assertTrue(results.containsKey("main.py"));
    }

    @Test
    void testExecuteAstAndTextKeywordSearch_PartialMatch() {
        String[] keywords = {"func", "fil"};
        Map<String, String> results = searchDatabaseService.executeAstAndTextKeywordSearch(keywords);

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertTrue(results.containsKey("test.py") || results.containsKey("main.py") || results.containsKey("util.py"));
    }

    @Test
    void testExecuteAstAndTextKeywordSearch_MultipleKeywords() {
        String[] keywords = {"test", "function", "file", "utility"};
        Map<String, String> results = searchDatabaseService.executeAstAndTextKeywordSearch(keywords);

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertTrue(results.containsKey("test.py"));
        assertTrue(results.containsKey("main.py"));
        assertTrue(results.containsKey("util.py"));
    }
}