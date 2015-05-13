package wangheng.leetcode;

public class RotateListSolution {

    public ListNode rotateRight(ListNode head, int n) {
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        if (length == 0)
            return head;
        int realN = n % length;
        if (realN == 0)
            return head;

        ListNode newHead = head;
        for (int i = 0; i < length - realN; i++) {
            if (i == length - realN - 1) {
                ListNode temp = newHead;
                newHead = newHead.next;
                temp.next = null;
            } else {
                newHead = newHead.next;
            }
        }

        curr = newHead;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = head;

        return newHead;
    }

}
