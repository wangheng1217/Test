package wangheng.leetcode;

// recursion
public class ReverseNodesInKGroupSolution {
    
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null)
            return null;
        ListNode curr = head;
        int count = 0;
        while (curr != null && count < k) {
            curr = curr.next;
            count++;

        }

        if (count == k) {
            ListNode pre = head;
            ListNode temp = head.next;
            head.next = null;
            for (int i = 1; i < k; i++) {
                ListNode temp2 = temp.next;
                temp.next = pre;
                pre = temp;
                temp = temp2;
            }

            ListNode newHead = pre;

            if (curr == null) {
                return newHead;
            } else {
                head.next = reverseKGroup(curr, k);
                return newHead;
            }
        } else {
            return head;
        }
    }

    public ListNode reverseKGroup2(ListNode head, int k) {
        if (k <= 1 || head == null) return head;
        
        ListNode newHead = null;
        ListNode preGroup = null;
        ListNode curr = null;
        ListNode pre = null;
        ListNode last = null;
        
        curr = head;
        
        boolean needReverse = true;
        int i = k;
        while (i > 1) {
            if (curr.next == null) {
                needReverse = false;
                break;
            }
            curr = curr.next;
            i--;
        }
        
        if (!needReverse) return head;
        
        curr = head;
        last = head;
        i = k;
        while (i >= 1) {
            ListNode temp = curr;
            curr = curr.next;
            temp.next = pre;
            pre = temp;
            i--;
        }
        
        newHead = pre;
        preGroup = last;
        
        while (needReverse) {
            if (curr == null) break;
            
            last = curr;
            i = k;
            while (i > 1) {
                if (curr.next == null) {
                    needReverse = false;
                    break;
                }
                curr = curr.next;
                i--;
            }
            
            if (!needReverse) {
                preGroup.next = last;
                break;
            }
            
            i = k;
            curr = last;
            pre = null;
            while (i >= 1) {
                ListNode temp = curr;
                curr = curr.next;
                temp.next = pre;
                pre = temp;
                i--;
            }
            
            preGroup.next = pre;
            preGroup = last;
        }
        
        return newHead;
        
    }

}
