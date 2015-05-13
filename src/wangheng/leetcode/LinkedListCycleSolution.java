package wangheng.leetcode;

public class LinkedListCycleSolution {
    public boolean hasCycle(ListNode head) {
        if (head == null)
            return false;

        ListNode fast = head, slow = head;

        do {
            if (fast.next == null || fast.next.next == null) {
                return false;
            }

            fast = fast.next.next;
            slow = slow.next;
        } while (fast != slow);

        return true;
    }
}
