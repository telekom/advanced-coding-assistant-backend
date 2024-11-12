package com.telekom.ai4coding.chatbot.graph;

import lombok.RequiredArgsConstructor;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.TransactionContext;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * The BatchInsertService class provides efficient bulk insertion capabilities for a graph structure
 * consisting of FileNodes, ASTNodes, and TextNodes into a Neo4j database.
 *
 * This service is designed to handle large-scale insertions by using batch operations.
 *
 * Key features:
 * 1. Bulk insertion of FileNodes, ASTNodes, and TextNodes
 * 2. Creation of relationships between nodes:
 *    - HAS_FILE: between parent and child FileNodes
 *    - HAS_AST: between FileNodes and their root ASTNodes
 *    - PARENT_OF: between parent and child ASTNodes
 *    - HAS_TEXT: between FileNodes and their TextNodes
 *    - NEXT_CHUNK: between consecutive TextNodes
 * 3. Efficient handling of large datasets through batching
 * 4. Transactional execution to ensure data consistency
 * 5. Creating Embedding indicies for ASTNode and TextNodes
 *    if they do not exist.
 *
 * The main entry point is the batchInsertFileStructure method, which takes a root FileNode
 * representing the top of the file structure to be inserted.
 *
 * Note: This service uses the Neo4j Java Driver directly for more control over the transactions
 * and to optimize the insertion process. It's designed to handle very large structures efficiently
 * while preserving all the relationships between nodes.
 *
 * Performance considerations:
 * - The BATCH_SIZE constant can be adjusted based on your specific data and system resources.
 *
 * @see FileNode
 * @see ASTNode
 * @see TextNode
 */
@RequiredArgsConstructor
@Service
public class KnowledgeGraphBatchInsertService {

    private static final int BATCH_SIZE = 1000;

    private final Driver neo4jDriver;

    public void batchInsertFileStructure(FileNode rootNode, int embeddingDimension) {
        // Schema modification session
        try (Session session = neo4jDriver.session()) {
            session.executeWrite(tx -> {
                createEmbeddingIndex(tx, embeddingDimension);

                return null;
            });
        }
        // Node and relationship writing session
        try (Session session = neo4jDriver.session()) {
            session.executeWrite(tx -> {
                Map<String, Long> pathToId = new HashMap<>();
                Map<ASTNode, Long> astNodeToId = new HashMap<>();
                Map<TextNode, Long> textNodeToId = new HashMap<>();
                List<FileNode> fileNodes = flattenFileStructure(rootNode);

                // Insert FileNodes
                for (int i = 0; i < fileNodes.size(); i += BATCH_SIZE) {
                    List<FileNode> batch = fileNodes.subList(i, Math.min(fileNodes.size(), i + BATCH_SIZE));
                    batchInsertFileNodes(tx, batch, pathToId);
                }

                // Insert ASTNodes
                List<ASTNode> astNodes = collectAllASTNodes(rootNode);
                for (int i = 0; i < astNodes.size(); i += BATCH_SIZE) {
                    List<ASTNode> batch = astNodes.subList(i, Math.min(astNodes.size(), i + BATCH_SIZE));
                    batchInsertASTNodes(tx, batch, astNodeToId);
                }

                // Insert TextNodes
                List<TextNode> textNodes = collectAllTextNodes(rootNode);
                for (int i = 0; i < textNodes.size(); i += BATCH_SIZE) {
                    List<TextNode> batch = textNodes.subList(i, Math.min(textNodes.size(), i + BATCH_SIZE));
                    batchInsertTextNodes(tx, batch, textNodeToId);
                }

                // Create FileNode 'HAS_FILE' relationships
                createFileNodeRelationships(tx, rootNode, pathToId);

                // Create FileNode to ASTNode 'HAS_AST' relationships
                createFileNodeASTNodeRelationships(tx, rootNode, pathToId, astNodeToId);

                // Create 'PARENT_OF' relationships between ASTNodes
                createASTNodeParentOfRelationships(tx, astNodes, astNodeToId);

                // Create 'HAS_TEXT' relationships between FileNodes and TextNodes
                createFileNodeTextNodeRelationships(tx, rootNode, pathToId, textNodeToId);

                // Create 'NEXT_CHUNK' relationships between TextNodes
                createTextNodeNextChunkRelationships(tx, textNodes, textNodeToId);

                return null;
            });
        }
    }

    private  void createEmbeddingIndex(TransactionContext tx, int embeddingDimension) {
        String dropAstNodeEmbeddingIndex = "DROP INDEX ASTNodeEmbedding IF EXISTS";
        tx.run(dropAstNodeEmbeddingIndex);

        String astNodeEmbeddingIndexQuery = """
            CREATE VECTOR INDEX ASTNodeEmbedding IF NOT EXISTS
            FOR (n:ASTNode)
            ON n.embedding
            OPTIONS {indexConfig: {
            `vector.dimensions`: %d,
            `vector.similarity_function`: 'cosine'
            }}
            """.formatted(embeddingDimension);
        tx.run(astNodeEmbeddingIndexQuery);

        String dropTextNodeEmbeddingIndex = "DROP INDEX TextNodeEmbedding IF EXISTS";
        tx.run(dropTextNodeEmbeddingIndex);
        String textNodeEmbeddingIndexQuery = """
            CREATE VECTOR INDEX TextNodeEmbedding IF NOT EXISTS
            FOR (n:TextNode)
            ON n.embedding
            OPTIONS {indexConfig: {
            `vector.dimensions`: %d,
            `vector.similarity_function`: 'cosine'
            }}
            """.formatted(embeddingDimension);
        tx.run(textNodeEmbeddingIndexQuery);
    }

    private void batchInsertFileNodes(TransactionContext tx, List<FileNode> fileNodes, Map<String, Long> pathToId) {
        String query = "UNWIND $nodes AS node " +
                "CREATE (f:FileNode) " +
                "SET f = node " +
                "RETURN id(f) AS id, node.relativePath AS relativePath";

        List<Map<String, Object>> params = new ArrayList<>();
        for (FileNode node : fileNodes) {
            Map<String, Object> nodeMap = new HashMap<>();
            nodeMap.put("basename", node.getBasename());
            nodeMap.put("relativePath", node.getRelativePath());
            params.add(nodeMap);
        }

        tx.run(query, Map.of("nodes", params))
                .forEachRemaining(record -> pathToId.put(record.get("relativePath").asString(),
                        record.get("id").asLong()));
    }

    private void createFileNodeRelationships(TransactionContext tx, FileNode rootNode, Map<String, Long> pathToId) {
        String query = "UNWIND $relationships AS rel " +
                "MATCH (parent:FileNode), (child:FileNode) " +
                "WHERE id(parent) = rel.parentId AND id(child) = rel.childId " +
                "CREATE (parent)-[:HAS_FILE]->(child)";

        List<Map<String, Object>> relationships = new ArrayList<>();
        collectFileRelationships(rootNode, pathToId, relationships);

        tx.run(query, Map.of("relationships", relationships));
    }

    private List<FileNode> flattenFileStructure(FileNode node) {
        List<FileNode> result = new ArrayList<>();
        Queue<FileNode> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            FileNode current = queue.poll();
            result.add(current);
            if (current.getChildFileNodes() != null) {
                queue.addAll(current.getChildFileNodes());
            }
        }

        return result;
    }

    private List<ASTNode> collectAllASTNodes(FileNode rootNode) {
        List<ASTNode> result = new ArrayList<>();
        Queue<FileNode> fileQueue = new LinkedList<>();
        fileQueue.offer(rootNode);

        while (!fileQueue.isEmpty()) {
            FileNode currentFile = fileQueue.poll();
            if (currentFile.getAstNodes() != null) {
                for (ASTNode astNode : currentFile.getAstNodes()) {
                    result.addAll(flattenASTStructure(astNode));
                }
            }
            if (currentFile.getChildFileNodes() != null) {
                fileQueue.addAll(currentFile.getChildFileNodes());
            }
        }

        return result;
    }

    private List<ASTNode> flattenASTStructure(ASTNode node) {
        List<ASTNode> result = new ArrayList<>();
        Queue<ASTNode> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            ASTNode current = queue.poll();
            result.add(current);
            if (current.getChildASTNodes() != null) {
                queue.addAll(current.getChildASTNodes());
            }
        }

        return result;
    }

    private void collectFileRelationships(FileNode node,
                                          Map<String, Long> pathToId,
                                          List<Map<String, Object>> relationships) {
        Long parentId = pathToId.get(node.getRelativePath());
        if (node.getChildFileNodes() != null) {
            for (FileNode child : node.getChildFileNodes()) {
                Long childId = pathToId.get(child.getRelativePath());
                relationships.add(Map.of("parentId", parentId, "childId", childId));
                collectFileRelationships(child, pathToId, relationships);
            }
        }
    }

    private void batchInsertASTNodes(TransactionContext tx, List<ASTNode> astNodes, Map<ASTNode, Long> astNodeToId) {
        String query = "UNWIND $nodes AS node " +
                "CREATE (a:ASTNode) " +
                "SET a.type = node.type, a.text = node.text, a.startLine = node.startLine, a.endLine = node.endLine, a.embedding = node.embedding " +
                "RETURN id(a) AS id, node.index AS index";

        List<Map<String, Object>> params = new ArrayList<>();
        for (int i = 0; i < astNodes.size(); i++) {
            ASTNode node = astNodes.get(i);
            Map<String, Object> nodeMap = new HashMap<>();
            nodeMap.put("type", node.getType());
            nodeMap.put("text", node.getText());
            nodeMap.put("startLine", node.getStartLine());
            nodeMap.put("endLine", node.getEndLine());
            nodeMap.put("embedding", node.getEmbedding());
            nodeMap.put("index", i);
            params.add(nodeMap);
        }

        tx.run(query, Map.of("nodes", params))
                .forEachRemaining(record -> astNodeToId.put(astNodes.get(record.get("index").asInt()),
                        record.get("id").asLong()));
    }

    private void createFileNodeASTNodeRelationships(TransactionContext tx,
                                                    FileNode rootNode,
                                                    Map<String, Long> pathToId,
                                                    Map<ASTNode, Long> astNodeToId) {
        String query = "UNWIND $relationships AS rel " +
                "MATCH (file:FileNode), (ast:ASTNode) " +
                "WHERE id(file) = rel.fileId AND id(ast) = rel.astId " +
                "CREATE (file)-[:HAS_AST]->(ast)";

        List<Map<String, Object>> relationships = new ArrayList<>();
        collectASTRelationships(rootNode, pathToId, astNodeToId, relationships);

        tx.run(query, Map.of("relationships", relationships));
    }

    private void collectASTRelationships(FileNode fileNode,
                                         Map<String, Long> pathToId,
                                         Map<ASTNode, Long> astNodeToId,
                                         List<Map<String, Object>> relationships) {
        Long fileId = pathToId.get(fileNode.getRelativePath());
        if (fileNode.getAstNodes() != null) {
            for (ASTNode astNode : fileNode.getAstNodes()) {
                Long astId = astNodeToId.get(astNode);
                if (astId != null) {
                    relationships.add(Map.of("fileId", fileId, "astId", astId));
                }
            }
        }
        if (fileNode.getChildFileNodes() != null) {
            for (FileNode child : fileNode.getChildFileNodes()) {
                collectASTRelationships(child, pathToId, astNodeToId, relationships);
            }
        }
    }

    private void createASTNodeParentOfRelationships(TransactionContext tx,
                                                    List<ASTNode> astNodes,
                                                    Map<ASTNode, Long> astNodeToId) {
        StringBuilder query = new StringBuilder("UNWIND $relationships AS rel ")
                .append("MATCH (parent:ASTNode), (child:ASTNode) ")
                .append("WHERE id(parent) = rel.parentId AND id(child) = rel.childId ")
                .append("CREATE (parent)-[:PARENT_OF]->(child)");

        List<Map<String, Object>> relationships = new ArrayList<>();
        for (ASTNode node : astNodes) {
            Long parentId = astNodeToId.get(node);
            if (node.getChildASTNodes() != null) {
                for (ASTNode child : node.getChildASTNodes()) {
                    Long childId = astNodeToId.get(child);
                    if (parentId != null && childId != null) {
                        relationships.add(Map.of("parentId", parentId, "childId", childId));
                    }
                }
            }
        }

        for (int i = 0; i < relationships.size(); i += BATCH_SIZE) {
            List<Map<String, Object>> batch = relationships.subList(i, Math.min(relationships.size(), i + BATCH_SIZE));
            tx.run(query.toString(), Map.of("relationships", batch));
        }
    }

    private List<TextNode> collectAllTextNodes(FileNode rootNode) {
        List<TextNode> result = new ArrayList<>();
        Queue<FileNode> fileQueue = new LinkedList<>();
        fileQueue.offer(rootNode);

        while (!fileQueue.isEmpty()) {
            FileNode currentFile = fileQueue.poll();
            if (currentFile.getTextNodes() != null) {
                result.addAll(currentFile.getTextNodes());
            }
            if (currentFile.getChildFileNodes() != null) {
                fileQueue.addAll(currentFile.getChildFileNodes());
            }
        }

        return result;
    }

    private void batchInsertTextNodes(TransactionContext tx,
                                      List<TextNode> textNodes,
                                      Map<TextNode, Long> textNodeToId) {
        String query = "UNWIND $nodes AS node " +
                "CREATE (t:TextNode) " +
                "SET t.text = node.text, t.metadata = node.metadata, t.embedding = node.embedding " +
                "RETURN id(t) AS id, node.index AS index";

        List<Map<String, Object>> params = new ArrayList<>();
        for (int i = 0; i < textNodes.size(); i++) {
            TextNode node = textNodes.get(i);
            Map<String, Object> nodeMap = new HashMap<>();
            nodeMap.put("text", node.getText());
            nodeMap.put("metadata", node.getMetadata());
            nodeMap.put("embedding", node.getEmbedding());
            nodeMap.put("index", i);
            params.add(nodeMap);
        }

        tx.run(query, Map.of("nodes", params))
                .forEachRemaining(record -> textNodeToId.put(textNodes.get(record.get("index").asInt()),
                        record.get("id").asLong()));
    }

    private void createFileNodeTextNodeRelationships(TransactionContext tx,
                                                     FileNode rootNode,
                                                     Map<String, Long> pathToId,
                                                     Map<TextNode, Long> textNodeToId) {
        String query = "UNWIND $relationships AS rel " +
                "MATCH (file:FileNode), (text:TextNode) " +
                "WHERE id(file) = rel.fileId AND id(text) = rel.textId " +
                "CREATE (file)-[:HAS_TEXT]->(text)";

        List<Map<String, Object>> relationships = new ArrayList<>();
        collectTextRelationships(rootNode, pathToId, textNodeToId, relationships);

        tx.run(query, Map.of("relationships", relationships));
    }

    private void collectTextRelationships(FileNode fileNode,
                                          Map<String, Long> pathToId,
                                          Map<TextNode, Long> textNodeToId,
                                          List<Map<String, Object>> relationships) {
        Long fileId = pathToId.get(fileNode.getRelativePath());
        if (fileNode.getTextNodes() != null) {
            for (TextNode textNode : fileNode.getTextNodes()) {
                Long textId = textNodeToId.get(textNode);
                if (textId != null) {
                    relationships.add(Map.of("fileId", fileId, "textId", textId));
                }
            }
        }
        if (fileNode.getChildFileNodes() != null) {
            for (FileNode child : fileNode.getChildFileNodes()) {
                collectTextRelationships(child, pathToId, textNodeToId, relationships);
            }
        }
    }

    private void createTextNodeNextChunkRelationships(TransactionContext tx,
                                                      List<TextNode> textNodes,
                                                      Map<TextNode, Long> textNodeToId) {
        String query = "UNWIND $relationships AS rel " +
                "MATCH (current:TextNode), (next:TextNode) " +
                "WHERE id(current) = rel.currentId AND id(next) = rel.nextId " +
                "CREATE (current)-[:NEXT_CHUNK]->(next)";

        List<Map<String, Object>> relationships = new ArrayList<>();
        for (TextNode node : textNodes) {
            Long currentId = textNodeToId.get(node);
            if (node.getNextTextNode() != null) {
                Long nextId = textNodeToId.get(node.getNextTextNode());
                if (currentId != null && nextId != null) {
                    relationships.add(Map.of("currentId", currentId, "nextId", nextId));
                }
            }
        }

        for (int i = 0; i < relationships.size(); i += BATCH_SIZE) {
            List<Map<String, Object>> batch = relationships.subList(i, Math.min(relationships.size(), i + BATCH_SIZE));
            tx.run(query, Map.of("relationships", batch));
        }
    }
}