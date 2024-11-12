package com.telekom.ai4coding.chatbot.tools.graph;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.store.graph.neo4j.Neo4jGraph;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.internal.value.NodeValue;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.Encoding;
import com.knuddels.jtokkit.api.EncodingRegistry;
import com.knuddels.jtokkit.api.EncodingResult;
import com.knuddels.jtokkit.api.IntArrayList;
import com.knuddels.jtokkit.api.ModelType;
import com.telekom.ai4coding.chatbot.treesitter.TreeSitterParser;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The GraphRetrieval class is responsible for retrieving data from a Neo4j graph database
 * related to a codebase. It provides methods to search for FileNodes and ASTNodes based on
 * different criteria such as basename, node type, and code snippet.
 */
@Slf4j
public class GraphRetrieval {

    static final String NO_RESULT_STRING = "No results found. Please check the input parameters.";

    private final int NEO4J_MAX_RESULT = 10;
    private final Neo4jGraph graph;
    private final int maxToken;
    private final Encoding encoding;
    private final String EXCEED_LENGTH_STRING = "..." + System.getProperty("line.separator") + "Truncated because it is too long";

    public GraphRetrieval(String uri, String user, String password, int maxToken) {
        Driver driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
        this.graph = new Neo4jGraph(driver);
        this.maxToken = maxToken;

        EncodingRegistry registry = Encodings.newDefaultEncodingRegistry();
        this.encoding = registry.getEncodingForModel(ModelType.GPT_4);
    }

    private String formatRecords(List<Record> records) {
        if(records.isEmpty()) {
            return NO_RESULT_STRING;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < records.size(); i++) {
            sb.append("Result ").append(i + 1).append(":").append(System.getProperty("line.separator"));
            sb.append(records.get(i).fields().stream()
                          .sorted(Comparator.comparing(field -> field.key()))
                          .map((field) -> {
                                if(field.value() instanceof NodeValue) {
                                    Map<String, Object> valueMap = field.value().asMap();
                                    // Remove embedding from attributes, since it is long and unnessary.
                                    valueMap.remove("embedding");
                                    return field.key() + ": " + field.value().asMap().toString();
                                }else{
                                    return field.key() + ": " + field.value().toString();
                                }
                            })
                          .collect(Collectors.joining(System.getProperty("line.separator"))));
            sb.append(System.getProperty("line.separator"));
        }
        return truncateString(sb.toString().trim());
    }

    private String truncateString(String text) {
        // We calculate number of tokens in EXCEED_LENGTH_STRING to subtract the maxToken limit.
        // This is to ensure that number of tokens is definitively under maxToken.
        IntArrayList exceed_length_string_encoded = this.encoding.encode(EXCEED_LENGTH_STRING);
        EncodingResult encoded = this.encoding.encode(
            text, this.maxToken-exceed_length_string_encoded.size());
        String truncatedText = encoding.decode(encoded.getTokens());

        if(encoded.isTruncated()) {
            truncatedText = truncatedText + EXCEED_LENGTH_STRING;
        }
        return truncatedText;
    }


    @Tool("This is the first tool/function you should use to gage and get general understanding which files the repository contains." +
            "Search the repository/codebase/project for all files. Will return files and their relative paths.")
    public String findAllFilesRelativePaths() {
        log.info("Using tool 'findAllFilesRelativePaths'");
        String query = """
            MATCH (n:FileNode) RETURN n.relativePath
            """;

        List<Record> records = graph.executeRead(query);
        return formatRecords(records);
    }

    /**
     * Search the FileNode in the codebase given the basename of the file/dir.
     * 
     * @param basename The basename of the FileNode to search for.
     * @return The formatted records of the matched FileNode.
     */
    @Tool("Search the FileNode in the codebase given the basename of the file/dir, " +
          "including the extension, for example \"foo.txt\" or \"src\".")
    public String findFileNode(@P("The basename of the FileNode to search for") String basename) {
        log.info("Using tool 'findFileNode'");
        String query = """
            MATCH (n:FileNode { basename: '%s'})
            RETURN apoc.map.merge({id: id(n)}, n) AS FileNode
            """.formatted(basename);
        
        List<Record> records = graph.executeRead(query);
        return formatRecords(records);
    }

    /**
     * Find all ASTNode and their associated FileNode in the whole codebase by the
     * ASTNode type, and if the ASTNode contains a code snippet.
     *
     * @param nodeType The ASTNode type to search for.
     * @param code The code snippet to search for.
     * @return A string representation of the found ASTNodes and their associated FileNode.
     */
    @Tool("Find all ASTNode and their associated FileNode in the whole codebase by the " +
          "ASTNode type and if the ASTNodes contains a code snippet. For example if " +
          "the node type is \"function_definition\" and code is \"c = a + b\", we are " +
          "looking for all functions that contains that code snippet")
    public String findASTNodeByTypeAndCode(
        @P("The ASTNode type to search for") String nodeType,
        @P("The code snippet to search for") String code
    ) {
        log.info("Using tool 'findASTNodeByTypeAndCode'");
        String query = """
            MATCH (f:FileNode) -[:HAS_AST]-> (:ASTNode) -[:PARENT_OF*] -> (a:ASTNode { type: '%s'})
            WHERE a.text CONTAINS '%s'
            RETURN apoc.map.merge({id: id(f)}, f) AS FileNode, apoc.map.merge({id: id(a)}, a) AS ASTNode
            LIMIT %d
            """.formatted(nodeType, code, NEO4J_MAX_RESULT);
        List<Record> records = graph.executeRead(query);
        return formatRecords(records);
    }

    /**
     * Find all ASTNode with a certain type under a FileNode by its basename.
     * The FileNode is specified using its basename.
     *
     * @param basename The nodeId of the FileNode.
     * @param nodeType   The ASTNode type to search for.
     * @return A string representation of the found ASTNodes.
     */
    @Tool("Find all ASTNode with a certain type under a FileNode by basename. The FileNode is specified using " +
          "its basename. For example if the basename is \"foo.c\" and nodeType is \"return_statement\". " +
          "We are looking for all return statement in the file with FileNode with basename \"foo.c\". " +
          "The basename of the FileNode could also be a directory name, for example \"src\", then we are " +
          "looking for all return statements in the directory with basename \"src\"")
    public String findASTNodeByTypeAndBasename(
        @P("The basename of the FileNode") String basename,
        @P("The ASTNode type to search for") String nodeType
    ) {
        log.info("Using tool 'findASTNodeByTypeAndBasename'");
        String query = """
            MATCH (d:FileNode) -[:HAS_FILE *0..]-> (f:FileNode) -[:HAS_AST]-> (:ASTNode)-[:PARENT_OF*]->(a:ASTNode)
            WHERE d.basename = '%s' AND a.type = '%s'
            RETURN apoc.map.merge({id: id(f)}, f) AS FileNode, apoc.map.merge({id: id(a)}, a) AS ASTNode
            """.formatted(basename, nodeType);
        List<Record> records = graph.executeRead(query);
        return formatRecords(records);
    }

    /**
     * Find all ASTNode under a FileNode if they contain a code snippet.
     *
     * @param basename The basename of the FileNode.
     * @param code The code snippet to search for.
     * @return A string representation of the found ASTNodes.
     */
    @Tool("Find all ASTNode under a FileNode if they contains a code snippet. The FileNode " +
          "is specified using its basename. For example if the basename is \"foo.cpp\" and code is " +
          "\"cin >> n;\", we are looking all ASTNode with code snippet \"cin >> n;\" " +
          "in the FileNode with basename \"foo.cpp\". The basename of the FileNode could also be " +
          "a directory name, for example \"src\", then we are looking for all ASTNode with code snippet " +
          "\"cin >> n;\" in the directory with basename \"src\".")
    public String findASTNodeByCodeInFileNode(
        @P("The basename of the FileNode") String basename,
        @P("The code snippet to search for") String code
    ) {
        log.info("Using tool 'findASTNodeByCodeInFileNode'");
        // We order the result by size here, because for a small code snippet, it will
        // be matched to many ASTNode. This is because all parent ASTNode will contain
        // all the text of the child ASTNode. By sorting by size, we get the smallest
        // ASTNode that contains the code snippet.
        String query = """
            MATCH (d:FileNode) -[:HAS_FILE *0..]-> (f:FileNode) -[:HAS_AST]-> (:ASTNode)-[:PARENT_OF*]->(a:ASTNode)
            WHERE d.basename = '%s' AND a.text CONTAINS '%s'
            RETURN apoc.map.merge({id: id(f)}, f) AS FileNode, apoc.map.merge({id: id(a)}, a) AS ASTNode
            ORDER BY SIZE(a.text)
            LIMIT %d
            """.formatted(basename, code, NEO4J_MAX_RESULT);
        List<Record> records = graph.executeRead(query);
        return formatRecords(records);
    }

    @Tool("Search TextNode if they contain a text. It finds " +
          "all TextNode that contain the text therefore the text should be " +
          "keywords.")
    public String searchDocumentation(@P("The exact text to search for") String text) {
        log.info("Using tool 'searchDocumentation'");
        String query = """
            MATCH (f:FileNode) -[:HAS_TEXT]-> (t:TextNode)
            WHERE t.text CONTAINS '%s'
            RETURN apoc.map.merge({id: id(f)}, f) AS FileNode, apoc.map.merge({id: id(t)}, t) AS TextNode
            ORDER BY id(f)
            LIMIT %d
            """.formatted(text, NEO4J_MAX_RESULT);
        List<Record> records = graph.executeRead(query);
        return formatRecords(records);
    }

    @Tool("Search TextNode with a given basename if they contain a text. The text should be " +
          "keywords.")
    public String searchDocumentationInFile(
        @P("The exact text to search for") String text,
        @P("The basename of the FileNode") String basename) {
        log.info("Using tool 'searchDocumentationInFile'");
        String query = """
            MATCH (f:FileNode) -[:HAS_TEXT]-> (t:TextNode)
            WHERE f.basename = '%s' AND t.text CONTAINS '%s'
            RETURN apoc.map.merge({id: id(f)}, f) AS FileNode, apoc.map.merge({id: id(t)}, t) AS TextNode
            LIMIT %d
            """.formatted(basename, text, NEO4J_MAX_RESULT);
        List<Record> records = graph.executeRead(query);
        return formatRecords(records);
    }


    @Tool("Get the next TextNode after the TextNode with the given id. Call " +
          "this function when you want to read the next part of text that you " +
          "think will be relevant to the query")
    public String getNextTextChunk(@P("The id of the TextNode to get the next chunk") int id) {
        log.info("Using tool 'getNextTextChunk'");
        String query = """
            MATCH (a:TextNode) -[:NEXT_CHUNK]-> (b:TextNode)
            WHERE id(a) = %d
            RETURN apoc.map.merge({id: id(b)}, b) AS NextTextNode
            """.formatted(id);
        List<Record> records = graph.executeRead(query);
        return formatRecords(records);
    }

    /**
     * Preview the content of a FileNode by its basename.
     *
     * @param basename The basename of the FileNode.
     * @return The first 300 lines of the content of the FileNode.
     */
    @Tool("Preview the content of a FileNode by its basename. The FileNode is specified using its basename. " +
          "For example if the basename is \"foo.cpp\", we are previewing the first 300 lines of \"foo.cpp\". " +
          "If the file is relevant, you should use other tools to extract the particular content of the file " +
          "that you need.")
    public String previewFileContent(@P("The basename of the FileNode") String basename) {
        log.info("Using tool 'previewFileContent'");
        String codeQuery = """
            MATCH (f:FileNode { basename: '%s'}) -[:HAS_AST]-> (a:ASTNode)
            WITH f, apoc.text.split(a.text, '\\R') AS lines
            RETURN apoc.map.merge({id: id(f)}, f) AS FileNode, apoc.text.join(lines[0..300], '\\n') AS preview
            """.formatted(basename);

        String textQuery = """
            MATCH (f:FileNode { basename: '%s' }) -[:HAS_TEXT]-> (t:TextNode)
            WHERE NOT EXISTS((:TextNode) -[:NEXT_CHUNK]-> (t))
            RETURN apoc.map.merge({id: id(f)}, f) AS FileNode, t.text AS preview
            """.formatted(basename);

        List<Record> records;
        if (TreeSitterParser.supportsFile(new File(basename))) {
            records = graph.executeRead(codeQuery);
        } else {
            records = graph.executeRead(textQuery);
        }

        return formatRecords(records);
    }

    /**
     * Retrieves the parent node of a given child node based on the provided ID.
     *
     * @param id The ID of the child node.
     * @return The parent node as a string.
     */
    @Tool("Get the parent node of a given child node based on the provided ID. For example, if the ID is " +
          "1, we are looking for the parent node of the node with ID 1. This function works for both " +
          "FileNode and ASTNode.")
    public String getParent(@P("The id of the child node") long id) {
        log.info("Using tool 'getParent'");
        String query = """
            MATCH (p) -[]-> (c)
            WHERE id(c) = %d
            RETURN apoc.map.merge({id: id(p)}, p) as Parent
            """.formatted(id);
        List<Record> records = graph.executeRead(query);
        return formatRecords(records);
    }

    /**
     * Retrieves the children of a parent node based on the provided parent node ID.
     *
     * @param id The ID of the parent node.
     * @return A string representation of the children nodes.
     */
    @Tool("Get the children of a parent node based on the provided parent node ID. For example, if the ID is " +
          "1, we are looking for the children of the node with ID 1. This function works for both " +
          "FileNode and ASTNode.")
    public String getChildren(@P("The id of the parent node") long id) {
        log.info("Using tool 'getChildren'");
        // Sort the children based on the type of the child node.
        // It is more to get a consistent result for testing it.
        String query = """
            MATCH (p) -[]-> (c)
            WHERE id(p) = %d
            RETURN apoc.map.merge({id: id(c)}, c) as Children
            ORDER BY 
            CASE 
                WHEN 'FileNode' IN LABELS(c) THEN c.relativePath
                WHEN 'ASTNode' IN LABELS(c) THEN c.endLine
                WHEN 'TextNode' IN LABELS(c) THEN c.text
                ELSE NULL
            END
            """.formatted(id);
        List<Record> records = graph.executeRead(query);
        return formatRecords(records);
    }

    public void close() {
        graph.close();
    }
}
