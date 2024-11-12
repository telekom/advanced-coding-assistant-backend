package com.telekom.ai4coding.chatbot.graph;

import com.telekom.ai4coding.chatbot.configuration.properties.AcaProperties;
import com.telekom.ai4coding.chatbot.repository.conversation.ConversationNode;
import com.telekom.ai4coding.chatbot.treesitter.TreeSitterParser;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.Metadata;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import lombok.extern.slf4j.Slf4j;
import nl.basjes.gitignore.GitIgnoreFileSet;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;
import org.treesitter.TSNode;
import org.treesitter.TSTree;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static com.telekom.ai4coding.chatbot.utils.EmojiRegex.replaceEmojis;


/**
 * The knowledge graph for a codebase.
 * <p>
 * The knowledge graph contains FileNode, ASTNode and TextNode. The FileNode represent a
 * file in the file system, the ASTNode represents a tree-sitter AST node, and TextNode
 * represents a chunk of text. FileNode can be connected to another FileNode through
 * HAS_FILE edge if a file is the parent of another file. ASTNode can be connected to
 * another ASTNode if the AST node is the parent node of the other AST node. FileNode can
 * also be connected to ASTNode, if the AST node is the root node of source code parsed
 * from the file. TextNode are connected to the next TextNode is chronological order.
 * The parent node of TextNode is a FileNode that represents the file.
 * 
 * All nodes has different attributes, like FileNode has basename, ASTNode has type and
 * TextNode has text. But for (some) ASTNode and TextNode, we also generate embeddings
 * for them. All TextNode are embedded, while not all ASTNode are embedded. The reason
 * is that the parent ASTNode has all the text that the children ASTNode have. So we
 * want to embed the biggest ASTNode we can.
 */
@Slf4j
@Component
public class KnowledgeGraphBuilder {

    private final TreeSitterParser parser;
    private final DocumentSplitter splitter;
    private final int astMaxDepth;
    private final int textMaxChar;
    private final EmbeddingModel embeddingModel;

    public KnowledgeGraphBuilder(AcaProperties acaProperties, EmbeddingModel embeddingModel) {
        this.parser = new TreeSitterParser();
        this.astMaxDepth = acaProperties.knowledgeGraphConstruction().astMaxDepth();
        this.textMaxChar = acaProperties.knowledgeGraphConstruction().textMaxChar();
        this.splitter = DocumentSplitters.recursive(acaProperties.knowledgeGraphConstruction().textMaxChar(),
                acaProperties.knowledgeGraphConstruction().textMaxOverlappingChar());
        this.embeddingModel = embeddingModel;
    }


    /**
     * Creates ASTNode and ParentOfEdge from a source code file.
     * <p>
     * We parse the file using tree-sitter and creates ASTNode for each AST node and
     * PARENT_OF edge between two AST nodes if one AST node is the parent of another AST
     * node. Embedding are also generated for the largest ASTNode parent if possible,
     * otherwise we recursively go down to the children ASTNode.
     *
     * @param file           The file to parse as {@code File}.
     * @param parentFileNode The parent FileNode to connect ASTNodes.
     * some errors (unsupported language, parser error, file reading error etc.)
     */
    private void buildASTSubGraph(File file, FileNode parentFileNode) {
        // Read file content and replace emoji before using tree-sitter
        // to parse it.
        byte[] fileContent = readFileContent(file);
        if (fileContent == null) {
            return;
        }

        String astRootNodeText = new String(fileContent, StandardCharsets.UTF_8).strip();

        TSTree tree = parser.parse(astRootNodeText, file.getName());
        if (!isValidTree(tree)) {
            return;
        }

        double[] astRootNodeEmbedding = null;
        // This attribute indicates if the children nodes need to generate embedding 
        // because the parent node is too large.
        boolean needEmbedding = true;
        if(astRootNodeText.length() <= this.textMaxChar) {
            astRootNodeEmbedding = convertFloatsToDoubles(this.embeddingModel.embed(astRootNodeText).content().vector());
            needEmbedding = false;
        }
        TSNode tsRootNode = tree.getRootNode();
        ASTNode astRootNode = ASTNode.of(
                tsRootNode.getType(),
                astRootNodeText,
                tsRootNode.getStartPoint().getRow() + 1,
                tsRootNode.getEndPoint().getRow() + 1,
                astRootNodeEmbedding);
        parentFileNode.addAstNode(astRootNode);

        Stack<NodeData> nodeStack = new Stack<>();
        nodeStack.push(new NodeData(tsRootNode, astRootNode, 1, needEmbedding));

        while (!nodeStack.empty()) {
            NodeData nodeData = nodeStack.pop();
            if (nodeData.depth > this.astMaxDepth) {
                continue;
            }
            for (int i = 0; i < nodeData.tsNode.getChildCount(); i++) {
                TSNode tsChildNode = nodeData.tsNode.getChild(i);
                byte[] astChildNodeBytes = Arrays.copyOfRange(fileContent, tsChildNode.getStartByte(), tsChildNode.getEndByte());
                String astChildNodeText = new String(astChildNodeBytes, StandardCharsets.UTF_8).strip();
                double[] astChildNodeEmbedding = null;

                // If the parent node need embedding, it means that we did not generate the
                // embedding for the parent node. Therefore we need to generate embedding
                // for the children ASTNode.
                boolean astChildNodeNeedEmbedding = nodeData.needEmbedding;
                if(nodeData.needEmbedding && astChildNodeText.length() > 0 && astChildNodeText.length() <= this.textMaxChar) {
                    astChildNodeEmbedding = convertFloatsToDoubles(this.embeddingModel.embed(astChildNodeText).content().vector());
                    astChildNodeNeedEmbedding = false;
                }
                ASTNode astChildNode = ASTNode.of(
                        tsChildNode.getType(),
                        astChildNodeText,
                        tsChildNode.getStartPoint().getRow() + 1,
                        tsChildNode.getEndPoint().getRow() + 1,
                        astChildNodeEmbedding
                );
                nodeData.astNode.addChildASTNode(astChildNode);
                nodeStack.push(new NodeData(tsChildNode, astChildNode, nodeData.depth + 1, astChildNodeNeedEmbedding));
            }
        }
    }

    private double[] convertFloatsToDoubles(float[] input) {
        double[] output = new double[input.length];
        for (int i = 0; i < input.length; i++) {
            output[i] = input[i];
        }
        return output;
    }

    private boolean isValidTree(TSTree tree) {
        if (tree == null) {
            return false;
        }
        TSNode tsRootNode = tree.getRootNode();
        return !tsRootNode.hasError() && tsRootNode.getChildCount() > 0;
    }

    private byte[] readFileContent(File file) {
        try {
            return replaceEmojis(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            log.error("Error reading file: " + e.getMessage());
            return null;
        }
    }

    private record NodeData(TSNode tsNode, ASTNode astNode, int depth, boolean needEmbedding){}

    private void buildTextSubGraph(File file, FileNode parentFileNode) {
        String text = readTextFile(file);
        try {
            processText(text, parentFileNode);
        } catch (NullPointerException e) {
            log.error("Error reading file: {}", file.getName(), e);
        }
    }

    private void buildPDFSubGraph(File file, FileNode parentFileNode) {
        try {
            String text = readPDFFile(file);
            processText(text, parentFileNode);
        } catch (IOException e) {
            log.error("Error reading PDF file: {}", file.getName(), e);
        }
    }

    private String readTextFile(File file) {
            try {
                // First, try to read the file as UTF-8
                return Files.readString(file.toPath(), StandardCharsets.UTF_8);
            } catch (IOException utf8Exception) {
                try {
                    // If UTF-8 parsing fails, try reading the file as ISO-8859-1
                    return Files.readString(file.toPath(), StandardCharsets.ISO_8859_1);
                } catch (IOException isoException) {
                    // If both attempts fail, log the error and handle it
                    log.error("Error reading file: {}. Either the type of the file you provided is not supported, or the file is not formatted in UTF-8 or ISO-8859-1.", file.getName(), isoException);
                    return null;
                }
            }
    }

    private String readPDFFile(File file) throws IOException {
        try (PDDocument document = Loader.loadPDF(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setSortByPosition(true);
            return stripper.getText(document);
        }
    }

    private void processText(String text, FileNode parentFileNode) {
        text = text.strip();
        if(text.length() == 0) {
            return;
        }
        Document doc = new Document(text, new Metadata());
        List<TextSegment> textSegments = splitter.split(doc);
        createTextNodes(textSegments, parentFileNode);
    }

    private void createTextNodes(List<TextSegment> textSegments, FileNode parentFileNode) {
        TextNode previousTextNode = null;
        for (TextSegment segment : textSegments) {
            double[] textNodeEmbedding = convertFloatsToDoubles(this.embeddingModel.embed(segment.text()).content().vector());;
            TextNode textNode = TextNode.of(segment.text(), segment.metadata().toString(), textNodeEmbedding);
            parentFileNode.addTextNode(textNode);
            if (previousTextNode != null) {
                previousTextNode.setNextTextNode(textNode);
            }
            previousTextNode = textNode;
        }
    }

    /**
     * Builds the knowledge graph from a directory.
     * <p>
     * The knowledge graph contains FileNode, ASTNode and TextNode. The FileNode represent a file in the
     * file system, the ASTNode represents a tree-sitter AST node, and the TextNode represents a piece of
     * text. FileNode can be connected to another FileNode through HAS_FILE edge if a file is the parent
     * of another file. ASTNode can be connected to another ASTNode if the AST node is the parent node of
     * the other AST node. TextNode can be connected to the next TextNode in chronological order. FileNode
     * can also be connected to ASTNode, if the AST node is the root node of source code parsed from the file.
     * FileNode can also be connected TextNode, if the file contains (txt, md or pdf) text that is not source
     * code. ConversationNode attach the uploaded File/Dir to the given conversation.
     * All nodes and edges are stored in the KnowledgeGraph attributes.
     *
     * @param rootDir The directory or file as {@code File} to create the knowledge graph.
     * @param conversationNode The conversation node to connect the file node to.
     * @return KnowledgeGraph which represents the root directory with the complete graph of subdirectories and all associated
     * {@link ASTNode}
     */

    public KnowledgeGraph buildGraphFromDir(File rootDir,ConversationNode conversationNode) {
        FileNode rootFileNode = FileNode.of(rootDir.getName(), ".",conversationNode);
        traverseFileSystem(rootDir, rootFileNode, rootDir);
        return new KnowledgeGraph(rootFileNode);
    }


    /**
     * This overloaded method builds the knowledge graph from a directory without requiring a `ConversationNode` parameter.
     * It is useful when the repository is not connected to a conversation, allowing users to upload files independently.
     *
     * @param rootDir The root directory or file to create the knowledge graph from.
     * @return KnowledgeGraph representing the root directory with the complete graph of subdirectories and all associated {@link ASTNode}.
     */
    public KnowledgeGraph buildGraphFromDir(File rootDir) {
        return this.buildGraphFromDir(rootDir, null);
    }



    private void traverseFileSystem(File rootDir, FileNode rootFileNode, File baseDir) {
        Stack<FileSystemItem> stack = new Stack<>();
        stack.push(new FileSystemItem(rootDir, rootFileNode));
        GitIgnoreFileSet gitIgnoreFileSet = null;

        File gitIgnoreFile = new File(rootDir, ".gitignore");
        if (gitIgnoreFile.exists()) {
            try {
                gitIgnoreFileSet = new GitIgnoreFileSet(rootDir);
            } catch (IllegalArgumentException e) {
                log.warn("The .gitignore file is not relative to the project root: {}", e.getMessage());
            }
        }

        while (!stack.isEmpty()) {
            FileSystemItem current = stack.pop();
            File file = current.file;
            FileNode fileNode = current.fileNode;

            // Not interested in files beginning with a dot "." and git ignored files
            if ((gitIgnoreFileSet != null && gitIgnoreFileSet.ignoreFile(file.getAbsolutePath()))
                    || file.getName().startsWith(".")) {
                continue;
            }

            if (file.isDirectory()) {
                processDirectory(file, fileNode, stack, baseDir);
            } else {
                processFile(file, fileNode);
            }
        }
    }

    private void processDirectory(File dir,
                                  FileNode dirNode,
                                  Stack<FileSystemItem> stack,
                                  File baseDir) {
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }

        for (File childFile : files) {

            FileNode childNode = createChildFileNode(childFile, baseDir);
            dirNode.addChildFileNode(childNode);
            stack.push(new FileSystemItem(childFile, childNode));
        }
    }

    private void processFile(File file, FileNode fileNode) {
        if (TreeSitterParser.supportsFile(file)) {
            buildASTSubGraph(file, fileNode);
        } else if (isTextFile(file)) {
            buildTextSubGraph(file, fileNode);
        } else if (isPDFFile(file)) {
            buildPDFSubGraph(file, fileNode);
        }
    }

    private FileNode createChildFileNode(File file, File baseDir) {
        Path basePath = baseDir.toPath();
        Path childPath = file.toPath();
        String relativePath = basePath.relativize(childPath).toString();
        return FileNode.of(file.getName(), relativePath);
    }

    private boolean isTextFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".txt") || name.endsWith(".md") || name.endsWith(".adoc");
    }

    private boolean isPDFFile(File file) {
        return file.getName().toLowerCase().endsWith(".pdf");
    }

    private record FileSystemItem(File file, FileNode fileNode) {}

}
