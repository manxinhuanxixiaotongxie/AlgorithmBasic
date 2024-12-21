package leetcode.practice;

public class Code019 {
    /**
     * 提供两种思路的实现
     * 方法1：
     * <p>
     * 要删除倒数第n个节点
     * 假设链表的长度是L
     * 那么要删除的节点就是L-n+1
     * <p>
     * 要注意两个问题：
     * 1.链表的长度没有N长
     * 2.要删除的节点是头节点
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0) {
            return head;
        }
        int length = 0;
        ListNode cur = head;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        if (n > length) {
            return head;
        }
        if (n == length) {
            return head.next;
        }
        int deleteIndex = length - n + 1;
        ListNode pre = null;
        cur = head;
        int index = 0;
        while (cur != null) {
            index++;
            if (index == deleteIndex) {
                // 就是要删除的节点
                pre.next = cur.next;
                cur.next = null;
                break;
            }
            pre = cur;
            cur = cur.next;
        }
        return head;

    }

    /**
     * 第二种思路：
     * <p>
     * 先让节点走N步 记录此时的节点为end
     * 然后让头结点向下移动 直到end节点来到空位置
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null || n <= 0) {
            return head;
        }
        ListNode cur = head;
        while (n - 1 > 0) {
            if (cur == null) {
                // 在走的过程中链表已经来到了尽头 说明链表的长度没有N长
                return head;
            }
            cur = cur.next;
            n--;
        }
        // 走了n步之后 cur来到了第n个节点
        // 从当前位置开始向链表的后面开始走
        // 当cur位置来到了最后一个节点的时候，最开始的节点就是要删除的节点
        ListNode pre = null;
        ListNode deletNode = head;
        while (cur != null) {
            if (cur.next == null) {
                // 说明当前的节点就是要删除的节点
                if (pre == null) {
                    // 说明要删除的节点是头节点
                    return head.next;
                }
                pre.next = deletNode.next;
                deletNode.next = null;
                break;
            }
            pre = deletNode;
            deletNode = deletNode.next;
            cur = cur.next;
        }
        return head;

    }
}
