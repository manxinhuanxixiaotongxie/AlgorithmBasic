package newerclass;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-05-25 9:25
 */
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        Queue<ListNode> myQueue = new PriorityQueue<>(new MyComparator());

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                myQueue.add(lists[i]);
            }
        }
        ListNode head = myQueue.poll();
        ListNode cur = head;
        while (!myQueue.isEmpty()) {
            if (cur.next != null) {
                myQueue.add(cur.next);
            }
            ListNode minNode = myQueue.poll();
            cur.next = minNode;
            cur = minNode;

        }

        return head;

    }

    public class MyComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    }
}

