package wangheng.leetcode;

// the same solution for II
public class PopulatingNextRightPointersInEachNodeSolution {

    public void connect(TreeLinkNode root) {
        TreeLinkNode node = root;
        while (node != null) {
            TreeLinkNode head = null;
            TreeLinkNode pre = null;

            while (node != null) {
                if (node.left != null) {
                    if (head == null) {
                        head = node.left;
                        pre = node.left;
                    } else {
                        pre.next = node.left;
                        pre = node.left;
                    }
                }

                if (node.right != null) {
                    if (head == null) {
                        head = node.right;
                        pre = node.right;
                    } else {
                        pre.next = node.right;
                        pre = node.right;
                    }
                }

                node = node.next;
            }

            node = head;
        }
    }

}

/**
 * Definition for binary tree with next pointer.
 */
class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;

    TreeLinkNode(int x) {
        val = x;
    }
}
