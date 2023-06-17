Task 3 -

CREATE TABLE tree_node (
id VARCHAR(255) PRIMARY KEY,
label VARCHAR(255) NOT NULL,
parent VARCHAR(255),
FOREIGN KEY (parent) REFERENCES tree_node(id)
);

The tree_node table represents each node in the tree. It has columns for the id, label, and parent fields from the TreeNode model. 
The id column is the unique identifier for each node, the label column stores the label of the node, and the parent column establishes 
the parent-child relationship between nodes.
By using the parent column to reference the id column of the parent node, you can represent the hierarchical structure of the tree in the database.


Task 4 -

1 - This query fetches all nodes from the tree_node table:
SELECT * FROM tree_node;

2 - Add a new node:
INSERT INTO tree_node (id, label, parent) VALUES (?, ?, ?);



