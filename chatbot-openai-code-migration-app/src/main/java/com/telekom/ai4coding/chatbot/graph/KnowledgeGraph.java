package com.telekom.ai4coding.chatbot.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Getter
public class KnowledgeGraph {
    private final FileNode rootFileNode;

    KnowledgeGraph(FileNode rootFileNode) {
        this.rootFileNode = rootFileNode;
    }

     List<FileNode> getAllFileNodes() {
        List<FileNode> allFileNodes = new ArrayList<FileNode>();
        Stack<FileNode> traversalStack = new Stack<FileNode>();
        if(rootFileNode == null) {
            return allFileNodes;
        }
        traversalStack.push(rootFileNode);

        FileNode curNode;
        List<FileNode> childFileNodes;
        while(!traversalStack.isEmpty()) {
            curNode = traversalStack.pop();
            allFileNodes.add(curNode);

            childFileNodes = curNode.getChildFileNodes();
            if(childFileNodes != null && !childFileNodes.isEmpty()) {
                traversalStack.addAll(childFileNodes);
            }
        }
        return allFileNodes;
    }

    List<ASTNode> getAllAstNodes() {
        List<ASTNode> allAstNodes = new ArrayList<ASTNode>();
        List<FileNode> allFileNodes = getAllFileNodes();

        Stack<ASTNode> traversalStack;
        List<ASTNode> rootAstNodes;
        ASTNode curNode;
        List<ASTNode> childAstNodes;
        for(FileNode fileNode : allFileNodes) {
            rootAstNodes = fileNode.getAstNodes();
            if(rootAstNodes != null && !rootAstNodes.isEmpty()) {
                traversalStack = new Stack<ASTNode>();
                traversalStack.addAll(rootAstNodes);
                while(!traversalStack.isEmpty()) {
                    curNode = traversalStack.pop();
                    allAstNodes.add(curNode);
        
                    childAstNodes = curNode.getChildASTNodes();
                    if(childAstNodes != null && !childAstNodes.isEmpty()) {
                        traversalStack.addAll(childAstNodes);
                    }
                }
            }
        }
        return allAstNodes;
    }

    List<TextNode> getAllTextNodes() {
        List<TextNode> allTextNodes = new ArrayList<TextNode>();
        List<FileNode> allFileNodes = getAllFileNodes();
        List<TextNode> childTextNodes;

        for(FileNode fileNode : allFileNodes) {
            childTextNodes = fileNode.getTextNodes();
            if(childTextNodes != null && !childTextNodes.isEmpty()) {
                allTextNodes.addAll(childTextNodes);
            }
        }
        return allTextNodes;
    }

    /**
     * Returns a string representation of the file tree for the knowledge graph.
     * The file tree is represented as a hierarchical structure with indentation and special characters.
     * Each line in the string represents a file or directory in the tree.
     * Directories are denoted by a prefix and files are denoted by their basename.
     * The tree is traversed in a depth-first manner.
     *
     * @return a string representation of the file tree.
     */
    String getFileTree() {
        @AllArgsConstructor
        class TraversalNode {
            public FileNode fileNode;
            public int depth;
            public String prefix;
            public boolean isLast;
        }
        Stack<TraversalNode> traversalStack = new Stack<TraversalNode>();
        traversalStack.push(new TraversalNode(rootFileNode, 0, "", true));
        ArrayList<String> resultLines = new ArrayList<String>();

        TraversalNode curNode;
        List<FileNode> children;
        String pointer;
        String extension;
        String linePrefix;
        String newPrefix;
        String SPACE = "    ";
        String BRANCH = "│   ";
        String TEE = "├── ";
        String LAST = "└── ";
        while(!traversalStack.isEmpty()) {
            curNode = traversalStack.pop();
            // The pointer is the special character that indicates the relationship
            // between the current file and its parent directory.
            pointer = curNode.isLast ? LAST : TEE;
            // Special treatment for the root directory, which has no pointer
            linePrefix = (curNode.depth == 0) ? "" : curNode.prefix + pointer;
            resultLines.add(linePrefix + curNode.fileNode.getBasename());

            children = curNode.fileNode.getChildFileNodes();
            if(children != null && !children.isEmpty()) {
                // Create a new list and sort children by basename to ensure consistent output
                children = new ArrayList<>(children);
                children.sort((a, b) -> a.getBasename().compareTo(b.getBasename()));
                for(int i = children.size()-1; i >= 0; i--) {
                    // Decide the extension and prefix for the child node
                    extension = (curNode.isLast) ? SPACE : BRANCH;
                    // Special treatment for the files for root directory, which
                    // adds no prefix
                    newPrefix = (curNode.depth == 0) ? "" : curNode.prefix + extension;
                    traversalStack.push(new TraversalNode(children.get(i), curNode.depth + 1, newPrefix, i == children.size() - 1));
                }
            }
        }

        return String.join("\n", resultLines);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; 
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        KnowledgeGraph other = (KnowledgeGraph) obj;

        return this.rootFileNode.equals(other.rootFileNode);
    }
}
