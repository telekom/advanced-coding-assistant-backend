package com.telekom.ai4coding.chatbot.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.LinkedList;
import java.util.List;


/**
 * Represents an AST node from tree-sitter.
 */
@AllArgsConstructor
@Getter
@Node
public class ASTNode {
    @Id
    @GeneratedValue
    private final Long id;

    private final String type;
    private final String text;

    // The starting line number of this AST node in the source code, 1-indexed and inclusive.
    private final int startLine;
    // The ending line number of this AST node in the source code, 1-indexed and inclusive.
    private final int endLine;

    // A vector of numbers representing the semantic meaning of the ASTNode generated
    // from an embedding model.
    // This has to be double, because float in neo4j is double in Java.
    // But the embedding model will generate float[] in Java, we need to convert them
    // into Java double[].
    // https://neo4j.com/docs/java-reference/current/extending-neo4j/values-and-types/
    private final double[] embedding;

    /**
     * An edge from a ASTNode to an ASTNode, that connects the parent AST node to the child AST node.
     */
    @Relationship(type = "PARENT_OF", direction = Relationship.Direction.OUTGOING)
    private List<ASTNode> childASTNodes;

    public static ASTNode of(final String type, final String text, final int startLine, final int endLine, final double[] embedding) {
        return new ASTNode(null, type, text, startLine, endLine, embedding, null);
    }

    /**
     * Mutating a Node for performance, but has to stay only package accessible!
     * <a href="https://docs.spring.io/spring-data/neo4j/reference/object-mapping/sdc-object-mapping.html#mapping.fundamentals.recommendations">...</a>
     *
     * @param childASTNode
     */
    void addChildASTNode(final ASTNode childASTNode) {
        if (this.childASTNodes == null) {
            this.childASTNodes = new LinkedList<>();
        }
        childASTNodes.add(childASTNode);
    }

    ASTNode withId(final Long id) {
        if (this.id != null && this.id.equals(id)) {
            return this;
        }

        return new ASTNode(id, this.type, this.text, this.startLine, this.endLine, this.embedding, this.childASTNodes);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        ASTNode other = (ASTNode) obj;
        return type.equals(other.type)
                && text.equals(other.text)
                && startLine == other.startLine
                && endLine == other.endLine;
    }
}