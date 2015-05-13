package wangheng.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeLevelOrderTraversalIISolution {

    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        if (root == null) return result;
        
        LinkedList<TreeNode> nodeList = new LinkedList<TreeNode>();
        nodeList.add(root);
        
        while (!nodeList.isEmpty()) {
            int length = nodeList.size();
            List<Integer> intList = new ArrayList<Integer>(length);
            for (int i = 0; i < length; i++) {
                TreeNode node = nodeList.pollFirst();
                intList.add(node.val);
                if (node.left != null) nodeList.addLast(node.left);
                if (node.right != null) nodeList.addLast(node.right);
            }
            result.add(0, intList);
        }
        
        return result;
    }

    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

        if (root == null)
            return res;

        ArrayList<TreeNode> preNodeList = new ArrayList<TreeNode>();
        preNodeList.add(root);

        while (!preNodeList.isEmpty()) {
            ArrayList<TreeNode> currNodeList = new ArrayList<TreeNode>();
            ArrayList<Integer> intList = new ArrayList<Integer>();

            for (int i = 0; i < preNodeList.size(); i++) {
                TreeNode node = preNodeList.get(i);

                intList.add(node.val);

                if (node.left != null)
                    currNodeList.add(node.left);
                if (node.right != null)
                    currNodeList.add(node.right);
            }

            preNodeList = currNodeList;
            res.add(0, intList);
        }

        return res;
    }

}
