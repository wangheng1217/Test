package wangheng.leetcode;

public class SumRootToLeafNumbersSolution {

    public int sumNumbers(TreeNode root) {
        int[] res = new int[1];
        res[0] = 0;

        if (root != null)
            dfs(root, 0, res);

        return res[0];
    }

    private void dfs(TreeNode node, int sum, int[] res) {
        int newSum = sum * 10 + node.val;

        if (node.left == null && node.right == null) {
            res[0] = res[0] + newSum;
            return;
        }

        if (node.left != null) {
            dfs(node.left, newSum, res);
        }

        if (node.right != null) {
            dfs(node.right, newSum, res);
        }
    }

}
