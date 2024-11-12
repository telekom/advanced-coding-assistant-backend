package com.telekom.ai4coding.chatbot.graph;

import com.telekom.ai4coding.chatbot.repository.conversation.ConversationNode;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

public class FileNodeTest {

    @Test
    public void testFileNodeCreationWithoutRelationships() {
        // Given
        String basename = "testFile.txt";
        String relativePath = "/path/to/testFile.txt";

        // When
        FileNode fileNode = FileNode.of(basename, relativePath);

        // Then
        assertThat(fileNode.getBasename()).isEqualTo(basename);
        assertThat(fileNode.getRelativePath()).isEqualTo(relativePath);
        assertThat(fileNode.getChildFileNodes()).isNull();
        assertThat(fileNode.getAstNodes()).isNull();
        assertThat(fileNode.getTextNodes()).isNull();
        assertThat(fileNode.getConversationNode()).isNull();
    }

    @Test
    public void testFileNodeCreationWithChildFiles() {
        // Given
        String parentBasename = "parentDir";
        String childBasename = "childFile.txt";
        FileNode childNode = FileNode.of(childBasename, "/parentDir/childFile.txt");

        // When
        FileNode parentNode = FileNode.of(parentBasename, "/parentDir", List.of(childNode));

        // Then
        assertThat(parentNode.getChildFileNodes()).containsExactly(childNode);
        assertThat(parentNode.getBasename()).isEqualTo(parentBasename);
    }

    @Test
    public void testAddChildFileNode() {
        // Given
        FileNode parentNode = FileNode.of("parentDir", "/parentDir");
        FileNode childNode1 = FileNode.of("childFile1.txt", "/parentDir/childFile1.txt");
        FileNode childNode2 = FileNode.of("childFile2.txt", "/parentDir/childFile2.txt");

        // When
        parentNode.addChildFileNode(childNode1);
        parentNode.addChildFileNode(childNode2);

        // Then
        assertThat(parentNode.getChildFileNodes()).containsExactly(childNode1, childNode2);
    }

    @Test
    public void testAddAstNode() {
        // Given
        FileNode fileNode = FileNode.of("testFile.txt", "/testFile.txt");
        ASTNode astNode = ASTNode.of("type", "code", 1, 10, null);

        // When
        fileNode.addAstNode(astNode);

        // Then
        assertThat(fileNode.getAstNodes()).containsExactly(astNode);
    }

    @Test
    public void testAddTextNode() {
        // Given
        FileNode fileNode = FileNode.of("testFile.txt", "/testFile.txt");
        TextNode textNode = TextNode.of("Sample text", "metadata", null);

        // When
        fileNode.addTextNode(textNode);

        // Then
        assertThat(fileNode.getTextNodes()).containsExactly(textNode);
    }

    @Test
    public void testWithId() {
        // Given
        FileNode fileNode = FileNode.of("testFile.txt", "/testFile.txt");
        Long newId = 123L;

        // When
        FileNode fileNodeWithId = fileNode.withId(newId);

        // Then
        assertThat(fileNodeWithId.getId()).isEqualTo(newId);
        assertThat(fileNodeWithId.getBasename()).isEqualTo(fileNode.getBasename());
        assertThat(fileNodeWithId.getRelativePath()).isEqualTo(fileNode.getRelativePath());
        assertThat(fileNodeWithId.getChildFileNodes()).isEqualTo(fileNode.getChildFileNodes());
    }

    @Test
    public void testEqualsMethod() {
        // Given
        FileNode fileNode1 = FileNode.of("testFile.txt", "/path/to/testFile.txt");
        FileNode fileNode2 = FileNode.of("testFile.txt", "/path/to/testFile.txt");
        FileNode fileNode3 = FileNode.of("differentFile.txt", "/path/to/differentFile.txt");

        // Then
        assertThat(fileNode1).isEqualTo(fileNode2);
        assertThat(fileNode1).isNotEqualTo(fileNode3);
    }

    @Test
    public void testFileNodeCreationWithConversationNode() {
        // Given
        ConversationNode conversationNode = ConversationNode.of("test");
        String basename = "testFile.txt";
        String relativePath = "/path/to/testFile.txt";

        // When
        FileNode fileNode = FileNode.of(basename, relativePath, conversationNode);

        // Then
        assertThat(fileNode.getConversationNode()).isEqualTo(conversationNode);
    }
}
