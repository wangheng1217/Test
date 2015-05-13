package wangheng.leetcode;

public class BalancedBinaryTreeSolution {

    public boolean isBalanced(TreeNode root) {
        return isBalancedAndGetDepth(root) >= 0;
    }
    
    // -1 indicates false
    private int isBalancedAndGetDepth(TreeNode root) {
        if (root == null) return 0;
        
        int l = isBalancedAndGetDepth(root.left);
        if (l == -1) return -1;
        
        int r = isBalancedAndGetDepth(root.right);
        if (r == -1) return -1;
        
        if (Math.abs(l-r) > 1) return -1;
        
        return Math.max(l, r) + 1;
    }

}
