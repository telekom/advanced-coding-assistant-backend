package com.telekom.ai4coding.chatbot.tools.embedding;

import com.telekom.ai4coding.chatbot.graph.ASTNode;
import com.telekom.ai4coding.chatbot.graph.TextNode;
import com.telekom.ai4coding.chatbot.utils.DoubleFormatter;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.graph.neo4j.Neo4jGraph;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * A modified version of Langchain4J Neo4jEmbeddingStore.
 * <p>
 * The main problem with Langchain4J Neo4jEmbeddingStore is that it will create
 * index and unique contraints in the contructor. But in our case, it is more than likely
 * that the embedding index has not yet been created when we initialize Neo4jEmbeddingStore.
 * Therefore in this constructor, we do nothing except for assigning the attributes.
 * This Neo4jEmbeddingStore is also only useful when you have already the embedding and index,
 * unlike the Langchain4J Neo4jEmbeddingStore.
 */
public class ACAEmbeddingStore {
    private final Neo4jGraph neo4jGraph;
    private final String astNodeIndexName;
    private final String textNodeIndexName;

    private final String QUERY_STRING = """
        CALL db.index.vector.queryNodes("%s", %d, %s)
        YIELD node, score
        WHERE score >= %s
        RETURN apoc.map.merge({id: id(node)}, node) as %s
        ORDER BY score DESC
        """;

    private ACAEmbeddingStore(Driver driver,  String astNodeIndexName, String textNodeIndexName) {
        this.neo4jGraph = new Neo4jGraph(driver);
        this.astNodeIndexName = astNodeIndexName;
        this.textNodeIndexName = textNodeIndexName;
    }

    public static ACAEmbeddingStore fromExistingIndices(Driver driver, String astNodeIndexName, String textNodeIndexName) {
       return new ACAEmbeddingStore(driver, astNodeIndexName, textNodeIndexName);
    }

    public List<ASTNode> searchASTNodes(EmbeddingSearchRequest embeddingSearchRequest) {
        float[] queryEmbedding = embeddingSearchRequest.queryEmbedding().vector();
        double minScore = embeddingSearchRequest.minScore();
        String minScoreString = DoubleFormatter.format(minScore);
        int maxResults = embeddingSearchRequest.maxResults();
        
        String query = QUERY_STRING.formatted(
          astNodeIndexName, maxResults, Arrays.toString(queryEmbedding), minScoreString, "ASTNode");

        List<Record> records = neo4jGraph.executeRead(query);
        
        List<ASTNode> result = new LinkedList<>();
        for(Record record : records) {
            if(!verifyASTNodeRecord(record)) {
              continue;
            }

            // Mapping Object to ASTNode.
            Map<String, Object> astNodeMap = record.get("ASTNode").asMap();
            Collection<Double> embeddingCollection = (Collection<Double>) astNodeMap.get("embedding");
            ASTNode astNode = new ASTNode(
                (long) astNodeMap.get("id"),
                (String) astNodeMap.get("type"),
                (String) astNodeMap.get("text"),
                (int) ((long) astNodeMap.get("startLine")),
                (int) ((long) astNodeMap.get("endLine")),
                embeddingCollection.stream().mapToDouble(Double::doubleValue).toArray(),
                null);
            result.add(astNode);
        }
        return result;
    }

    public List<TextNode> searchTextNodes(EmbeddingSearchRequest embeddingSearchRequest) {
      float[] queryEmbedding = embeddingSearchRequest.queryEmbedding().vector();
      double minScore = embeddingSearchRequest.minScore();
      int maxResults = embeddingSearchRequest.maxResults();
      
      String query = QUERY_STRING.formatted(
          textNodeIndexName, maxResults, Arrays.toString(queryEmbedding), minScore, "TextNode");

      List<Record> records = neo4jGraph.executeRead(query);
      
      List<TextNode> result = new LinkedList<>();
      for(Record record : records) {
          if(!verifyTextNodeRecord(record)) {
            continue;
          }

          // Mapping Object to TextNode.
          Map<String, Object> textNodeMap = record.get("TextNode").asMap();
          Collection<Double> embeddingCollection = (Collection<Double>) textNodeMap.get("embedding");
          TextNode textNode = new TextNode(
              (long) textNodeMap.get("id"),
              (String) textNodeMap.get("text"),
              (String) textNodeMap.get("metadata"),
              embeddingCollection.stream().mapToDouble(Double::doubleValue).toArray(),
              null);
          result.add(textNode);
      }
      return result;
    }

    private boolean verifyASTNodeRecord(Record record) {
        if(!record.containsKey("ASTNode")) {
            return false;
        }
        Map<String, Object> astNodeMap = record.get("ASTNode").asMap();
        String[] astNodeKeys = new String[]{"type", "text", "startLine", "endLine", "embedding"};
        for(String key : astNodeKeys) {
            if(!astNodeMap.containsKey(key)) {
                return false;
            }
        }
        return true;
    }

    private boolean verifyTextNodeRecord(Record record) {
      if(!record.containsKey("TextNode")) {
          return false;
      }
      Map<String, Object> textNodeMap = record.get("TextNode").asMap();
      String[] astNodeKeys = new String[]{"text", "metadata", "embedding"};
      for(String key : astNodeKeys) {
          if(!textNodeMap.containsKey(key)) {
              return false;
          }
      }
      return true;
  }
}

