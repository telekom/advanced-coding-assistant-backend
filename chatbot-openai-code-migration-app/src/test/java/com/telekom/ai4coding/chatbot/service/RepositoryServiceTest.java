package com.telekom.ai4coding.chatbot.service;

import com.telekom.ai4coding.chatbot.BaseIntegrationTest;
import com.telekom.ai4coding.chatbot.graph.KnowledgeGraphBatchInsertService;
import com.telekom.ai4coding.chatbot.graph.KnowledgeGraphBuilder;
import com.telekom.ai4coding.chatbot.repository.FileNodeRepository;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
import lombok.SneakyThrows;
import net.lingala.zip4j.ZipFile;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.ProjectApi;
import org.gitlab4j.api.RepositoryApi;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.RepositoryArchiveParams;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


class RepositoryServiceTest extends BaseIntegrationTest {

    @MockBean
    private GitLabApi gitLabApi;

    @TempDir
    private File tempDir;

    @MockBean
    private ProjectApi projectApi;

    @MockBean
    private Project project;

    @MockBean
    private FileNodeRepository fileNodeRepository;

    @MockBean
    private RepositoryApi repositoryApi;

    @MockBean
    private KnowledgeGraphBatchInsertService knowledgeGraphBatchInsertService;

    @MockBean
    EmbeddingModel embeddingModel;

    @Autowired
    KnowledgeGraphBuilder knowledgeGraphBuilder;

    @Autowired
    private RepositoryService repositoryService;

    @SneakyThrows
    @Test
    void uploadGitlabRepository() {
        Embedding mockEmbedding = Embedding.from(new float[]{0.5f, 0.5f});
        Response<Embedding> mockResponse = Response.from(mockEmbedding);
        when(embeddingModel.embed(anyString())).thenReturn(mockResponse);
        // Prepare
        String testProjectId = "123";
        when(gitLabApi.getProjectApi()).thenReturn(projectApi);
        when(projectApi.getProject(testProjectId)).thenReturn(project);
        when(gitLabApi.getRepositoryApi()).thenReturn(repositoryApi);
        when(project.getName()).thenReturn("test");

        // Generate a dummy zip file
        File file = new File(tempDir, "test.txt");
        List<String> fileContent = new ArrayList<String>();
        for(int i=0; i<100; i++) fileContent.add("Hello world\n");
        Files.write(file.toPath(), fileContent, StandardCharsets.UTF_8);
        ZipFile zipFile = new ZipFile("test.zip");
        zipFile.addFile(file);

        Path directoryPath = Paths.get("CLONE");
        when(repositoryApi.getRepositoryArchive(
                eq(project.getId()),
                any(RepositoryArchiveParams.class),
                eq(directoryPath.toFile()),
                eq("zip")
        )).thenReturn(zipFile.getFile());

        // Do work
        ResponseEntity<String> response = repositoryService.uploadGitlabRepository(testProjectId);
        
        zipFile.close();
        String[]entries = directoryPath.toFile().list();
        for(String s: entries){
            File currentFile = new File(directoryPath.toFile(), s);
            currentFile.delete();
        }
        directoryPath.toFile().delete();

        // Test
        verify(knowledgeGraphBatchInsertService, times(1)).batchInsertFileStructure(any(), anyInt());
        assertEquals("Repository processed successfully.", response.getBody());
    }

    @SneakyThrows
    @Test
    void uploadLocalRepository() {
        Embedding mockEmbedding = Embedding.from(new float[]{0.5f, 0.5f});
        Response<Embedding> mockResponse = Response.from(mockEmbedding);
        when(embeddingModel.embed(anyString())).thenReturn(mockResponse);
        // Do work
        ResponseEntity<String> response = repositoryService.uploadLocalRepository(String.valueOf(tempDir));

        // Test
        verify(knowledgeGraphBatchInsertService, times(1)).batchInsertFileStructure(any(), anyInt());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Repository processed successfully.", response.getBody());
    }


    @Test
    void uploadGitlabRepository_GitLabApiException() throws GitLabApiException {
        // Prepare
        String testProjectId = "123";
        when(gitLabApi.getProjectApi()).thenReturn(projectApi);
        when(projectApi.getProject(testProjectId)).thenThrow(new GitLabApiException("Test exception"));

        // Do work
        ResponseEntity<String> response = repositoryService.uploadGitlabRepository(testProjectId);

        // Test
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error processing GitLab repository: Test exception", response.getBody());
    }

    @Test
    void uploadLocalRepository_BadRequest() {
        // Do work
        ResponseEntity<String> response = repositoryService.uploadLocalRepository("fake/path");
        // Test
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid path: Path does not exist or is not a directory.", response.getBody());
    }

}
