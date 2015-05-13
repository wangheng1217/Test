package wangheng.leetcode;

import java.util.LinkedList;

public class MinimumDepthOfBinaryTreeSolution {

    public int minDepth3(TreeNode root) {
        if (root == null) return 0;
        
        LinkedList<TreeNode> list = new LinkedList<TreeNode>();
        list.add(root);
        int depth = 1;
        
        while (!list.isEmpty()) {
            int length = list.size();
            for (int i = 0; i < length; i++) {
                TreeNode node = list.pollFirst();
                
                if (node.left == null && node.right == null) return depth;
                
                if (node.left != null) list.addLast(node.left);
                if (node.right != null) list.addLast(node.right);
            }
            
            depth++; 
        }
        
        return depth;
    }
    
    public int minDepth2(TreeNode root) {
        if (root == null)
            return 0;

        java.util.ArrayList<TreeNode> preLevelList = new java.util.ArrayList<TreeNode>();
        preLevelList.add(root);

        int depth = 0;

        while (!preLevelList.isEmpty()) {
            depth++;

            java.util.ArrayList<TreeNode> currLevelList = new java.util.ArrayList<TreeNode>();

            for (int i = 0; i < preLevelList.size(); i++) {
                TreeNode node = preLevelList.get(i);

                if (node.left == null && node.right == null) {
                    return depth;
                } else {
                    if (node.left != null)
                        currLevelList.add(node.left);
                    if (node.right != null)
                        currLevelList.add(node.right);
                }
            }

            preLevelList = currLevelList;
        }

        return depth;
    }

    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;

        if (root.left == null) {
            return 1 + minDepth(root.right);
        }

        if (root.right == null) {
            return 1 + minDepth(root.left);
        }

        return 1 + min(minDepth(root.left), minDepth(root.right));
    }

    private int min(int a, int b) {
        return a < b ? a : b;
    }

}
