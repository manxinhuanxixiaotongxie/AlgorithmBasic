package book_02linkedlist;

import java.util.Stack;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-04-18 15:41
 */
public class DeleteDefineNode {
    /**
     * 删除指定值的节点 时间复杂度O(1)
     */

    public ListNode deletDefineNode(ListNode head, int k) {

        ListNode cur = head;
        ListNode pre = null;
        while (head != null) {
            if (head.val != k) {
                break;
            }
            head = head.next;
        }
        while (cur != null) {
            if (cur.val == k) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    /**
     * 删除指定值的节点 时间复杂度O(N)
     */

    public ListNode deletDefineNode2(ListNode head, int k) {

        ListNode cur = head;
        Stack<ListNode> stack = new Stack<>();
        while (cur != null) {
            if (cur.val != k) {
                stack.push(cur);
            }
            cur = cur.next;
        }
        // 此时head走向null
        while (!stack.isEmpty()) {
            stack.peek().next = head;
            head = stack.pop();
        }
        return head;
    }
}
