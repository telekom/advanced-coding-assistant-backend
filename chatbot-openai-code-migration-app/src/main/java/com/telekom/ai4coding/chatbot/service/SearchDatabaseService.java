package com.telekom.ai4coding.chatbot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.telekom.ai4coding.chatbot.template.cypher.StateMachineRetrievalCypherTemplates.AST_KEYWORD_SEARCH_CYPHER;
import static com.telekom.ai4coding.chatbot.template.cypher.StateMachineRetrievalCypherTemplates.TEXT_KEYWORD_SEARCH_CYPHER;

@Slf4j
@RequiredArgsConstructor
@Service
public class SearchDatabaseService {

    private final Driver neo4jDriver;

    /**
     * Executes both AST and text-based keyword searches in the Neo4j database.
     *
     * @param keywords An array of keywords to search for in the database.
     * @return A map where keys are file paths and values are the corresponding file contents.
     *         The results are shuffled to increase entropy and ensure diverse results.
     */
    public Map<String, String> executeAstAndTextKeywordSearch(String[] keywords) {
        try (Session session = neo4jDriver.session()) {
            return session.executeRead(context -> {

                Map<String, String> astResults = context.run(AST_KEYWORD_SEARCH_CYPHER, Map.of("keywords", keywords))
                        .stream()
                        .collect(Collectors.toMap(
                                record -> record.get("FilePath").asString(),
                                record -> record.get("FileContent").asString(),
                                (fileContent1, fileContent2)
                                        -> fileContent1.equals(fileContent2) ? fileContent1 : fileContent1 + " \n" + fileContent2
                        ));

                Map<String, String> textResults = context.run(TEXT_KEYWORD_SEARCH_CYPHER, Map.of("keywords", keywords))
                        .stream()
                        .collect(Collectors.toMap(
                                record -> record.get("FilePath").asString(),
                                record -> record.get("FileContent").asString(),
                                (fileContent1, fileContent2)
                                        -> fileContent1.equals(fileContent2) ? fileContent1 : fileContent1 + " \n" + fileContent2
                        ));

                // Shuffle the results to increase entropy in retrieved queries, thereby enhancing
                // randomness and ensuring users are more likely to receive diverse results
                // even when submitting similar queries
                List<Map.Entry<String, String>> entryListToShuffle = new ArrayList<>();
                entryListToShuffle.addAll(astResults.entrySet());
                entryListToShuffle.addAll(textResults.entrySet());
                Collections.shuffle(entryListToShuffle);
                Map<String, String> shuffledAstResults = entryListToShuffle.stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (v1, v2) -> v1,
                                LinkedHashMap::new
                        ));

                return shuffledAstResults;
            });
        }
    }

    /**
     * Executes a custom Cypher query on the Neo4j database.
     *
     * @param cypherQuery The Cypher query to execute.
     * @return A string representation of the query results, with records separated by ";\n---".
     *         If an error occurs returns an empty string and the RuntimeException.
     */
    public Pair<String, RuntimeException> executeCypherQuery(String cypherQuery) {
        try (Session session = neo4jDriver.session()) {
            return session.executeRead(tx -> {
                var result = tx.run(cypherQuery);
                return Pair.of(
                        result.stream()
                        .map(this::formatRecord)
                        .collect(Collectors.joining(";\n---")),
                        null);
            });
        } catch (RuntimeException e) {
            log.warn("LLM probably produced an incorrect query. Generated query: \n{} Error: {}",
                    cypherQuery, e.getMessage());
            return Pair.of("", e);
        }
    }

    /**
     * Formats a Neo4j record into a string representation.
     *
     * @param record The Neo4j record to format.
     * @return A string representation of the record, with each field on a new line.
     */
    private String formatRecord(org.neo4j.driver.Record record) {
        return record.fields().stream()
                .map(pair -> pair.key() + ": " + pair.value())
                .collect(Collectors.joining("\n"));
    }
}
