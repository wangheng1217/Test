package wangheng.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CopyListWithRandomPointerSolution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null)
            return null;

        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode pre = dummy, newNode;

        while (head != null) {
            if (map.containsKey(head)) {
                newNode = map.get(head);
            } else {
                newNode = new RandomListNode(head.label);
                map.put(head, newNode);
            }

            if (head.random != null) {
                if (map.containsKey(head.random)) {
                    newNode.random = map.get(head.random);
                } else {
                    newNode.random = new RandomListNode(head.random.label);
                    map.put(head.random, newNode.random);
                }
            }

            head = head.next;
            pre.next = newNode;
            pre = newNode;
        }

        return dummy.next;
    }

    public RandomListNode copyRandomList2(RandomListNode head) {
        if (head == null)
            return null;

        List<RandomListNode> origList = new ArrayList<RandomListNode>();
        List<RandomListNode> newList = new ArrayList<RandomListNode>();
        Map<RandomListNode, Integer> map = new HashMap<RandomListNode, Integer>();

        RandomListNode node = head;
        int i = 0;
        while (node != null) {
            origList.add(node);
            map.put(node, i);

            RandomListNode newNode = new RandomListNode(node.label);
            newList.add(newNode);

            node = node.next;
            i++;
        }

        for (int j = 0; j < origList.size(); j++) {
            if (j < origList.size() - 1) {
                newList.get(j).next = newList.get(j + 1);
            }

            if (origList.get(j).random != null) {
                newList.get(j).random = newList.get(map.get(origList.get(j).random));
            }
        }

        return newList.get(0);
    }

}

class RandomListNode {
    int label;
    RandomListNode next, random;

    RandomListNode(int x) {
        this.label = x;
    }
}