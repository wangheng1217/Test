package wangheng.leetcode;

public class MergeTwoSortedListsSolution {
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        
        ListNode head, pre;
        if (l1.val < l2.val) {
            head = l1;
            pre = l1;
            l1 = l1.next;
        } else {
            head = l2;
            pre = l2;
            l2 = l2.next;
        }
        
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                pre.next = l1;
                pre = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                pre = l2;
                l2 = l2.next;
            }
        }
        
        if (l1 == null) pre.next = l2;
        else pre.next = l1;
        
        return head;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode pre = null;

        ListNode curr1 = l1;
        ListNode curr2 = l2;
        while (curr1 != null && curr2 != null) {
            if (curr1.val < curr2.val) {
                if (head == null) {
                    head = curr1;
                    pre = curr1;
                    curr1 = curr1.next;
                } else {
                    pre.next = curr1;
                    pre = curr1;
                    curr1 = curr1.next;
                }
            } else {
                if (head == null) {
                    head = curr2;
                    pre = curr2;
                    curr2 = curr2.next;
                } else {
                    pre.next = curr2;
                    pre = curr2;
                    curr2 = curr2.next;
                }
            }
        }

        if (curr1 == null) {
            if (head == null) {
                head = curr2;
            } else {
                pre.next = curr2;
            }
        } else {
            if (head == null) {
                head = curr1;
            } else {
                pre.next = curr1;
            }
        }

        return head;
    }

}
