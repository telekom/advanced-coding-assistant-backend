package com.telekom.ai4coding.chatbot.graph;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TextNodeTest {

    @Test
    public void testTextNodeCreationWithoutNextNode() {
        // Given
        String text = "Ich bin ein Test Text";
        String metadata = "Test metadata";

        // When
        TextNode textNode = TextNode.of(text, metadata, null);

        // Then
        assertThat(textNode.getText()).isEqualTo(text);
        assertThat(textNode.getMetadata()).isEqualTo(metadata);
        assertThat(textNode.getNextTextNode()).isNull();
    }

    @Test
    public void testTextNodeCreationWithNextNode() {
        // Given
        String text1 = "Test Text 1";
        String metadata1 = "Test metadata 1";
        String text2 = "Test Text 2";
        String metadata2 = "Test metadata 2";

        TextNode nextNode = TextNode.of(text2, metadata2, null);

        // When
        TextNode textNode = TextNode.of(text1, metadata1, null, nextNode);

        // Then
        assertThat(textNode.getText()).isEqualTo(text1);
        assertThat(textNode.getMetadata()).isEqualTo(metadata1);
        assertThat(textNode.getNextTextNode()).isEqualTo(nextNode);
    }

    @Test
    public void testEqualsMethod() {
        // Given
        TextNode node1 = TextNode.of("Test Text", "Test Metadata", null);
        TextNode node2 = TextNode.of("Test Text", "Test Metadata", null);
        TextNode node3 = TextNode.of("Different Text", "Test Metadata", null);
        TextNode node4 = TextNode.of("Test Text", "Different Metadata", null);

        // Then
        assertThat(node1).isEqualTo(node2);
        assertThat(node1).isNotEqualTo(node3);
        assertThat(node1).isNotEqualTo(node4);
    }

    @Test
    public void testNextTextNodeSetter() {
        // Given
        TextNode node1 = TextNode.of("Test Text 1", "Test Metadata 1", null);
        TextNode node2 = TextNode.of("Test Text 2", "Test Metadata 2", null);

        // When
        node1.setNextTextNode(node2); // Accessible due to package-private access

        // Then
        assertThat(node1.getNextTextNode()).isEqualTo(node2);
    }
}

