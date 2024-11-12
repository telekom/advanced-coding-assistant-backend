package com.telekom.ai4coding.chatbot.graph;

import com.telekom.ai4coding.chatbot.repository.conversation.ConversationNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.LinkedList;
import java.util.List;


/**
 * Represents a file in the directory.
 */
@AllArgsConstructor
@Getter
@Node
public class FileNode {
    @Id
    @GeneratedValue
    private final Long id;

    private final String basename;
    private final String relativePath;


    /**
     * An edge from a FileNode to an FileNode, that connects the parent directory to the child directory/file.
     */
    @Relationship(type = "HAS_FILE", direction = Relationship.Direction.OUTGOING)
    private List<FileNode> childFileNodes;

    /**
     * An edge from a FileNode to an ASTNode, that connects the root AST node to the corresponding file.
     */
    @Relationship(type = "HAS_AST", direction = Relationship.Direction.OUTGOING)
    private List<ASTNode> astNodes;

    /**
     * An edge from a FileNode to an TextNode, that connect file to all text it contains.
     */
    @Relationship(type = "HAS_TEXT", direction = Relationship.Direction.OUTGOING)
    private List<TextNode> textNodes;


    /**
     * An edge from a FileNode to an ConversationNode, that connects the file to the conversation.
     */
    @Relationship(type = "RELATED_TO", direction = Relationship.Direction.OUTGOING)
    private ConversationNode conversationNode;



    public static FileNode of(final String basename, final String relativePath,
                              final List<FileNode> childFiles, final List<ASTNode> astNodes,
                              final List<TextNode> textNodes) {
        return new FileNode(null, basename, relativePath, childFiles, astNodes, textNodes,null);
    }

    public static FileNode of(final String basename, final String relativePath,
                              final List<FileNode> childFiles, final List<ASTNode> astNodes) {
        return new FileNode(null, basename, relativePath, childFiles, astNodes, null,null);
    }

    public static FileNode of(final String basename, final String relativePath, final List<FileNode> childFiles) {
        return new FileNode(null, basename, relativePath, childFiles, null, null,null);
    }

    public static FileNode of(final String basename, final String relativePath) {
        return new FileNode(null, basename, relativePath, null, null, null,null);
    }

    public static FileNode of(final String basename, final String relativePath,final ConversationNode conversationNode) {
        return new FileNode(null, basename, relativePath, null, null, null,conversationNode);
    }

    /**
     * Mutating a Node for performance, but has to stay only package accessible!
     * <a href="https://docs.spring.io/spring-data/neo4j/reference/object-mapping/sdc-object-mapping.html#mapping.fundamentals.recommendations">...</a>
     * @param childFileNode
     */
    void addChildFileNode(final FileNode childFileNode) {
        if (this.childFileNodes == null) {
            this.childFileNodes = new LinkedList<>();
        }
        childFileNodes.add(childFileNode);
    }

    /**
     * Mutating a Node for performance, but has to stay only package accessible!
     * <a href="https://docs.spring.io/spring-data/neo4j/reference/object-mapping/sdc-object-mapping.html#mapping.fundamentals.recommendations">...</a>
     * @param astNode
     */
    void addAstNode(final ASTNode astNode) {
        if (this.astNodes == null) {
            this.astNodes = new LinkedList<>();
        }
        astNodes.add(astNode);
    }

    /**
     * Mutating a Node for performance, but has to stay only package accessible!
     * <a href="https://docs.spring.io/spring-data/neo4j/reference/object-mapping/sdc-object-mapping.html#mapping.fundamentals.recommendations">...</a>
     * @param textNode
     */
    void addTextNode(final TextNode textNode) {
        if (this.textNodes == null) {
            this.textNodes = new LinkedList<>();
        }
        textNodes.add(textNode);
    }

    FileNode withId(Long id) {
        if (this.id != null && this.id.equals(id)) {
            return this;
        }

        return new FileNode(id, basename, relativePath, childFileNodes, astNodes, textNodes,conversationNode);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        FileNode other = (FileNode) obj;
        return basename.equals(other.basename) && relativePath.equals(other.relativePath);
    }
}