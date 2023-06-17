package com.hingehealth.demo.controllers;

import com.hingehealth.demo.model.TreeNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TreeControllerTest {

    private TreeController treeController;

    @BeforeEach
    public void setup() {
        treeController = new TreeController();
    }

    @Test
    public void testGetTree() {
        ResponseEntity<?> response = treeController.getTree();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        // Add additional assertions to verify the response
    }

    @Test
    public void testAddNode() {
        TreeNode newNode = new TreeNode("8", "rabbit", "2", new ArrayList<>());
        ResponseEntity<?> response = treeController.addNode(newNode);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Verifying that the node was added to the tree
        TreeNode addedNode = treeController.findNodeById(newNode.getId());
        assertNotNull(addedNode);
        assertEquals(newNode.getId(), addedNode.getId());
        assertEquals(newNode.getParent(), addedNode.getParent());
    }

    @Test
    public void testAddNodeInvalidParent() {
        TreeNode newNode = new TreeNode("9", "turtle", "10", new ArrayList<>());
        ResponseEntity<?> response = treeController.addNode(newNode);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        // Verifying that the appropriate exception was thrown
        String errorMessage = (String) response.getBody();
        assertNotNull(errorMessage);
        assertTrue(errorMessage.contains("Parent node not found."));
    }
}
