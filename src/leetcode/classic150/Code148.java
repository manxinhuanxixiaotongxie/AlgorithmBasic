package leetcode.classic150;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 *
 */
public class Code148 {
    public ListNode sortList(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = head;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        while (cur != null) {
            ListNode next = cur.next;
            pq.offer(cur);
            cur.next = null;
            cur = next;
        }

        cur = dummyHead;
        while (!pq.isEmpty()) {
            cur.next = pq.poll();
            cur = cur.next;
        }

        return dummyHead.next;
    }
}
