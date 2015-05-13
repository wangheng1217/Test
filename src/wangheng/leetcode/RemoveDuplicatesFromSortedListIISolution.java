package wangheng.leetcode;

public class RemoveDuplicatesFromSortedListIISolution {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode newHead = null;
        ListNode newCurr = null;
        ListNode curr = head;
        boolean duplicated = false;
        while (curr != null) {
            if (curr.next != null && curr.next.val == curr.val) {
                duplicated = true;
            } else {
                if (duplicated) {
                    duplicated = false;
                } else {
                    if (newHead == null) {
                        newHead = curr;
                        newCurr = curr;
                    } else {
                        newCurr.next = curr;
                        newCurr = newCurr.next;
                    }
                }
            }
            curr = curr.next;
        }
        if (newCurr != null)
            newCurr.next = null;
        return newHead;
    }

}
