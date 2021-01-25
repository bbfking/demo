package com.pfxiong.algorithm;

import org.junit.Test;

/**
 * @author: pfXiong
 * @datetime: 2021/1/23 17:08
 * @description:
 */
public class TreeTest {

    class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int value) {
            this.value = value;
        }
    }
    public void isSubTree() {
        TreeNode root1 = new TreeNode();
        TreeNode root2 = new TreeNode();

    }

    @Test
    public void reverseTree() {
        TreeNode root = create();
        TreeNode rs = reverse(root);
    }

    private TreeNode reverse(TreeNode treeNode) {
        if (treeNode == null) {
            return null;
        }
        TreeNode left = treeNode.left;
        TreeNode right = treeNode.right;
        treeNode.left = reverse(right);
        treeNode.right = reverse(left);
        return treeNode;
    }
    private boolean isSubTree(TreeNode t1, TreeNode t2) {
        boolean result = false;
        return result;
    }

    private TreeNode create() {
        TreeNode t3 = new TreeNode(3);
        TreeNode t2 = new TreeNode(2);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        t3.left = t2;
        t3.right = t5;
        t5.left = t4;
        t5.right = t6;
        return t3;
    }


}