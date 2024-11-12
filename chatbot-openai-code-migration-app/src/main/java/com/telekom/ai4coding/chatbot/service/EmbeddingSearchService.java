package com.telekom.ai4coding.chatbot.service;

import java.util.List;
import java.util.stream.Collectors;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.stereotype.Service;

import com.telekom.ai4coding.chatbot.configuration.agent.CodeContextVerifyAgent;
import com.telekom.ai4coding.chatbot.configuration.agent.HypotheticalDocumentGenerator;
import com.telekom.ai4coding.chatbot.graph.ASTNode;
import com.telekom.ai4coding.chatbot.graph.TextNode;
import com.telekom.ai4coding.chatbot.tools.embedding.ACAEmbeddingStore;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * A service that retrieves the most similar ASTNode and TextNode given a
 * user query.
 * <p>
 * The embedding search is done on the knowledge graph. We assume here that
 * we have already created a ASTNodeEmbedding and TextNodeEmbedding embedding
 * index. The retrieval process follows a HyDE method. It means that we will
 * generate a hypothetical answer the user query, and use that hypothetical answer
 * to search for relevant nodes.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EmbeddingSearchService {

    private final HypotheticalDocumentGenerator hypotheticalDocumentGenerator;
    private final CodeContextVerifyAgent codeContextVerifyAgent;
    private final EmbeddingModel embeddingModel;
    private final int EMBEDDING_MAX_RESULT = 3;
    private final double MIN_SCORE = 0.7;
    private final Neo4jProperties neo4jProperties;

    private ACAEmbeddingStore embeddingStore;

    @PostConstruct
    private void initializeEmbeddingStore() {
        Driver neo4jDriver = GraphDatabase.driver(
              neo4jProperties.getUri().toString(),
              AuthTokens.basic(
                  neo4jProperties.getAuthentication().getUsername(),
                  neo4jProperties.getAuthentication().getPassword()));
        this.embeddingStore = ACAEmbeddingStore.fromExistingIndices(
              neo4jDriver, "ASTNodeEmbedding", "TextNodeEmbedding");
      
    }

    public String getContextUsingEmbedding(String query) {
        String context = "";
        
        context = context + getCodeContext(query) + System.getProperty("line.separator");

        context = context + getDocContext(query) + System.getProperty("line.separator");

        return context.strip();
    }

    private String getCodeContext(String query) {
        String hypotheticalCodeContext = hypotheticalDocumentGenerator.getFakeCodeSnippet(query);
        log.debug("Generated hypothetical code \"{}\" for query \"{}\"", hypotheticalCodeContext, query);

        Embedding queryEmbedding = this.embeddingModel.embed(hypotheticalCodeContext).content();
        EmbeddingSearchRequest embeddingSearchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(queryEmbedding)
                .maxResults(EMBEDDING_MAX_RESULT)
                .minScore(MIN_SCORE)
                .build();

        List<ASTNode> searchResult = embeddingStore.searchASTNodes(embeddingSearchRequest);

        List<ASTNode> filteredSearchResult = searchResult.stream()
                .filter(node -> {
                    boolean isRelevant = codeContextVerifyAgent.isRelevant(query, node.getText());
                    log.debug("\"{}\" is deemed irrelevant by codeContextVerifyAgent", node.getText());
                    return isRelevant;
                })
                .collect(Collectors.toList());

        if(filteredSearchResult.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < filteredSearchResult.size(); i++) {
            sb.append("Related ASTNode " + Integer.toString(i+1) + ": ");
            sb.append(System.getProperty("line.separator"));
            sb.append("{");
            sb.append("id: " + Long.toString(filteredSearchResult.get(i).getId()) + ", ");
            sb.append("type: " + "\"" + filteredSearchResult.get(i).getType() + "\"" + ", ");
            sb.append("text: " + "\"" + filteredSearchResult.get(i).getText() + "\"" + ", ");
            sb.append("startLine: " + Integer.toString(filteredSearchResult.get(i).getStartLine()) + "\"" + ", ");
            sb.append("endLine: " + Integer.toString(filteredSearchResult.get(i).getStartLine()));
            sb.append("}");
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

    private String getDocContext(String query) {
      String hypotheticalDocumentationContext = hypotheticalDocumentGenerator.getFakeCodeDocumentation(query);
      log.debug("Generated hypothetical doc \"{}\" for query \"{}\"", hypotheticalDocumentationContext, query);

      Embedding queryEmbedding = this.embeddingModel.embed(hypotheticalDocumentationContext).content();
      EmbeddingSearchRequest embeddingSearchRequest = EmbeddingSearchRequest.builder()
              .queryEmbedding(queryEmbedding)
              .maxResults(EMBEDDING_MAX_RESULT)
              .minScore(MIN_SCORE)
              .build();

      List<TextNode> searchResult = embeddingStore.searchTextNodes(embeddingSearchRequest);

      if(searchResult.isEmpty()) {
          return "";
      }

      StringBuilder sb = new StringBuilder();
      for(int i = 0; i < searchResult.size(); i++) {
          sb.append("Related TextNode " + Integer.toString(i+1) + ": ");
          sb.append(System.getProperty("line.separator"));
          sb.append("{");
          sb.append("id: " + Long.toString(searchResult.get(i).getId()) + ", ");
          sb.append("text: " + "\"" + searchResult.get(i).getText() + "\"" + ", ");
          sb.append("metadata: " + searchResult.get(i).getMetadata() + "\"" + ", ");
          sb.append("}");
          sb.append(System.getProperty("line.separator"));
      }
      return sb.toString();
  }
  
}
