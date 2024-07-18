package systemreview.code08;

public class Code01_PlainDrome {

    public boolean isPlainDrome(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 此时slow来到了上中点的位置

        ListNode cur = slow;
        ListNode next;
        ListNode pre = null;
        // 翻转后面的链表
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 此时pre来到了最后一个节点
        cur = head;
        ListNode tail = pre;
        while (cur != null) {
            if (cur.val != pre.val) {
                return false;
            }
            cur = cur.next;
            pre = pre.next;
        }
        // 恢复链表
        while (tail != null) {
            next = tail.next;
            tail.next = cur;
            cur = tail;
            tail = next;
        }
        return true;
    }

}


class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}
