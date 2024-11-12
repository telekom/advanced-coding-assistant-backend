package com.telekom.ai4coding.chatbot.service;

import com.telekom.ai4coding.chatbot.graph.FileNode;
import com.telekom.ai4coding.chatbot.graph.KnowledgeGraphBatchInsertService;
import com.telekom.ai4coding.chatbot.graph.KnowledgeGraphBuilder;
import com.telekom.ai4coding.chatbot.repository.FileNodeRepository;

import dev.langchain4j.model.embedding.EmbeddingModel;
import lombok.RequiredArgsConstructor;
import net.lingala.zip4j.ZipFile;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.RepositoryArchiveParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class RepositoryService {

    @Autowired
    EmbeddingModel embeddingModel;

    private final GitLabApi gitLabApi;
    private final KnowledgeGraphBuilder knowledgeGraphBuilder;
    private final FileNodeRepository fileNodeRepository;
    private final KnowledgeGraphBatchInsertService knowledgeGraphBatchInsertService;

    //TODO remove this folder after usage, so it doesn't linger in the file structure
    private static final String CLONE_DIR_PATH = "CLONE";


    /**
     * Uploads a GitLab repository to the database and builds its knowledge graph.
     * This method download the archive of the repository identified by the given project ID from GitLab,
     * processes it to build a knowledge graph, and then uploads this graph to the database.
     * at last delete the archive of the repository.
     *
     * @param projectId The ID of the GitLab project to be uploaded.
     * @return ResponseEntity<String> containing the result of the operation. Returns an OK status with
     * a success message if the repository was processed successfully, or a BAD_REQUEST status
     * with an error message if the operation fails.
     */
    public ResponseEntity<String> uploadGitlabRepository(String projectId) {
        try {
            Project gitlab = gitLabApi.getProjectApi().getProject(projectId);
            Path directoryPath = Files.createDirectories(Paths.get(CLONE_DIR_PATH));
            handleGitLabProjectAndDeleteRepo(gitlab, directoryPath, false);
            return ResponseEntity.ok("Repository processed successfully.");
        } catch (IOException | GitLabApiException e) {
            return ResponseEntity.badRequest().body("Error processing GitLab repository: " + e.getMessage());
        }
    }

    /**
     * Uploads a local repository to the database and builds its knowledge graph.
     * This method processes the local repository identified by the given path to build a knowledge graph,
     * and then uploads this graph to the database.
     *
     * @param localPath The path of the local repository to be uploaded.
     * @return ResponseEntity<String> containing the result of the operation. Returns an OK status with
     * a success message if the repository was processed successfully, or a BAD_REQUEST status
     * with an error message if the operation fails.
     */
    public ResponseEntity<String> uploadLocalRepository(String localPath) {
        Path directoryPath = Paths.get(localPath);
        if (!Files.exists(directoryPath) || !Files.isDirectory(directoryPath)) {
            return ResponseEntity.badRequest().body("Invalid path: Path does not exist or is not a directory.");
        }
        handleLocalProject(directoryPath, false);
        return ResponseEntity.ok("Repository processed successfully.");
    }

    /**
     * Refreshes a GitLab repository by re-downloading the archive of the repository identified by the given project ID from GitLab,
     * processes it to build a knowledge graph, and then uploads this graph to the database.
     * at last delete the archive of the repository.
     *
     * @param projectId The ID of the GitLab project to be refreshed.
     * @return ResponseEntity<String> containing the result of the operation. Returns an OK status with
     * a success message if the repository was refreshed successfully, or a BAD_REQUEST status
     * with an error message if the operation fails.
     */
    public ResponseEntity<String> refreshGitlabRepository(String projectId) {
        try {
            Project gitlab = gitLabApi.getProjectApi().getProject(projectId);
            Path directoryPath = Files.createDirectories(Paths.get(CLONE_DIR_PATH));
            handleGitLabProjectAndDeleteRepo(gitlab, directoryPath, true);
            return ResponseEntity.ok("Repository refreshed successfully.");
        } catch (IOException | GitLabApiException e) {
            return ResponseEntity.badRequest().body("Error refreshing GitLab repository: " + e.getMessage());
        }
    }

    /**
     * Refreshes a local repository by processing the local repository identified by the given path to build a knowledge graph,
     * and then uploads this graph to the database.
     *
     * @param localPath The path of the local repository to be refreshed.
     * @return ResponseEntity<String> containing the result of the operation. Returns an OK status with
     * a success message if the repository was refreshed successfully, or a BAD_REQUEST status
     * with an error message if the operation fails.
     */
    public ResponseEntity<String> refreshLocalRepository(String localPath) {
        Path directoryPath = Paths.get(localPath);
        if (!Files.exists(directoryPath) || !Files.isDirectory(directoryPath)) {
            return ResponseEntity.badRequest().body("Invalid path: Path does not exist or is not a directory.");
        }
        handleLocalProject(directoryPath, true);
        return ResponseEntity.ok("Repository refreshed successfully.");
    }

    /**
     * Handles the GitLab project by downloading the archive of the repository, processing it to build a knowledge graph,
     * and then uploading this graph to the database.
     * at last delete the archive of the repository.
     *
     * @param gitlab        The GitLab project to be handled.
     * @param directoryPath The path of the directory where the archive of the repository will be downloaded.
     * @param refresh       A boolean value indicating whether the repository is being or should be refreshed.
     * @throws GitLabApiException if an error occurs while downloading the archive of the repository.
     */
    private void handleGitLabProjectAndDeleteRepo(Project gitlab, Path directoryPath, boolean refresh) throws GitLabApiException, IOException {
        String repoPath = downloadGitlabArchiveAndDeleteZip(gitlab, directoryPath);
        repoPath = repoPath.replaceFirst("\\.zip$", "");
        Path archivePath = Paths.get(CLONE_DIR_PATH, repoPath);
        File correctName = new File(directoryPath.toFile(), gitlab.getName());
        archivePath.toFile().renameTo(correctName);
        uploadRepoToDB(directoryPath.toFile(), refresh);
        FileUtils.deleteDirectory(correctName);
    }

    /**
     * Handles the local project by processing the local repository identified by the given path to build a knowledge graph,
     * and then uploading this graph to the database.
     *
     * @param directoryPath The path of the local repository to be handled.
     * @param refresh       A boolean value indicating whether the repository is being or should be refreshed.
     */
    private void handleLocalProject(Path directoryPath, boolean refresh) {
        uploadRepoToDB(directoryPath.toFile(), refresh);
    }

    /**
     * Uploads the repository to the database.
     *
     * @param directory The directory of the repository to be uploaded.
     * @param refresh   A boolean value indicating whether the repository is being refreshed.
     *                  If true, the existing repository in the database will be deleted before uploading the new one.
     *                  If false, the new repository will be added to the existing one.
     */
    private void uploadRepoToDB(File directory, boolean refresh) {
        FileNode knowledgeGraph = knowledgeGraphBuilder.buildGraphFromDir(directory).getRootFileNode();
        if (refresh) {
            fileNodeRepository.deleteCompleteCodeGraph();
        }

        knowledgeGraphBatchInsertService.batchInsertFileStructure(knowledgeGraph, embeddingModel.dimension());
    }

    /**
     * Downloads the archive of the repository identified by the given project ID from GitLab,
     * unzips it, and then deletes the archive.
     *
     * @param project       The GitLab project to download the archive from.
     * @param directoryPath The path of the directory where the archive of the repository will be downloaded.
     * @return The name of the downloaded archive.
     * @throws GitLabApiException if an error occurs while downloading the archive of the repository.
     */
    private String downloadGitlabArchiveAndDeleteZip(Project project, Path directoryPath) throws GitLabApiException {
        try {
            File archive = gitLabApi.getRepositoryApi().getRepositoryArchive(project.getId(), new RepositoryArchiveParams(), directoryPath.toFile(), "zip");
            unzip(archive.toPath(), directoryPath);
            archive.delete();
            return archive.getName();
        } catch (GitLabApiException e) {
            throw new GitLabApiException("Error downloading the repo: ", e.getHttpStatus());
        }
    }

    /**
     * Unzips the given zip file to the specified destination directory.
     *
     * @param zipDir  The path of the zip file to be unzipped.
     * @param destDir The path of the directory where the zip file will be unzipped.
     */
    private void unzip(Path zipDir, Path destDir) {
        if (!Files.exists(zipDir)) return;
        try (ZipFile zipFile = new ZipFile(zipDir.toFile())) {
            zipFile.extractAll(destDir.toString());
        } catch (IOException e) {
            throw new RuntimeException("Error unzipping file: " + e.getMessage(), e);
        }
    }

}
