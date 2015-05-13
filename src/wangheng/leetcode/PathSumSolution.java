package wangheng.leetcode;

public class PathSumSolution {
    public boolean hasPathSum3(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val == sum;
        return hasPathSum3(root.left, sum-root.val) || hasPathSum3(root.right, sum-root.val);
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null && sum == root.val)
            return true;
        if (root.left != null && hasPathSum(root.left, sum - root.val))
            return true;
        if (root.right != null && hasPathSum(root.right, sum - root.val))
            return true;
        return false;
    }

    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null && root.val == sum)
            return true;
        else
            return hasPathSum2(root.left, sum - root.val)
                    || hasPathSum2(root.right, sum - root.val);
    }
}
