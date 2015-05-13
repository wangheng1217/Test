package wangheng.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedListsSolution {
    
    // use the heap implementation in Java - PriorityQueue
    public ListNode mergeKLists3(ArrayList<ListNode> lists) {
        if (lists == null || lists.isEmpty())
            return null;

        Comparator<ListNode> comparator = new Comparator<ListNode>() {
            @Override
            public int compare(ListNode n1, ListNode n2) {
                if (n1.val < n2.val)
                    return -1;
                else if (n1.val > n2.val)
                    return 1;
                else
                    return 0;
            }
        };

        PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(
                lists.size(), comparator);
        ListNode head = null;
        ListNode cur = null;
        for (int i = 0; i < lists.size(); i++) {
            ListNode node = lists.get(i);
            if (node != null)
                heap.add(lists.get(i));
        }
        while (!heap.isEmpty()) {
            if (head == null) {
                head = heap.poll();
                cur = head;
                if (cur.next != null)
                    heap.add(cur.next);
            } else {
                ListNode newNode = heap.poll();
                cur.next = newNode;
                cur = newNode;
                if (cur.next != null)
                    heap.add(cur.next);
            }
        }

        return head;
    }

    // using heap, O(k*n*lgk)
    public ListNode mergeKLists2(ArrayList<ListNode> lists) {
        if (lists.size() == 0)
            return null;

        // construct MIN-heap
        ListNode[] heap = new ListNode[lists.size()];
        for (int i = 0; i < heap.length; i++) {
            heap[i] = lists.get(i);
        }
        for (int i = 1; i < heap.length; i++) {
            int curr = i;
            while (curr > 0
                    && heap[curr] != null
                    && (heap[(curr - 1) / 2] == null || heap[curr].val < heap[(curr - 1) / 2].val)) {
                swap(heap, curr, (curr - 1) / 2);
                curr = (curr - 1) / 2;
            }
        }

        ListNode head = null;
        ListNode curr = null;
        while (heap[0] != null) {
            if (head == null) {
                head = new ListNode(heap[0].val);
                curr = head;
            } else {
                curr.next = new ListNode(heap[0].val);
                curr = curr.next;
            }

            heap[0] = heap[0].next;
            int currIndex = 0;
            while (currIndex < heap.length) {
                int minChild = minChild(heap, currIndex);

                // leaf
                if (minChild == -1)
                    break;
                else {
                    if (heap[minChild] == null)
                        break;
                    else {
                        if (heap[currIndex] == null
                                || heap[currIndex].val > heap[minChild].val) {
                            swap(heap, currIndex, minChild);
                            currIndex = minChild;
                        } else
                            break;
                    }
                }
            }
        }
        return head;
    }

    private void swap(ListNode[] list, int a, int b) {
        ListNode tempNode = list[a];
        list[a] = list[b];
        list[b] = tempNode;
    }

    private int minChild(ListNode[] heap, int n) {
        int leftChild = 2 * n + 1;
        int rightChild = 2 * n + 2;
        if (leftChild > heap.length - 1)
            return -1;
        else if (rightChild > heap.length - 1)
            return leftChild;
        else {
            ListNode leftNode = heap[leftChild];
            ListNode rightNode = heap[rightChild];
            if (rightNode == null) {
                return leftChild;
            } else {
                if (leftNode == null) {
                    return rightChild;
                } else {
                    return leftNode.val <= rightNode.val ? leftChild
                            : rightChild;
                }
            }
        }
    }
    
    // O(k2*n)
    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        ListNode head = null;
        ListNode curr = null;
        while (true) {
            ListNode smallestNode = null;
            int smallestIndex = -1;
            for (int i = 0; i < lists.size(); i++) {
                ListNode node = lists.get(i);
                if (node != null) {
                    if (smallestNode == null) {
                        smallestNode = node;
                        smallestIndex = i;
                    } else {
                        if (node.val < smallestNode.val) {
                            smallestNode = node;
                            smallestIndex = i;
                        }
                    }
                }
            }
            if (smallestNode == null)
                break;
            else {
                // can not use the node reference, need create new node
                /*
                if (head == null) {
                    head = smallestNode;
                    curr = smallestNode;
                } else {
                    curr.next = smallestNode;
                    curr = curr.next;
                }
                smallestNode = smallestNode.next;
                */

                if (head == null) {
                    head = new ListNode(smallestNode.val);
                    curr = head;
                } else {
                    curr.next = new ListNode(smallestNode.val);
                    curr = curr.next;
                }
                
                // this code won't update the reference in the list
                //smallestNode = smallestNode.next;

                if (smallestNode.next == null) {
                    lists.remove(smallestIndex);
                } else {
                    smallestNode.val = smallestNode.next.val;
                    smallestNode.next = smallestNode.next.next;
                }
            }
        }
        return head;
    }

}
