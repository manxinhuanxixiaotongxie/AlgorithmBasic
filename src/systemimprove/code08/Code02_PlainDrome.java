package systemimprove.code08;

import com.sun.xml.internal.ws.addressing.WsaTubeHelperImpl;

import java.util.Stack;

/**
 * 给定一个单链表的头节点head，请判断该链表是否为回文结构
 */
public class Code02_PlainDrome {

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        cur = head;
        while (!stack.isEmpty()) {
            if (stack.pop().val != cur.val) {
                return false;
            }
            cur = cur.next;
        }
        return true;

    }

    public boolean isPalindrome2(ListNode head) {
        if (head == null) {
            return false;
        }
        // 来到上中点
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode cur = slow;
        ListNode pre = null;
        // 假设来到了最后一位
        // 0 0 0
        //
        while (cur.next != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        cur.next = pre;
        // cur来到了右半部分的第一个节点
        ListNode left = head;
        ListNode right = cur;
        while (left != null) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        // 恢复链表
        pre = null;
        while (cur.next != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        cur.next = pre;
        return true;
    }

}
