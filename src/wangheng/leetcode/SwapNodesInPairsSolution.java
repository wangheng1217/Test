package wangheng.leetcode;

public class SwapNodesInPairsSolution {
    
    public ListNode swapPairs2(ListNode head) {
        ListNode newHead = null, pre = null;
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            if (newHead == null) {
                newHead = curr.next;
                curr.next = curr.next.next;
                newHead.next = curr;
                pre = curr;
                curr = pre.next;
            } else {
                pre.next = curr.next;
                curr.next = curr.next.next;
                pre.next.next = curr;
                pre = curr;
                curr = pre.next;
            }
        }
        
        if (newHead == null) newHead = head;
        
        return newHead;
    }


    public ListNode swapPairs(ListNode head) {
        if (head == null)
            return null;
        if (head.next == null)
            return head;
        if (head.next.next == null) {
            ListNode newHead = head.next;
            newHead.next = head;
            head.next = null;
            return newHead;
        }

        ListNode curr = head.next.next;
        ListNode newHead = head.next;
        newHead.next = head;
        newHead.next.next = curr;
        ListNode pre = newHead.next;

        while (curr != null) {
            if (curr.next == null) {
                break;
            } else {
                ListNode temp = curr.next.next;
                pre.next = curr.next;
                pre.next.next = curr;
                pre.next.next.next = temp;
                curr = pre.next.next.next;
                pre = pre.next.next;
            }
        }
        return newHead;
    }

}
