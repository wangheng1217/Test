package wangheng.leetcode;

public class RemoveDuplicatesFromSortedListSolution {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return head;
        ListNode pre = head;
        ListNode curr = head.next;
        while (curr != null) {
            if (curr.val != pre.val) {
                pre.next = curr;
                pre = curr;
            }
            curr = curr.next;
        }
        pre.next = null;
        return head;
    }

}
