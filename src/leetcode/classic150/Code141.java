package leetcode.classic150;

public class Code141 {
    public boolean hasCycle(ListNode head) {
        // 返回链表是否有环
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == null) {
                return false;
            }
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
