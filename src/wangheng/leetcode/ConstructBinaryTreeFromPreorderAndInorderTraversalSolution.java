package wangheng.leetcode;

public class ConstructBinaryTreeFromPreorderAndInorderTraversalSolution {

    public static void main(String[] args) {
        int[] preorder = { 1, 2, 4, 8, 12, 14, 15, 5, 9, 3, 6, 10, 7, 13, 16,
                17, 11 };
        int[] inorder = { 8, 4, 14, 12, 15, 2, 5, 9, 1, 10, 6, 3, 16, 13, 17,
                7, 11 };
        ConstructBinaryTreeFromPreorderAndInorderTraversalSolution buildTreeSolution = new ConstructBinaryTreeFromPreorderAndInorderTraversalSolution();
        BinaryTreeInorderTraversalSolution traversalSolution = new BinaryTreeInorderTraversalSolution();

        TreeNode root = buildTreeSolution.buildTree(preorder, inorder);

        traversalSolution.print(traversalSolution.postorderTraversal(root));
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder, 0, 0, preorder.length);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int preIndex,
            int inIndex, int length) {
        if (length <= 0)
            return null;
        if (length == 1)
            return new TreeNode(preorder[preIndex]);

        TreeNode root = new TreeNode(preorder[preIndex]);

        int rootInInorder = find(inorder, inIndex, length, preorder[preIndex]);

        root.left = buildTree(preorder, inorder, preIndex + 1, inIndex,
                rootInInorder - inIndex);
        root.right = buildTree(preorder, inorder, preIndex + 1 + rootInInorder
                - inIndex, rootInInorder + 1, length
                - (rootInInorder - inIndex) - 1);

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
