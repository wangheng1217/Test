package wangheng.leetcode;

public class ValidateBinarySearchTreeSolution {

    public boolean isValidBST(TreeNode root) {
        java.util.Stack<TreeNode> stack = new java.util.Stack<TreeNode>();
        TreeNode node = root;
        Integer curr = null;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            if (!stack.isEmpty()) {
                node = stack.pop();

                if (curr == null) {
                    curr = node.val;
                } else {
                    if (node.val <= curr.intValue()) {
                        return false;
                    } else {
                        curr = node.val;
                    }
                }

                node = node.right;
            }
        }
        return true;
    }

}
