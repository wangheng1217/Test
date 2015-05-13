package wangheng.leetcode;

public class ConvertSortedArrayToBinarySearchTreeSolution {

    public TreeNode sortedArrayToBST(int[] num) {
        return sortedArrayToBST(num, 0, num.length - 1);
    }

    private TreeNode sortedArrayToBST(int[] num, int start, int end) {
        if (start > end)
            return null;
        if (start == end)
            return new TreeNode(num[start]);

        int rootIndex = start + (end - start) / 2;
        TreeNode root = new TreeNode(num[rootIndex]);
        root.left = sortedArrayToBST(num, start, rootIndex - 1);
        root.right = sortedArrayToBST(num, rootIndex + 1, end);

        return root;
    }

}
