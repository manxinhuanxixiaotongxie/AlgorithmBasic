package leetcode.classic150;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 */
public class Code023 {
    /**
     * 不是最优解
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummyHead = new ListNode(0);

        ListNode cur = dummyHead;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for (ListNode node : lists) {
            ListNode temp = node;
            while (temp != null) {
                ListNode next = temp.next;
                pq.add(temp);
                temp.next = null;
                temp = next;
            }
        }
        while (!pq.isEmpty()) {
            cur.next = pq.poll();
            cur = cur.next;
        }
        return dummyHead.next;
    }
}
