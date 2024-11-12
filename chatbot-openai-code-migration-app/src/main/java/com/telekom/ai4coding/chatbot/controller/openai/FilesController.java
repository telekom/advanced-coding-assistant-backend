package com.telekom.ai4coding.chatbot.controller.openai;

import com.telekom.ai4coding.chatbot.service.FileService;
import com.telekom.ai4coding.openai.completions.FilesApi;
import com.telekom.ai4coding.openai.model.DeleteFileResponse;
import com.telekom.ai4coding.openai.model.ListFilesResponse;
import com.telekom.ai4coding.openai.model.OpenAIFile;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class FilesController implements FilesApi {

    private final FileService fileService;

    @Override
    public ResponseEntity<OpenAIFile> createFile(MultipartFile file,
                                                 @RequestParam(value = "purpose") String purpose) {
        return fileService.createFile(file, purpose);
    }

    @Override
    public ResponseEntity<DeleteFileResponse> deleteFile(@PathVariable("file_id") String fileId) {
        return fileService.deleteFile(Long.valueOf(fileId));
    }

    @Override
    public ResponseEntity<ListFilesResponse> listFiles(String purpose) {
        return fileService.getFilesByConversationId(purpose);
    }
}