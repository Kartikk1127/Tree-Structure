package com.hingehealth.demo.controllers;

import com.hingehealth.demo.exceptionhandler.TreeOperationException;
import com.hingehealth.demo.model.TreeNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TreeController {
    private TreeNode root; // Represents the root of the tree

    public TreeController() {
        // Initialize the root node
        root = new TreeNode("1", "root", null, new ArrayList<>());
        TreeNode ant = new TreeNode("2", "ant", "1", new ArrayList<>());
        TreeNode cat = new TreeNode("4", "cat", "3", new ArrayList<>());
        TreeNode dog = new TreeNode("5", "dog", "3", new ArrayList<>());
        TreeNode elephant = new TreeNode("6", "elephant", "5", new ArrayList<>());
        TreeNode bear = new TreeNode("3", "bear", "1", List.of(cat, dog));
        TreeNode frog = new TreeNode("7", "frog", "1", new ArrayList<>());

        root.getChildren().add(ant);
        root.getChildren().add(bear);
        root.getChildren().add(frog);
        dog.getChildren().add(elephant);
    }

    /*Retrieves the entire tree*/
    @GetMapping("/tree")
    public ResponseEntity<?> getTree() {
        try {
            List<TreeNode> tree = new ArrayList<>();
            tree.add(root);
            return ResponseEntity.ok(tree);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    /*Adds a new node to the tree*/
    @PostMapping("/tree")
    public ResponseEntity<?> addNode(@RequestBody TreeNode newNode) {
        try {
            String newNodeId = generateUniqueId();
            TreeNode parentNode = findNodeById(newNode.getParent());
            if (parentNode != null) {
                newNode.setId(newNodeId);
                parentNode.getChildren().add(newNode);
                return ResponseEntity.ok().build();
            } else {
                throw new TreeOperationException("Parent node not found.");
            }
        } catch (Exception e) {
            return handleException(e);
        }
    }

    /*Exception handler for TreeOperationException that handles exceptions specific to tree operations.*/
    @ExceptionHandler(TreeOperationException.class)
    public ResponseEntity<?> handleTreeOperationException(TreeOperationException ex) {
        String errorMessage = "Tree Operation Error: " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    /*Generic exception handler that handles exceptions other than TreeOperationException*/
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        String errorMessage = "An error occurred: " + ex.getMessage();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (ex instanceof TreeOperationException) {
            status = HttpStatus.BAD_REQUEST;
        }

        return ResponseEntity.status(status).body(errorMessage);
    }

    /*Finds a node in the tree by its ID*/
    public TreeNode findNodeById(String id) {
        return findNodeByIdRecursive(root, id);
    }

    /*Recursive helper method to find a node by ID*/
    private TreeNode findNodeByIdRecursive(TreeNode currentNode, String id) {
        if (currentNode == null) {
            return null;
        }

        if (currentNode.getId().equals(id)) {
            return currentNode;
        }

        for (TreeNode child : currentNode.getChildren()) {
            TreeNode foundNode = findNodeByIdRecursive(child, id);
            if (foundNode != null) {
                return foundNode;
            }
        }

        return null;
    }

    /*Generates a unique ID for a new node*/
    private String generateUniqueId() {
        // Logic to generate a unique id for a new node
        // You can use a UUID or any other suitable method
        // For simplicity, let's assume the ids are sequential
        UUID uniqueId = UUID.randomUUID();
        return uniqueId.toString();

    }

    /*Finds the maximum ID in the given node and its children recursively*/
    private int findMaxId(TreeNode node) {
        int maxId = Integer.parseInt(node.getId());

        for (TreeNode child : node.getChildren()) {
            int childId = findMaxId(child);
            if (childId > maxId) {
                maxId = childId;
            }
        }

        return maxId;
    }
}
