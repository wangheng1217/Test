package wangheng.leetcode;

//TODO solution using preorder traversal
public class MaximumDepthOfBinaryTreeSolution {

    public int maxDepth3(TreeNode root) {
        if (root == null)
            return 0;

        java.util.ArrayList<TreeNode> preLevelList = new java.util.ArrayList<TreeNode>();
        preLevelList.add(root);

        int maxDepth = 0;

        while (!preLevelList.isEmpty()) {
            maxDepth++;

            java.util.ArrayList<TreeNode> currLevelList = new java.util.ArrayList<TreeNode>();
            for (int i = 0; i < preLevelList.size(); i++) {
                TreeNode node = preLevelList.get(i);
                if (node.left != null) {
                    currLevelList.add(node.left);
                }
                if (node.right != null) {
                    currLevelList.add(node.right);
                }
            }

            preLevelList = currLevelList;
        }

        return maxDepth;
    }

    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        return 1 + max(maxDepth(root.left), maxDepth(root.right));
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

}
