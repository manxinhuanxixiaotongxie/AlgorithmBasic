package leetcode.practice;

public class Code0024 {
    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return head;
        }
        if (head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        ListNode cur = head;
        ListNode next;
        ListNode pre = new ListNode(-1);

        while (cur != null) {
            if (cur.next == null) {
                break;
            }
            next = cur.next.next;
            // 两个两个一翻转
            ListNode after = cur.next;
            pre.next = after;
            after.next = cur;
            cur.next = next;
            pre = cur;
            cur = next;
        }
        return newHead;
    }
}
