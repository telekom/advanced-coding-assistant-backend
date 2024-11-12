package com.telekom.ai4coding.chatbot.template.prompt;

public class StateMachineRetrievalPromptTemplates {

    private StateMachineRetrievalPromptTemplates(){}

    public static final String EXCEPTION_OCCURRED_KEYWORDS_MESSAGE = """
                During your last attempt at extracting keywords, this exception occurred:
                ```
                %s
                ```
                """;

    public static final String EXCEPTION_OCCURRED_BY_LLM_MESSAGE = """
                During your last attempt at creating Cypher query, this exception occurred:
                ```
                %s
                ```
                """;

    public static final String CYPHER_PREVIOUS_ATTEMPT = """
                This was your previous attempt at creating a Cypher query. Create a new one:
                ```
                %s
                ```
                """;

    public static final String CREATE_CYPHER_TEMPLATE = """
            You have access to a Neo4J Graph database for a codebase representation with these nodes:
            - FileNode
            - ASTNode
            - TextNode
             
            This is the schema as represented by Spring Data Neo4J Nodes:
            ```
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
            }
            ```
            Example of a `FileNode`:
            ```
                {
                  "identity": 22155,
                  "labels": [
                    "FileNode"
                  ],
                  "properties": {
                    "basename": "openai_openapi.yaml",
                    "relativePath": "openai-api\\openai_openapi.yaml"
                  },
                  "elementId": "4:46c590ba-ec57-464d-ad94-b0ca0c548d5d:22155"
                }
            ```
            ---
            ```
            **
             * Represents an Abstract Syntax Tree node from tree-sitter.
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
            
                /**
                 * An edge from a ASTNode to an ASTNode, that connects the parent AST node to the child AST node.
                 */
                @Relationship(type = "PARENT_OF", direction = Relationship.Direction.OUTGOING)
                private List<ASTNode> childASTNodes;
            }
            Example of an `ASTNode`:
            ```
                {
                  "identity": 7,
                  "labels": [
                    "ASTNode"
                  ],
                  "properties": {
                    "endLine": 31,
                    "startLine": 31,
                    "text": "@ExtendWith(MockitoExtension.class)",
                    "type": "annotation"
                  },
                  "elementId": "4:46c590ba-ec57-464d-ad94-b0ca0c548d5d:7"
                }
            ```
            ---
            ```
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
            
                /**
                 * An edge from a TextNode to the next TextNode that contains continuation of the text.
                 * Mutating a Node for performance, but has to stay only package accessible!
                 * https://docs.spring.io/spring-data/neo4j/reference/object-mapping/sdc-object-mapping.html#mapping.fundamentals.recommendations
                 */
                @Setter(AccessLevel.PACKAGE)
                @Relationship(type = "NEXT_CHUNK", direction = Relationship.Direction.OUTGOING)
                private TextNode nextTextNode;
            }
            ```    
            Example of an `TextNode`:
            ```
                {
                  "identity": 8940,
                  "labels": [
                    "TextNode"
                  ],
                  "properties": {
                    "metadata": "Metadata { metadata = {index=0} }",
                    "text": "The art of urban gardening has evolved significantly in recent years, ..."
                  },
                  "elementId": "4:46c590ba-ec57-464d-ad94-b0ca0c548d5d:8940"
                }
            ```
            ---
            These are all the `FileNode.relativePath` in the database:
            ```
            %s
            ```
            ---
            These are all the `ASTNode.type` in the database:
            ```
            %s
            ```
            ---
            
            I will now specify your task. !!!THIS IS VERY IMPORTANT!!!
            
            Your task is to create a Cypher query from the provided user query, which is in XML tags `</userQuery>`.
            
            The Cypher query that you create will be used to search the above described database which represents a 
            codebase in a Knowledge Graph.          
            
            Make the Cypher query as specific as possible based on all the information you have about the schema, 
            details of the codebase like file names and AST types and based on the user query which is in XML tags `</userQuery>`,
            because due to length constraints, the result will be significantly pruned. 
             
            Your response MUST be a pure Cypher query and nothing else. Do NOT use any markdown in your response!
            I repeat, your response MUST be a pure Cypher query and nothing else.
             
            Your response will be executed by Neo4J Database.
             
             ```
             <userQuery>
             %s
             </userQuery>
             ```
            """;

    public static final String EXTRACT_KEYWORDS_TEMPLATE = """
            You have access to a Neo4J Graph database for a codebase representation with these nodes:
            - FileNode
            - ASTNode
            - TextNode
             
            This is the schema as represented by Spring Data Neo4J Nodes:
            ```
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
            }
            ```
            Example of a `FileNode`:
            ```
                {
                  "identity": 22155,
                  "labels": [
                    "FileNode"
                  ],
                  "properties": {
                    "basename": "openai_openapi.yaml",
                    "relativePath": "openai-api\\openai_openapi.yaml"
                  },
                  "elementId": "4:46c590ba-ec57-464d-ad94-b0ca0c548d5d:22155"
                }
            ```
            ---
            ```
            **
             * Represents an Abstract Syntax Tree node from tree-sitter.
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
            
                /**
                 * An edge from a ASTNode to an ASTNode, that connects the parent AST node to the child AST node.
                 */
                @Relationship(type = "PARENT_OF", direction = Relationship.Direction.OUTGOING)
                private List<ASTNode> childASTNodes;
            }
            Example of an `ASTNode`:
            ```
                {
                  "identity": 7,
                  "labels": [
                    "ASTNode"
                  ],
                  "properties": {
                    "endLine": 31,
                    "startLine": 31,
                    "text": "@ExtendWith(MockitoExtension.class)",
                    "type": "annotation"
                  },
                  "elementId": "4:46c590ba-ec57-464d-ad94-b0ca0c548d5d:7"
                }
            ```
            ---
            ```
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
            
                /**
                 * An edge from a TextNode to the next TextNode that contains continuation of the text.
                 * Mutating a Node for performance, but has to stay only package accessible!
                 * https://docs.spring.io/spring-data/neo4j/reference/object-mapping/sdc-object-mapping.html#mapping.fundamentals.recommendations
                 */
                @Setter(AccessLevel.PACKAGE)
                @Relationship(type = "NEXT_CHUNK", direction = Relationship.Direction.OUTGOING)
                private TextNode nextTextNode;
            }
            ```    
            Example of an `TextNode`:
            ```
                {
                  "identity": 8940,
                  "labels": [
                    "TextNode"
                  ],
                  "properties": {
                    "metadata": "Metadata { metadata = {index=0} }",
                    "text": "The art of urban gardening has evolved significantly in recent years, ..."
                  },
                  "elementId": "4:46c590ba-ec57-464d-ad94-b0ca0c548d5d:8940"
                }
            ```
            ---
            These are all the `FileNode.relativePath` in the database:
            ```
            %s
            ```
            ---
            These are all the `ASTNode.type` in the database:
            ```
            %s
            ```
            ---
            
            I will now specify your task. !!!THIS IS VERY IMPORTANT!!!
            
            Your task is to extract all keywords from the provided user query, which is in XML tags `</userQuery>`.
            
            The keywords that you extract will be used to search the above described database which represents a codebase
            in a Knowledge Graph.
            
            Therefore you must extract different variations of the keywords, meaning:
             - standard case
             - camelCase
             - PascalCase
             - snake_case
             - kebab-case
             - flatcase
             - etc...
             
             Your response MUST be in a pure JSON format and nothing else. Do NOT use any markdown in your response!
             I repeat, your response MUST be in a pure JSON format and nothing else:
             {
                "keywords": ["keyword1", "keyword2", "keyword3", "keyword4" ...]
             }
             
             Your response will be parsed by a JSON parser, so your response MUST CONTAIN ONLY a JSON specified above!
             
             ```
             <userQuery>
             %s
             </userQuery>
             ```
            """;
}
