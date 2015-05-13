package wangheng.leetcode;

import java.util.ArrayList;
import java.util.List;

public class UniqueBinarySearchTreesIISolution {
    
    public static void main(String[] args) {
        System.out.println(new UniqueBinarySearchTreesSolution().numTrees(8));
        System.out.println(new UniqueBinarySearchTreesIISolution().generateTrees4(8).size());
    }
    
    public List<TreeNode> generateTrees4(int n) {
        return generateTrees(1, n);
    }
    
    private List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        
        if (start > end) {
            list.add(null);
            return list;
        }
        
        if (start == end) {
            TreeNode node = new TreeNode(start);
            list.add(node);
            return list;
        }
        
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftList = generateTrees(start, i-1);
            List<TreeNode> rightList = generateTrees(i+1, end);
            
            for (TreeNode leftNode : leftList) {
                for (TreeNode rightNode : rightList) {
                    TreeNode node = new TreeNode(i);
                    node.left = leftNode;
                    node.right = rightNode;
                    list.add(node);
                }
            }
        }
        
        return list;
    }


    public ArrayList<TreeNode> generateTrees3(int n) {
        ArrayList<TreeNode>[][] dp = new ArrayList[n + 2][n + 2];

        ArrayList<TreeNode> nullList = new ArrayList<TreeNode>();
        nullList.add(null);

        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < n + 2; j++) {
                if (i > j) {
                    dp[i][j] = nullList;
                }
            }
        }

        for (int diff = 0; diff < n; diff++) {
            for (int i = 1; i < n + 1 && i + diff < n + 1; i++) {
                ArrayList<TreeNode> res = new ArrayList<TreeNode>();
                for (int r = i; r <= i + diff; r++) {
                    ArrayList<TreeNode> leftRes = dp[i][r - 1];
                    ArrayList<TreeNode> rightRes = dp[r + 1][i + diff];
                    for (int j = 0; j < leftRes.size(); j++) {
                        for (int k = 0; k < rightRes.size(); k++) {
                            TreeNode root = new TreeNode(r);
                            root.left = leftRes.get(j);
                            root.right = rightRes.get(k);
                            res.add(root);
                        }
                    }
                }
                dp[i][i + diff] = res;
            }
        }

        return dp[1][n];
    }

    public ArrayList<TreeNode> generateTrees2(int n) {
        return generateTree(1, n);
    }

    public ArrayList<TreeNode> generateTree(int l, int r) {
        ArrayList<TreeNode> res = new ArrayList<TreeNode>();

        if (l > r) {
            res.add(null);
            return res;
        }

        for (int i = l; i <= r; i++) {
            ArrayList<TreeNode> leftRes = generateTree(l, i - 1);
            ArrayList<TreeNode> rightRes = generateTree(i + 1, r);
            for (int j = 0; j < leftRes.size(); j++) {
                for (int k = 0; k < rightRes.size(); k++) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftRes.get(j);
                    root.right = rightRes.get(k);
                    res.add(root);
                }
            }
        }

        return res;
    }

    public ArrayList<TreeNode> generateTrees(int n) {
        ArrayList<TreeNode>[] dp = new ArrayList[n + 1];

        ArrayList<TreeNode> list0 = new ArrayList<TreeNode>();
        list0.add(null);
        dp[0] = list0;

        for (int i = 1; i <= n; i++) {
            ArrayList<TreeNode> list = new ArrayList<TreeNode>();

            for (int j = 1; j <= i; j++) {
                ArrayList<TreeNode> leftList = dp[j - 1];
                ArrayList<TreeNode> rightList = dp[i - j];
                int[] array = new int[i - j];
                for (int k = 1; k <= array.length; k++) {
                    array[k - 1] = j + k;
                }
                ArrayList<TreeNode> convertRightList = new ArrayList<TreeNode>();
                for (int r = 0; r < rightList.size(); r++) {
                    convertRightList.add(convert(rightList.get(r), array));
                }

                for (int l = 0; l < leftList.size(); l++) {
                    for (int m = 0; m < convertRightList.size(); m++) {
                        TreeNode root = new TreeNode(j);
                        root.left = leftList.get(l);
                        root.right = convertRightList.get(m);
                        list.add(root);
                    }
                }
            }
            dp[i] = list;
        }

        return dp[n];
    }

    private TreeNode convert(TreeNode root, int[] array) {
        if (root == null)
            return null;
        TreeNode newRoot = new TreeNode(array[root.val - 1]);
        newRoot.left = convert(root.left, array);
        newRoot.right = convert(root.right, array);
        return newRoot;
    }

}
