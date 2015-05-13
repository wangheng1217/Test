package wangheng.leetcode;

public class PartitionListSolution {

    public ListNode partition(ListNode head, int x) {
        ListNode head1 = null, curr1 = null, head2 = null, curr2 = null;
        ListNode curr = head;
        while (curr != null) {
            if (curr.val < x) {
                if (head1 == null) {
                    head1 = curr;
                    curr1 = curr;
                } else {
                    curr1.next = curr;
                    curr1 = curr1.next;
                }
            } else {
                if (head2 == null) {
                    head2 = curr;
                    curr2 = curr;
                } else {
                    curr2.next = curr;
                    curr2 = curr2.next;
                }
            }
            curr = curr.next;
        }
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        curr1.next = head2;
        curr2.next = null;
        return head1;
    }

}
