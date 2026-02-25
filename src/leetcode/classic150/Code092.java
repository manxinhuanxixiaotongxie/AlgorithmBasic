package leetcode.classic150;

/**
 * 链表的部分反转
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 * <p>
 * <p>
 * 进阶： 你可以使用一趟扫描完成反转吗？
 */
public class Code092 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null) {
            return head;
        }
        // 有可能涉及到换头
        // 设置两个指针
        // left前面的结束位置
        ListNode end = null;
        // right后面的开始位置
        ListNode begin = null;
        ListNode newHead = null;
        ListNode from = null;
        ListNode to = null;
        ListNode cur = head;
        int index = 0;
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            if (index + 1 == left) {
                // 说明当前节点就是要翻转的from节点
                // 那么pre就赋值给end节点 说明是翻转之前的结束节点
                from = cur;
                end = pre;
            }
            if (index + 1 == right) {
                // 说明当前节点翻转的最后一个节点 那么begin赋值给begin节点
                to = cur;
                begin = next;
            }
            index++;
            pre = cur;
            cur = next;
        }
        // 翻转from到to的链表
        ListNode newRevserse = reverseAndReturn(from, to);
        if (end == null) {
            // 说明需要换头
            newHead = newRevserse;
            from.next = begin;
        } else {
            // 不要需要换头
            newHead = head;
            end.next = newRevserse;
            from.next = begin;
        }

        return newHead;
    }

    public ListNode reverseAndReturn(ListNode from, ListNode to) {
        // 翻转from到to的链表 并返回新头节点
        ListNode pre = null;
        ListNode cur = from;
        while (cur != to) {
            // 反转
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        cur.next = pre;
        return cur;
    }

    static void main() {
        Code092 code = new Code092();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode res = code.reverseBetween(head, 2, 4);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}
