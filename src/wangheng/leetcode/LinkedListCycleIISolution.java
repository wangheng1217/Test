package wangheng.leetcode;

public class LinkedListCycleIISolution {
    public ListNode detectCycle(ListNode head) {
        if (head == null)
            return null;

        ListNode slow = head, fast = head;

        do {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }

            slow = slow.next;
            fast = fast.next.next;
        } while (slow != fast);

        // if has cycle, then head and slow must be able to meet at slow's
        // current position, then must already met at the start of the cycle
        while (head != slow) {
            head = head.next;
            slow = slow.next;
        }

        return head;
    }
}
