package wangheng.leetcode;

import java.util.ArrayList;

public class BinaryTreeLevelOrderTraversalSolution {

    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (root == null) {
            return res;
        }

        ArrayList<TreeNode> preLevelList = new ArrayList<TreeNode>();
        preLevelList.add(root);

        while (!preLevelList.isEmpty()) {
            ArrayList<TreeNode> levelList = new ArrayList<TreeNode>();

            ArrayList<Integer> intList = new ArrayList<Integer>();

            for (int i = 0; i < preLevelList.size(); i++) {
                TreeNode node = preLevelList.get(i);

                intList.add(node.val);

                if (node.left != null) {
                    levelList.add(node.left);
                }
                if (node.right != null) {
                    levelList.add(node.right);
                }
            }

            res.add(intList);

            preLevelList = levelList;
        }

        return res;
    }

}
