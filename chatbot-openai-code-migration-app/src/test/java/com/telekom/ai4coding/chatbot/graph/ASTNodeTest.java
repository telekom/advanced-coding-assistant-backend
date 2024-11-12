package com.telekom.ai4coding.chatbot.graph;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

public class ASTNodeTest {

    @Test
    public void testASTNodeCreationWithoutChildren() {
        // Given
        String type = "node type";
        String text = "node text";
        int startLine = 1;
        int endLine = 2;

        // When
        ASTNode astNode = ASTNode.of(type, text, startLine, endLine, null);

        // Then
        assertThat(astNode.getType()).isEqualTo(type);
        assertThat(astNode.getText()).isEqualTo(text);
        assertThat(astNode.getStartLine()).isEqualTo(startLine);
        assertThat(astNode.getEndLine()).isEqualTo(endLine);
        assertThat(astNode.getChildASTNodes()).isNull();
    }

    @Test
    public void testASTNodeCreationWithChildren() {
        // Given
        String parentType = "parent node type";
        String childType = "child node type";
        ASTNode childNode = ASTNode.of(childType, "child node text", 2, 3, null);

        // When
        ASTNode parentNode = new ASTNode(null, parentType, "parent node text", 1, 4, null, List.of(childNode));

        // Then
        assertThat(parentNode.getChildASTNodes()).contains(childNode);
        assertThat(parentNode.getChildASTNodes()).hasSize(1);
    }

    @Test
    public void testAddChildASTNode() {
        // Given
        ASTNode parentNode = ASTNode.of("parent node type", "parent node text", 1, 4, null);
        ASTNode childNode1 = ASTNode.of("child node type 1", "child node text 1", 2, 3, null);
        ASTNode childNode2 = ASTNode.of("child node type 2", "child node text 2", 4, 5, null);

        // When
        parentNode.addChildASTNode(childNode1);
        parentNode.addChildASTNode(childNode2);

        // Then
        assertThat(parentNode.getChildASTNodes()).containsExactly(childNode1, childNode2);
    }

    @Test
    public void testWithId() {
        // Given
        ASTNode node = ASTNode.of("node type", "node text", 1, 2, null);
        Long newId = 100L;

        // When
        ASTNode nodeWithId = node.withId(newId);

        // Then
        assertThat(nodeWithId.getId()).isEqualTo(newId);
        assertThat(nodeWithId.getType()).isEqualTo(node.getType());
        assertThat(nodeWithId.getText()).isEqualTo(node.getText());
        assertThat(nodeWithId.getStartLine()).isEqualTo(node.getStartLine());
        assertThat(nodeWithId.getEndLine()).isEqualTo(node.getEndLine());
    }

    @Test
    public void testEqualsMethod() {
        // Given
        ASTNode node1 = ASTNode.of("node type", "node text", 1, 2, null);
        ASTNode node2 = ASTNode.of("node type", "node text", 1, 2, null);
        ASTNode node3 = ASTNode.of("different node type", "node text", 1, 2, null);
        ASTNode node4 = ASTNode.of("node type", "different node text", 1, 2, null);
        ASTNode node5 = ASTNode.of("node type", "node text", 3, 4, null);

        // Then
        assertThat(node1).isEqualTo(node2);
        assertThat(node1).isNotEqualTo(node3);
        assertThat(node1).isNotEqualTo(node4);
        assertThat(node1).isNotEqualTo(node5);
    }
}
