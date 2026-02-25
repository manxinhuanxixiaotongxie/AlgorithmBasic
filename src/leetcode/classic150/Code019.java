package leetcode.classic150;

/**
 * 删除链表的倒数第N个节点
 *
 */
public class Code019 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 删除倒数第n节点
        // 有几种处理方式
        // 第一种方式 翻转链表 就是正数第N个节点需要被删除
        // 第二种方式 假设链表的长度是5 删除倒是第五个节点 就是删除正数第len - n个节点
        // 第三种方式 快慢指针 快指针先走N步 然后快慢指针向后走 当快指针走到链表结束时 慢指针所在位置就是需要被删除节点的前一个节点

        // 本方式采用第二种解法
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        // 计算出来链表的长度
        // 那么需要删除的节点就是正数len - n个节点
        int delIndex = len - n;
        ListNode pre = null;
        cur = head;
        while (cur != null) {
            if (delIndex == 0) {
                if (pre == null) {
                    return head.next;
                } else {
                    pre.next = pre.next == null ? null : pre.next.next;
                    return head;
                }
            }
            delIndex--;
            pre = cur;
            cur = cur.next;
        }
        return head;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        // 删除倒数第n节点
        // 有几种处理方式
        // 第一种方式 翻转链表 就是正数第N个节点需要被删除
        // 第二种方式 假设链表的长度是5 删除倒是第五个节点 就是删除正数第len - n个节点
        // 第三种方式 快慢指针 快指针先走N步 然后快慢指针向后走 当快指针走到链表结束时 慢指针所在位置就是需要被删除节点的前一个节点

        // 本方式采用第三种解法
        // 快慢指针
        if (head == null) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (n > 0) {
            fast = fast.next;
            if (fast == null) return head.next;
            n--;
        }
        // 同时向下走
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        // slow是删除的前一个位置
        slow.next = slow.next == null ? null : slow.next.next;

        return head;
    }

    static void main() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        Code019 code019 = new Code019();
        ListNode ans = code019.removeNthFromEnd2(head, 2);
        while (ans != null) {
            System.out.println(ans.val);
            ans = ans.next;
        }
    }
}
