package wangheng.leetcode;

public class BinaryTreeMaximumPathSumSolution {

    public static void main(String[] args) {
        BinaryTreeMaximumPathSumSolution solution = new BinaryTreeMaximumPathSumSolution();
        TreeNode root = new TreeNode(-3);
        System.out.println(solution.maxPathSum(root));
    }

    public int maxPathSum(TreeNode root) {
        int[] maxPath = new int[1];
        maxPath[0] = Integer.MIN_VALUE;
        maxChildPath(root, maxPath);
        return maxPath[0];
    }

    private int maxChildPath(TreeNode root, int[] maxPath) {
        if (root == null)
            return 0;
        int left = max(0, maxChildPath(root.left, maxPath));
        int right = max(0, maxChildPath(root.right, maxPath));
        maxPath[0] = max(maxPath[0], root.val + left + right);
        return root.val + max(left, right);
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

}
