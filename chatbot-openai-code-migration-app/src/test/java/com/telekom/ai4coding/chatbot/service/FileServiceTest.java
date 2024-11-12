package com.telekom.ai4coding.chatbot.service;

import com.telekom.ai4coding.chatbot.BaseIntegrationTest;
import com.telekom.ai4coding.chatbot.graph.FileNode;
import com.telekom.ai4coding.chatbot.graph.KnowledgeGraphBuilder;
import com.telekom.ai4coding.chatbot.repository.ConversationNodeRepository;
import com.telekom.ai4coding.chatbot.repository.FileNodeRepository;
import com.telekom.ai4coding.chatbot.repository.conversation.ConversationNode;
import com.telekom.ai4coding.openai.model.DeleteFileResponse;
import com.telekom.ai4coding.openai.model.OpenAIFile;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


class FileServiceTest extends BaseIntegrationTest {

    @TempDir
    private File tempDir;

    @MockBean
    private FileNodeRepository fileNodeRepository;

    @Autowired
    KnowledgeGraphBuilder knowledgeGraphBuilder;

    @MockBean
    ConversationNodeRepository conversationNodeRepository;

    @MockBean
    FileNode fileNode;

    @MockBean
    ConversationNode conversationNode;

    @MockBean
    EmbeddingModel embeddingModel;

    @Autowired
    private FileService fileService;

    @SneakyThrows
    @Test
    void testCreateFile_success() {
        // Prepare
        MultipartFile multipartFile = new MockMultipartFile("file", "test.txt",
                "text/plain", "test content".getBytes());

        Embedding mockEmbedding = Embedding.from(new float[]{0.5f, 0.5f});
        Response<Embedding> mockResponse = Response.from(mockEmbedding);
        when(embeddingModel.embed(anyString())).thenReturn(mockResponse);

        when(conversationNodeRepository.findById(anyString())).thenReturn(Optional.ofNullable(conversationNode));
        when(fileNodeRepository.save(any())).thenReturn(fileNode);
        when(fileNode.getId()).thenReturn(123L);

        // Do work
        ResponseEntity<OpenAIFile> response = fileService.createFile(multipartFile, "123");

        // Test
        verify(fileNodeRepository, times(1)).save(any());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @SneakyThrows
    @Test
    void testCreateFile_ioException() {
        // Prepare
        MultipartFile multipartFile = mock(MultipartFile.class);
        when(multipartFile.getOriginalFilename()).thenReturn("test.txt");
        doThrow(new IOException("Test Exception")).when(multipartFile).transferTo(any(File.class));

        // Do work
        ResponseEntity<OpenAIFile> response = fileService.createFile(multipartFile, "purpose");

        // Test
        assertEquals(ResponseEntity.status(500).build(), response);
    }

    @Test
    void testDeleteFile_success() {

        ResponseEntity<DeleteFileResponse> response = fileService.deleteFile(123L);

        verify(fileNodeRepository, times(1)).deleteFileNodeAndItsChildrenById(123L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}