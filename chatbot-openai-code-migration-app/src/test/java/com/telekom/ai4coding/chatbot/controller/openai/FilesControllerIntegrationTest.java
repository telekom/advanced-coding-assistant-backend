package com.telekom.ai4coding.chatbot.controller.openai;

import com.telekom.ai4coding.chatbot.service.FileService;
import com.telekom.ai4coding.openai.model.DeleteFileResponse;
import com.telekom.ai4coding.openai.model.ListFilesResponse;
import com.telekom.ai4coding.openai.model.OpenAIFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = FilesController.class)
public class FilesControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileService fileService;

    @BeforeEach
    void setUp() {
        // Set up mock responses for file service methods
        OpenAIFile openAIFile = new OpenAIFile();
        openAIFile.setId("file-123");

        Mockito.when(fileService.createFile(any(), anyString()))
                .thenReturn(ResponseEntity.ok(openAIFile));

        DeleteFileResponse deleteFileResponse = new DeleteFileResponse();
        deleteFileResponse.setId("file-123");
        deleteFileResponse.setDeleted(true);

        Mockito.when(fileService.deleteFile(anyLong()))
                .thenReturn(ResponseEntity.ok(deleteFileResponse));

        ListFilesResponse listFilesResponse = new ListFilesResponse();
        listFilesResponse.setData(Collections.emptyList());

        Mockito.when(fileService.getFilesByConversationId(anyString()))
                .thenReturn(ResponseEntity.ok(listFilesResponse));
    }

    @Test
    void testCreateFile() throws Exception {
        // GIVEN: A valid MultipartFile and purpose
        MockMultipartFile mockFile = new MockMultipartFile("file", "test-file.txt",
                MediaType.TEXT_PLAIN_VALUE, "file content".getBytes());

        // WHEN: Sending a POST request to "/v1/files"
        mockMvc.perform(multipart("/v1/files")
                        .file(mockFile)
                        .param("purpose", "testpurpose")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                // THEN: The status is OK and the response contains the created file details
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("file-123"));
    }

    @Test
    void testDeleteFile() throws Exception {
        // GIVEN: A valid file ID
        String fileId = "123";

        // WHEN: Sending a DELETE request to "/v1/files/{fileId}"
        mockMvc.perform(delete("/v1/files/{file_id}", fileId)
                        .accept(MediaType.APPLICATION_JSON))
                // THEN: The status is OK and the response contains the deletion status
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("file-123"))
                .andExpect(jsonPath("$.deleted").value(true));
    }

    @Test
    void testListFiles() throws Exception {
        // GIVEN: A valid conversation ID
        String purpose = "testpurpose";

        // WHEN: Sending a GET request to "/v1/files"
        mockMvc.perform(get("/v1/files")
                        .param("purpose", purpose)
                        .accept(MediaType.APPLICATION_JSON))
                // THEN: The status is OK and the response contains an empty list of files
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isEmpty());
    }
}

