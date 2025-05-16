package book_02linkedlist;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-04-20 14:00
 */
public class LeftRightList {

    /**
     * 按照左右半区的方式重新组合单链表
     */
    public ListNode leftRightList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        // 1 2 3 4
        ListNode cur = head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow.next = null;

        // 此时slow走到中间节点
        cur = slow;
        ListNode curLeft = head;
        ListNode leftNext;
        ListNode rightNext;
        while (curLeft.next != null) {
            rightNext = cur.next;
            cur.next = curLeft.next;
            curLeft.next = cur;
            curLeft = cur.next;
            cur = rightNext;
        }

        curLeft.next = cur;

        return head;
    }
}
