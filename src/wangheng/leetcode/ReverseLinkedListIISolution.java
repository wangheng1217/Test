package wangheng.leetcode;

public class ReverseLinkedListIISolution {
    public ListNode reverseBetween2(ListNode head, int m, int n) {
        if (m == n) return head;
        
        ListNode curr = head;
        ListNode pre = null;
        ListNode theTail = null, theCurr = null;
        int i = 1;
        while (curr != null) {
            if (i < m) {
                pre = curr;
                curr = curr.next;
            } else if (i == m) {
                theTail = curr;
                theCurr = curr;
                curr = curr.next;
            } else if (i > m && i < n) {
                ListNode tmp = curr.next;
                curr.next = theCurr;
                theCurr = curr;
                curr = tmp;
            } else if (i == n) {
                ListNode tmp = curr.next;
                curr.next = theCurr;
                if (pre != null) pre.next = curr;
                else head = curr;
                theTail.next = tmp;
                break;
            }
            
            i++;
        }
        
        return head;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n)
            return head;

        ListNode curr = head;
        ListNode tailOfHead = null;
        int i = 1;

        ListNode tailR = null, headR = null, preR = null;
        while (curr != null & i <= n) {

            ListNode nextNode = curr.next;

            if (i == m - 1) {
                tailOfHead = curr;
            } else if (i == m) {
                tailR = curr;
                preR = curr;
            } else if (m < i && i < n) {
                curr.next = preR;
                preR = curr;
            } else if (i == n) {
                curr.next = preR;
                headR = curr;
                tailR.next = nextNode;
            }

            curr = nextNode;
            i++;
        }

        if (tailOfHead == null) {
            return headR;
        } else {
            tailOfHead.next = headR;
            return head;
        }
    }

}
