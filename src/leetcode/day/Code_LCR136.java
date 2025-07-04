package leetcode.day;

import leetcode.ListNode;

public class Code_LCR136 {

    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) return head;
        if (val == head.val) return head.next;
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (val == cur.val) {
                pre.next = cur.next;
            }
            pre = cur;
            cur = cur.next;
        }
        return head;
    }
}
