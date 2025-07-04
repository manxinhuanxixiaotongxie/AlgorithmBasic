package book_02linkedlist;

/**
 * @Description 删除链表倒数第K个节点
 * 1.如果是单项链表
 * 遍历链表长度 如果链表的长度是N 那么其实就是删除第N-K+1个节点
 * 2.双向链表直接逆向遍历
 * @Author Scurry
 * @Date 2023-02-06 16:44
 */
public class Code02_DeleteKNode {

    class ListNode {
        int value;
        ListNode next;
        ListNode last;

        ListNode(int value) {
            this.value = value;
        }

        ListNode(ListNode node, int value, ListNode last) {
            this.value = value;
            this.next = next;
            this.last = last;
        }
    }

    /**
     * 实现一个函数可以删除单链表倒数第K个节点
     * 实现一个函数可以删除双链表倒数第K个节点
     *
     */

    /**
     * 单向链表：测试链接
     * <p>
     * https://leetcode.cn/problems/SLwz0R/
     */

    public ListNode removeNthFromEnd(ListNode head, int k) {
        if (head == null) return head;

        ListNode cur = head;
        int N = 0;
        while (cur != null) {
            N++;
            cur = cur.next;
        }
        // 链表长度是N
        if (k > N) return head;
        if (k == N) return head.next;
        //  1  2 3 4 5
        //  1  2 3 4 5
        // 删除倒数第二个
        // 要删除的节点是 N-K+1
        ListNode pre = head;
        cur = head.next;
        int index = 1; // 从1开始
        while (cur != null) {
            if (++index == N - k + 1) {
                // 删除当前节点
                pre.next = cur.next;
                break;
            }
            pre = cur;
            cur = cur.next;
        }
        return head;
    }

    /**
     * 双向链表
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode deleteKNode2(ListNode head, int k) {
        ListNode cur = head;
        while (cur != null) {
            k--;
            cur = cur.next;
        }
        if (k > 0) {
            return head;
        } else if (k == 0) {
            head = head.next;
            head.last = null;
            return head;
        } else {
            cur = head;
            while (++k < 0) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
            return head;
        }

    }

}
