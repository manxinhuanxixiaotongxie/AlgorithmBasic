package book_02linkedlist;


/**
 * @Description leetcode测试通过
 * @Author Scurry
 * @Date 2023-05-13 10:23
 */
public class ReverseListKGroup {

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        reverseKGroup(n1, 2);
    }

    /**
     * leetcode k个一组翻转链表
     * 给定一个链表
     * 给定一个整数K
     * 每K个进行反转
     * <p>
     * 进阶：你可以设计一个O(1)额外内存空间的算法解决这个问题吗
     */

    public static ListNode reverseKGroup(ListNode head, int k) {

        if (head == null || k <= 0) {
            return head;
        }

        ListNode nextNode = getNextNode(head, k);
        if (nextNode == null) {
            return head;
        }

        ListNode listNode = reverseListNode(head, nextNode);

        // 上一次结束end节点
        ListNode lastEnd = head;

        // 新头部
        head = listNode;

        ListNode next = null;
        while (lastEnd != null) {
            next = lastEnd.next;
            ListNode nextNode1 = getNextNode(next, k);
            if (nextNode1 == null) {
                return head;
            }
            ListNode listNode1 = reverseListNode(next, nextNode1);
            lastEnd.next = listNode1;
            lastEnd = next;

        }

        return head;
    }

    /**
     * start end进行翻转
     *
     * @param start
     * @param end
     * @return
     */
    public static ListNode reverseListNode(ListNode start, ListNode end) {

        ListNode pre = null;
        ListNode next = null;
        end = end.next;
        ListNode cur = start;
        while (cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = end;
        return pre;

    }

    public static ListNode getNextNode(ListNode start, int k) {
        while (--k > 0 && start != null) {
            start = start.next;
        }
        return start;
    }


}
