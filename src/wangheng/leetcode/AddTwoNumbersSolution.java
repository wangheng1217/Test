package wangheng.leetcode;

public class AddTwoNumbersSolution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        int sum = l1.val + l2.val;
        result.val = sum % 10;
        int carry = sum / 10;
        l1 = l1.next;
        l2 = l2.next;

        ListNode pre = result;

        while (l1 != null || l2 != null || carry > 0) {
            sum = carry;
            if (l1 != null) {
                sum = sum + l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum = sum + l2.val;
                l2 = l2.next;
            }
            ListNode curr = new ListNode(0);
            curr.val = sum % 10;
            carry = sum / 10;
            pre.next = curr;
            pre = curr;
        }

        return result;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}
