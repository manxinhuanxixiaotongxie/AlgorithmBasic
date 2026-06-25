package leetcode.day;

import java.util.List;
import java.util.Stack;

/**
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 */
public class Code025 {
    /**
     * 第一个思路 借助辅助结构
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        Stack<ListNode> stack = new Stack<>();
        ListNode lastNode = dummy;
        ListNode cur = head;
        int index = 0;
        while (cur != null) {
            ListNode next;
            while (index < k && cur != null) {
                next = cur.next;
                stack.push(cur);
                cur = next;
                index++;
            }
            // 翻转
            if (!stack.isEmpty() && stack.size() == k) {
                while (!stack.isEmpty()) {
                    lastNode.next = stack.pop();
                    lastNode = lastNode.next;
                }
                lastNode.next = cur;
                index = 0;
            }
        }
        return dummy.next;
    }

    /**
     * 不使用辅助结构 原地修改
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup2(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode lastNode = dummy;
        ListNode cur = head;
        int index = 0;
        while (cur != null) {
            // k个一组
            while (cur != null && index < k - 1) {
                // 当index
                ListNode next = cur.next;
                index++;
                cur = next;
            }
            // 当index==k的时候 cur已经来到了下一组的结束位置
            // 剩余节点刚好剩k-1个 会导致cur为null
            if (index == k - 1 && cur != null) {
                // 需要翻转的链表是lastNode.next 到cur这一段
                ListNode next = cur.next;
                ListNode temp = lastNode.next;
                lastNode.next = revers(lastNode.next, cur);
                temp.next = next;
                lastNode = temp;
                cur = next;
                index = 0;
            }
        }
        return dummy.next;
    }

    public ListNode revers(ListNode head, ListNode tail) {
        // 翻转head到tail这段链表
        ListNode pre = null;
        while (head != tail) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        tail.next = pre;
        return head;
    }

    static void main() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        int k = 2;
        Code025 obj = new Code025();
        ListNode res = obj.reverseKGroup2(head, k);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}
