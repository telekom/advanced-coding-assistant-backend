package com.telekom.ai4coding.chatbot.controller;

import com.telekom.ai4coding.chatbot.service.RepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/repositories")
public class RepositoriesController {

    public final RepositoryService repositoryService;

    /**
     * Uploads a GitLab repository identified by the project ID.
     * @param projectId the ID of the GitLab project to upload
     * @return ResponseEntity with the operation result
     */
    @PostMapping("/gitlab/{projectId}")
    public ResponseEntity<String> uploadGitlabRepository(@PathVariable String projectId) {
        return repositoryService.uploadGitlabRepository(projectId);
    }

    /**
     * Uploads a local repository identified by the local path.
     * @param localPath the path of the local repository to upload
     * @return ResponseEntity with the operation result
     * @consumes text/plain
     */
    @PostMapping(value="/local", consumes = "text/plain")
    public ResponseEntity<String> uploadLocalRepository(@RequestBody String localPath) {
        return repositoryService.uploadLocalRepository(localPath);
    }

    /**
     * Refreshes a GitLab repository identified by the project ID.
     * @param projectId the ID of the GitLab project to refresh
     * @return ResponseEntity with the operation result
     */
    @PutMapping("/gitlab/{projectId}/refresh")
    public ResponseEntity<String> refreshRepository(@PathVariable String projectId) {
        return repositoryService.refreshGitlabRepository(projectId);
    }

    /**
     * Refreshes a local repository identified by the local path.
     * @param localPath the path of the local repository to refresh
     * @return ResponseEntity with the operation result
     * @consumes text/plain
     */
    @PutMapping(value="/local/refresh" , consumes = "text/plain")
    public ResponseEntity<String> refreshLocalRepository(@RequestBody String localPath) {
        return repositoryService.refreshLocalRepository(localPath);
    }
}
