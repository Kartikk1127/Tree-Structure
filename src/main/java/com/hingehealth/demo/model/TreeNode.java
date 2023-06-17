package com.hingehealth.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {
    private String id;
    private String label;
    private String parent;
    private List<TreeNode> children;
}
