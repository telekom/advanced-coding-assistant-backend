package com.telekom.ai4coding.chatbot;

import dev.langchain4j.store.graph.neo4j.Neo4jGraph;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.containers.Neo4jLabsPlugin;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@DirtiesContext
public class BaseIntegrationTest {

    @MockBean
    private Neo4jGraph neo4jGraph;
    @Container
    private static Neo4jContainer<?> neo4jContainer = new Neo4jContainer<>("neo4j:5.20.0")
            .withLabsPlugins(Neo4jLabsPlugin.APOC);

    @BeforeAll
    static void startContainer() {
        neo4jContainer.start();
    }

    @AfterAll
    static void stopContainer() {
        neo4jContainer.stop();
    }

    @DynamicPropertySource
    static void neo4jProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.neo4j.uri", () -> neo4jContainer.getBoltUrl());
        registry.add("spring.neo4j.authentication.username", () -> "neo4j");
        registry.add("spring.neo4j.authentication.password", () -> neo4jContainer.getAdminPassword());
    }

}
