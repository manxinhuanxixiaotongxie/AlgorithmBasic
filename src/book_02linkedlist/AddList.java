package book_02linkedlist;

import java.util.Stack;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-04-14 17:35
 */
public class AddList {
    /**
     * 两个单链表生成相加链表
     * 假定链表中每个值都在0-9之间
     * 1 9 3 这个链表表达的是 193   0 0 7 这个链表表达的是7
     * 两个链表相加生成2-0-0
     */
    public ListNode addList(ListNode head1, ListNode head2) {

        /**
         * 常规思路这样做：
         * 1.遍历链表 将数组变成字节数组
         * 2.字符数组拼接 重新转换成链表
         * 问题：链表可能很长会导致int溢出
         */

        return head1;
    }

    /**
     * 使用栈解决
     * 1 9 3 这个链表表达的是 193   0 0 7 这个链表表达的是7
     * 3
     * 9
     * 1
     * <p>
     * 7
     * 0
     * 0
     * <p>
     * 要考虑进位问题
     */
    public ListNode addListUseStack(ListNode head1, ListNode head2) {
        if (head1 == null && head2 == null) {
            return null;
        }
        if (head1 == null && head2 != null) {
            return head2;
        }

        if (head1 != null && head2 == null) {
            return head1;
        }

        ListNode cur = head1;
        Stack<Integer> stack1 = new Stack<>();
        while (cur.next != null) {
            stack1.push(cur.val);
            cur = cur.next;
        }

        Stack<Integer> stack2 = new Stack<>();
        cur = head2;
        while (head2.next != null) {
            stack2.push(cur.val);
            cur = cur.next;
        }

        int n1 = 0;
        int n2 = 0;
        int cal = 0;
        int newVal = 0;
        ListNode newNode = null;
        ListNode pre = null;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            n1 = stack1.isEmpty() ? 0 : stack1.pop();
            n2 = stack2.isEmpty() ? 0 : stack2.pop();
            if ((n1 + n2 + cal) >= 10) {
                newNode = new ListNode((n1 + n2 + cal) % 10);
                cal = (n1 + n2 + cal) / 10;
            } else {
                newNode = new ListNode((n1 + n2 + cal));
            }
            newNode.next = pre;
            pre = newNode;

        }

        // 所有数都弹出还有需要进位
        if (cal > 0) {
            pre = newNode;
            newNode = new ListNode(cal);
            newNode.next = pre;
        }

        return newNode;
    }


    /**
     * 利用链表的逆序，节省栈空间
     *
     * @param head1
     * @param head2
     * @return
     */
    public ListNode addListUseStackImprove(ListNode head1, ListNode head2) {
        if (head1 == null && head2 == null) {
            return null;
        }
        if (head1 == null && head2 != null) {
            return head2;
        }

        if (head1 != null && head2 == null) {
            return head1;
        }

        // 逆序两个链表
        ListNode next;
        ListNode pre = null;
        while (head1 != null) {
            next = head1.next;
            head1.next = pre;
            pre = head1;
            head1 = next;
        }

        pre = null;
        while (head2 != null) {
            next = head2.next;
            head2.next = pre;
            pre = head2;
            head2 = next;
        }

        int cal = 0;
        int n1 = 0;
        int n2 = 0;
        int n = 0;
        ListNode newHead = null;
        pre = null;
        ListNode c1 = head1;
        ListNode c2 = head2;
        while (c1 != null || c2 != null) {
            n1 = head1 == null ? 0 : head1.val;
            n2 = head2 == null ? 0 : head2.val;
            n = n1 + n2 + cal;
            newHead = new ListNode(n % 10);
            cal = n / 10;

            newHead.next = pre;

//            if (head1 != null) {
//                head1 = head1.next;
//            }
//            if (head2 != null) {
//                head2 = head2.next;
//            }
            c1 = head1 != null ? c1.next : null;
            c2 = head2 != null ? c2.next : null;


        }

        if (cal == 1) {
            pre = newHead;
            newHead = new ListNode(1);
            newHead.next = pre;

        }

        // 恢复链表
        reverstList(head1);
        reverstList(head2);

        return newHead;
    }

    public static ListNode reverstList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return head;
    }

}
