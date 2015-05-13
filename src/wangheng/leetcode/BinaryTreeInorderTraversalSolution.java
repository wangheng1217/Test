package wangheng.leetcode;

import java.util.ArrayList;
import java.util.Stack;

public class BinaryTreeInorderTraversalSolution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);
        root.left.right.right = new TreeNode(9);
        root.right.left.left = new TreeNode(10);
        root.right.right.right = new TreeNode(11);
        root.left.left.right = new TreeNode(12);
        root.right.right.left = new TreeNode(13);
        root.left.left.right.left = new TreeNode(14);
        root.left.left.right.right = new TreeNode(15);
        root.right.right.left.left = new TreeNode(16);
        root.right.right.left.right = new TreeNode(17);

        BinaryTreeInorderTraversalSolution solution = new BinaryTreeInorderTraversalSolution();
        solution.print(solution.inorderTraversal(root));
        solution.print(solution.inorderTraversalRecursion(root));
        solution.print(solution.preorderTraversal(root));
        solution.print(solution.preorderTraversalRecursion(root));
        solution.print(solution.postorderTraversal(root));
        solution.print(solution.postorderTraversalRecursion(root));
    }

    public void print(ArrayList<Integer> res) {
        for (int i = 0; i < res.size(); i++) {
            System.out.print(res.get(i) + " ");
        }
        System.out.println();
    }

    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                stack.push(node);
                node = node.left;
            }

            if (!stack.isEmpty()) {
                node = stack.pop();
            }

            if (!stack.isEmpty() && node == stack.peek()) {
                node = node.right;
            } else {
                res.add(node.val);
                node = null;
            }
        }
        return res;
    }

    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }

            if (!stack.isEmpty()) {
                node = stack.pop();
            }

            node = node.right;
        }
        return res;
    }

    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            if (!stack.isEmpty()) {
                node = stack.pop();
            }

            res.add(node.val);
            node = node.right;
        }
        return res;
    }

    public ArrayList<Integer> inorderTraversal2(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (root != null) {
            while (root != null) {
                if (root.right != null) {
                    stack.push(root.right);
                }
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            while (!stack.isEmpty() && root.right == null) {
                res.add(root.val);
                root = stack.pop();
            }

            res.add(root.val);

            if (stack.isEmpty()) {
                root = null;
            } else {
                root = stack.pop();
            }
        }
        return res;
    }

    public ArrayList<Integer> postorderTraversalRecursion(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        postorderTraversalRecursion(root, res);
        return res;
    }

    private void postorderTraversalRecursion(TreeNode root,
            ArrayList<Integer> res) {
        if (root == null)
            return;
        postorderTraversalRecursion(root.left, res);
        postorderTraversalRecursion(root.right, res);
        res.add(root.val);
    }

    public ArrayList<Integer> preorderTraversalRecursion(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        preorderTraversalRecursion(root, res);
        return res;
    }

    private void preorderTraversalRecursion(TreeNode root,
            ArrayList<Integer> res) {
        if (root == null)
            return;
        res.add(root.val);
        preorderTraversalRecursion(root.left, res);
        preorderTraversalRecursion(root.right, res);
    }

    public ArrayList<Integer> inorderTraversalRecursion(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        inorderTraversalRecursion(root, res);
        return res;
    }

    private void inorderTraversalRecursion(TreeNode root, ArrayList<Integer> res) {
        if (root == null)
            return;
        inorderTraversalRecursion(root.left, res);
        res.add(root.val);
        inorderTraversalRecursion(root.right, res);
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
