package com.telekom.ai4coding.chatbot.controller.openai;

import com.telekom.ai4coding.chatbot.service.Neo4JDatabaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = Neo4JDatabaseController.class)
public class Neo4JDatabaseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Neo4JDatabaseService neo4JDatabaseService;

    @BeforeEach
    void setUp() {
        // Reset mock before each test case to avoid side effects between tests
        Mockito.reset(neo4JDatabaseService);
    }

    @Test
    void givenValidRequest_whenCleanupDatabase_thenReturnStatusOk() throws Exception {
        // Given: No specific setup is needed here as the service will succeed

        // When: Performing the DELETE request to the /cleanup endpoint
        mockMvc.perform(delete("/v1/graphdb/cleanup")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Then: Verify that the service method was called exactly once
        verify(neo4JDatabaseService, times(1)).cleanupDatabase();
    }

    @Test
    void givenServiceThrowsException_whenCleanupDatabase_thenReturnServerError() throws Exception {
        // Given: The service throws a RuntimeException when cleanupDatabase is called
        Mockito.doThrow(new RuntimeException("Database error")).when(neo4JDatabaseService).cleanupDatabase();

        // When: Performing the DELETE request to the /cleanup endpoint
        mockMvc.perform(delete("/v1/graphdb/cleanup")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());

        // Then: Verify that the service method was still called exactly once
        verify(neo4JDatabaseService, times(1)).cleanupDatabase();
    }
}
