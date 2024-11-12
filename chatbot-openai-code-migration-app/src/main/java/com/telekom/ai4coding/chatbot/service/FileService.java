package com.telekom.ai4coding.chatbot.service;

import com.telekom.ai4coding.chatbot.graph.FileNode;
import com.telekom.ai4coding.chatbot.graph.KnowledgeGraph;
import com.telekom.ai4coding.chatbot.graph.KnowledgeGraphBuilder;
import com.telekom.ai4coding.chatbot.repository.ConversationNodeRepository;
import com.telekom.ai4coding.chatbot.repository.FileNodeRepository;
import com.telekom.ai4coding.chatbot.repository.conversation.ConversationNode;
import com.telekom.ai4coding.openai.model.DeleteFileResponse;
import com.telekom.ai4coding.openai.model.ListFilesResponse;
import com.telekom.ai4coding.openai.model.OpenAIFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileService {

    private final FileNodeRepository fileNodeRepository;
    private final KnowledgeGraphBuilder knowledgeGraphBuilder;
    private final ConversationNodeRepository conversationNodeRepository;

    public ResponseEntity<OpenAIFile> createFile(MultipartFile file, String purpose) {
        try {

            Path tempdir= Files.createTempDirectory("tempdir");
            File newFile=new File(tempdir.toString(), Objects.requireNonNull(file.getOriginalFilename()));
            file.transferTo(newFile);
            ConversationNode conversationNode = conversationNodeRepository.findById(purpose)
                    .orElseThrow(() -> new IllegalArgumentException("Conversation with purpose " + purpose + " not found"));

            KnowledgeGraph knowledgeGraph = knowledgeGraphBuilder.buildGraphFromDir(newFile,conversationNode);
            FileNode fileNode =fileNodeRepository.save(knowledgeGraph.getRootFileNode());

            OpenAIFile openAIFile = new OpenAIFile();
            openAIFile.id(fileNode.getId().toString());
            openAIFile.filename(fileNode.getBasename());

            return ResponseEntity.ok(openAIFile);
        } catch (IOException e) {
            log.error("Error while creating file", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    public ResponseEntity<ListFilesResponse> getFilesByConversationId(String conversationId) {
        Optional<List<FileNode>> files = fileNodeRepository.getFileNodeByConversationNodeId(conversationId);
        if (files.isPresent()) {
            List<OpenAIFile> openAIFiles = files.get().stream().map(fileNode -> {
                OpenAIFile openAIFile = new OpenAIFile();
                openAIFile.id(fileNode.getId().toString());
                openAIFile.filename(fileNode.getBasename());
                return openAIFile;
            }).toList();
            ListFilesResponse listFilesResponse = new ListFilesResponse();
            listFilesResponse.setData(openAIFiles);
            listFilesResponse.setObject(ListFilesResponse.ObjectEnum.LIST);
            return ResponseEntity.ok(listFilesResponse);

        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<DeleteFileResponse> deleteFile(Long fileId) {
        fileNodeRepository.deleteFileNodeAndItsChildrenById(fileId);
        return ResponseEntity.ok(new DeleteFileResponse());
    }

}