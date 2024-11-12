package com.telekom.ai4coding.chatbot.graph;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;


/**
 * Represents a piece of text.
 */
@AllArgsConstructor
@Getter
@Node
public class TextNode {
    @Id
    @GeneratedValue
    private final Long id;

    private final String text;
    private final String metadata;

    // A vector of numbers representing the semantic meaning of the TextNode generated
    // from an embedding model.
    // This has to be double, because float in neo4j is double in Java.
    // But the embedding model will generate float[] in Java, we need to convert them
    // into Java double[].
    // https://neo4j.com/docs/java-reference/current/extending-neo4j/values-and-types/
    private final double[] embedding;

    /**
     * An edge from a TextNode to the next TextNode that contains continuation of the text.
     * Mutating a Node for performance, but has to stay only package accessible!
     * https://docs.spring.io/spring-data/neo4j/reference/object-mapping/sdc-object-mapping.html#mapping.fundamentals.recommendations
     */
    @Setter(AccessLevel.PACKAGE)
    @Relationship(type = "NEXT_CHUNK", direction = Relationship.Direction.OUTGOING)
    private TextNode nextTextNode;

    public static TextNode of(final String text, final String metadata, final double[] embedding, final TextNode nextTextNode) {
        return new TextNode(null, text, metadata, embedding, nextTextNode);
    }

    public static TextNode of(final String text, final String metadata, final double[] embedding) {
      return new TextNode(null, text, metadata, embedding, null);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        TextNode other = (TextNode) obj;
        return text.equals(other.text) && metadata.equals(other.metadata);
    }
}