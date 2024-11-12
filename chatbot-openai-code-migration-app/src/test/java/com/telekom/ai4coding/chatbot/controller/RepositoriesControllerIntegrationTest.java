package com.telekom.ai4coding.chatbot.controller;

import com.telekom.ai4coding.chatbot.service.RepositoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = RepositoriesController.class)
public class RepositoriesControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepositoryService repositoryService;

    @BeforeEach
    void setUp() {
        // Response Strings used are the ones specified in RepositoryService.java even if the Service is only mocked here!
        // Set up mock responses for the repository service
        Mockito.when(repositoryService.uploadGitlabRepository(anyString()))
                .thenReturn(ResponseEntity.ok("Repository processed successfully."));

        Mockito.when(repositoryService.uploadLocalRepository(anyString()))
                .thenReturn(ResponseEntity.ok("Repository processed successfully."));

        Mockito.when(repositoryService.refreshGitlabRepository(anyString()))
                .thenReturn(ResponseEntity.ok("Repository refreshed successfully."));

        Mockito.when(repositoryService.refreshLocalRepository(anyString()))
                .thenReturn(ResponseEntity.ok("Repository refreshed successfully."));
    }

    @Test
    void testUploadGitlabRepository() throws Exception {
        // GIVEN: A valid GitLab project ID "12345"
        String projectId = "12345";

        // WHEN: Sending a POST request to "/v1/repositories/gitlab/{projectId}"
        mockMvc.perform(post("/v1/repositories/gitlab/{projectId}", projectId)
                        .accept(MediaType.APPLICATION_JSON))
                // THEN: The status is OK and the response contains the success message
                .andExpect(status().isOk())
                .andExpect(content().string("Repository processed successfully."));
    }

    @Test
    void testUploadLocalRepository() throws Exception {
        // GIVEN: A valid local repository path
        String localPath = "/path/to/local/repository";

        // WHEN: Sending a POST request to "/v1/repositories/local" with plain text
        mockMvc.perform(post("/v1/repositories/local")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(localPath)
                        .accept(MediaType.APPLICATION_JSON))
                // THEN: The status is OK and the response contains the success message
                .andExpect(status().isOk())
                .andExpect(content().string("Repository processed successfully."));
    }

    @Test
    void testRefreshGitlabRepository() throws Exception {
        // GIVEN: A valid GitLab project ID "12345"
        String projectId = "12345";

        // WHEN: Sending a PUT request to "/v1/repositories/gitlab/{projectId}/refresh"
        mockMvc.perform(put("/v1/repositories/gitlab/{projectId}/refresh", projectId)
                        .accept(MediaType.APPLICATION_JSON))
                // THEN: The status is OK and the response contains the success message
                .andExpect(status().isOk())
                .andExpect(content().string("Repository refreshed successfully."));
    }

    @Test
    void testRefreshLocalRepository() throws Exception {
        // GIVEN: A valid local repository path
        String localPath = "/path/to/local/repository";

        // WHEN: Sending a PUT request to "/v1/repositories/local/refresh" with plain text
        mockMvc.perform(put("/v1/repositories/local/refresh")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(localPath)
                        .accept(MediaType.APPLICATION_JSON))
                // THEN: The status is OK and the response contains the success message
                .andExpect(status().isOk())
                .andExpect(content().string("Repository refreshed successfully."));
    }
}
