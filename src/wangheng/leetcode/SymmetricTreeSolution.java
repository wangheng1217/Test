package wangheng.leetcode;

public class SymmetricTreeSolution {

    public boolean isSymmetricIteration(TreeNode root) {
        if (root == null)
            return true;

        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;
        java.util.Stack<TreeNode> leftStack = new java.util.Stack<TreeNode>();
        java.util.Stack<TreeNode> rightStack = new java.util.Stack<TreeNode>();
        while (leftNode != null || !leftStack.isEmpty()) {
            while (leftNode != null) {
                if (rightNode == null)
                    return false;

                leftStack.push(leftNode);
                rightStack.push(rightNode);

                leftNode = leftNode.left;
                rightNode = rightNode.right;
            }

            if (rightNode != null)
                return false;

            if (!leftStack.isEmpty() && !rightStack.isEmpty()) {
                leftNode = leftStack.pop();
                rightNode = rightStack.pop();

                if (leftNode.val != rightNode.val)
                    return false;

                leftNode = leftNode.right;
                rightNode = rightNode.left;
            } else {
                return false;
            }
        }
        return (rightNode == null && rightStack.isEmpty());
    }

    public boolean isSymmetricRecursion(TreeNode root) {
        if (root == null)
            return true;
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2 == null;
        } else {
            if (root2 == null)
                return false;
            else {
                return root1.val == root2.val
                        && isSymmetric(root1.left, root2.right)
                        && isSymmetric(root1.right, root2.left);
            }
        }
    }

}
