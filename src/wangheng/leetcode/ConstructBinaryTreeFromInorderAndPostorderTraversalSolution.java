package wangheng.leetcode;

public class ConstructBinaryTreeFromInorderAndPostorderTraversalSolution {

    public static void main(String[] args) {
        int[] inorder = { 8, 4, 14, 12, 15, 2, 5, 9, 1, 10, 6, 3, 16, 13, 17,
                7, 11 };
        int[] postorder = { 8, 14, 15, 12, 4, 9, 5, 2, 10, 6, 16, 17, 13, 11,
                7, 3, 1 };

        ConstructBinaryTreeFromInorderAndPostorderTraversalSolution buildTreeSolution = new ConstructBinaryTreeFromInorderAndPostorderTraversalSolution();
        BinaryTreeInorderTraversalSolution traversalSolution = new BinaryTreeInorderTraversalSolution();

        TreeNode root = buildTreeSolution.buildTree(inorder, postorder);
        traversalSolution.print(traversalSolution.preorderTraversal(root));
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, postorder, 0, 0, inorder.length);
    }

    private TreeNode buildTree(int[] inorder, int[] postorder, int inIndex,
            int postIndex, int length) {
        if (length <= 0)
            return null;
        if (length == 1)
            return new TreeNode(postorder[postIndex]);

        TreeNode root = new TreeNode(postorder[postIndex + length - 1]);

        int rootInInorder = find(inorder, inIndex, length, root.val);

        root.left = buildTree(inorder, postorder, inIndex, postIndex,
                rootInInorder - inIndex);
        root.right = buildTree(inorder, postorder, rootInInorder + 1, postIndex
                + rootInInorder - inIndex, length - (rootInInorder - inIndex)
                - 1);

        return root;
    }

    private int find(int[] array, int startIndex, int length, int n) {
        for (int i = startIndex; i < startIndex + length; i++) {
            if (array[i] == n)
                return i;
        }
        return -1;
    }

}
