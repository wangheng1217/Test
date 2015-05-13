package wangheng.leetcode;

public class RemoveNthFromEndSolution {
    
    // tow pointers
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n == 0)
            return head;
        ListNode curr = head;
        for (int i = 2; i <= n; i++) {
            curr = curr.next;
        }

        // remove the head
        if (curr.next == null) {
            return head.next;
        }

        ListNode pre = head;
        curr = curr.next;
        while (curr.next != null) {
            curr = curr.next;
            pre = pre.next;
        }

        pre.next = pre.next.next;

        return head;
    }

    /*
    public ListNode removeNthFromEnd(ListNode head, int n) {
        java.util.ArrayList<ListNode> list = new java.util.ArrayList<ListNode>();
        ListNode curr = head;
        while (curr != null) {
            list.add(curr);
            curr = curr.next;
        }
        if (n == 0)
            return head;
        if (n == 1) {
            if (list.size() > 1) {
                list.get(list.size() - 2).next = null;
                return head;
            } else {
                return null;
            }
        }
        if (n == list.size())
            return list.get(1);
        ListNode pre = list.get(list.size() - n - 1);
        pre.next = pre.next.next;
        return head;
    }
    */
}
