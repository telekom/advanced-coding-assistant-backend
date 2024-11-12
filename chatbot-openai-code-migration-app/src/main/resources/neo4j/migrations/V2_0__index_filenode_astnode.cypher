CREATE INDEX file_node_id_index IF NOT EXISTS FOR (n:FileNode) ON (n.id);
CREATE INDEX ast_node_id_index IF NOT EXISTS FOR (n:ASTNode) ON (n.id);