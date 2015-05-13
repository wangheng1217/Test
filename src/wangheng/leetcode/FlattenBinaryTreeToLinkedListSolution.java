package wangheng.leetcode;

import java.util.Stack;

public class FlattenBinaryTreeToLinkedListSolution {
    private TreeNode lastNode = null;

    public void flatten3(TreeNode root) {
        if (root == null) {
            return;
        }

        if (lastNode != null) {
            lastNode.left = null;
            lastNode.right = root;
        }

        lastNode = root;
        TreeNode right = root.right;
        flatten3(root.left);
        flatten3(right);
    }
    
    public void flatten2(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        TreeNode curr = null;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
            if (curr == null) {
                curr = node;
            } else {
                curr.left = null;
                curr.right = node;
                curr = node;
            }
        }
    }

    public void flatten(TreeNode root) {
        if (root == null)
            return;

        flatten(root.left);
        flatten(root.right);
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = null;
        TreeNode node = root;
        while (node.right != null) {
            node = node.right;
        }
        node.right = temp;
    }

}
