package wangheng.leetcode;

public class ConvertSortedListToBinarySearchTreeSolution {
    private ListNode current;
    
    public TreeNode sortedListToBST(ListNode head) {
        current = head;
        
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        
        return sortedListToBSTHelper(size);
    }
    
    private TreeNode sortedListToBSTHelper(int size) {
        if (size <= 0) return null;
        
        TreeNode left = sortedListToBSTHelper(size/2);
        TreeNode root = new TreeNode(current.val);
        current = current.next;
        TreeNode right = sortedListToBSTHelper(size - size/2 - 1);
        
        root.left = left;
        root.right = right;
        
        return root;    
    }
}
