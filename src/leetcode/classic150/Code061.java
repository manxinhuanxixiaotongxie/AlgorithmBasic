package leetcode.classic150;

/**
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 *
 */
public class Code061 {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k <= 0) {
            return  head;
        }
        int len = 0;
        ListNode cur = head;
        ListNode newHead = null;
        ListNode end = null;
        while (cur != null) {
            len++;
            end = cur;
            cur = cur.next;
        }
        int n = len - k % len;
        if (n == len) return  head;
        cur = head;
        ListNode pre = null;
        while (n  > 0) {
            pre = cur;
            cur = cur.next;
            n--;
        }
        newHead = cur;
        pre.next = null;
        end.next = head;
        return newHead;
    }
}
