package book_02linkedlist;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-04-18 10:30
 */
public class ReverseKNode {

    /**
     * 将单链表的每K个节点之间逆序
     * 给定一个head 实现一个调整单链表的函数
     * st 每K个节点之间逆序 如果k个节点不够一组 则不调整最后几个节点
     */

    public ListNode reverseKNode(ListNode head, int k) {
        if (head == null || k <= 0) {
            return head;
        }
        ListNode cur = head;
        int length = 0;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        if (length < k) {
            return head;
        }
        length = 0;
        cur = head;
        ListNode begin = null;
        ListNode newHead = null;
        while (cur != null) {
            if (length == 0) {
                begin = cur;
            }
            ++length;
            if (length + 1 == k) {
                ListNode reverse = reverse(begin, cur.next);
                if (newHead == null) {
                    newHead = reverse;
                }
                length = 0;
            }
            cur = cur.next;
        }

        return head;
    }

    /**
     * 将单链表的每K个节点之间逆序
     * 给定一个head 实现一个调整单链表的函数
     * st 每K个节点之间逆序 如果k个节点不够一组 则不调整最后几个节点
     */

    public ListNode reverseKNode2(ListNode head, int k) {
        if (head == null || k <= 0) {
            return head;
        }
        ListNode cur = head;
        int length = 0;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        if (length < k) {
            return head;
        }
        length = 0;
        cur = head;
        ListNode begin = null;
        ListNode newHead = null;
        while (cur != null) {
            if (length == 0) {
                begin = cur;
            }
            ++length;
            if (length + 1 == k) {
                ListNode reverse = reverse(begin, cur.next);
                if (newHead == null) {
                    newHead = reverse;
                }
                length = 0;
            }
            cur = cur.next;
        }

        return head;
    }

    /**
     * 将单链表的每K个节点之间逆序
     * 给定一个head 实现一个调整单链表的函数
     * st 每K个节点之间逆序 如果k个节点不够一组 则不调整最后几个节点
     * 测试通过
     */

    public ListNode reverseKNode3(ListNode head, int k) {
        if (head == null || k <= 0) {
            return head;
        }
        ListNode cur = head;
        int length = 0;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        if (length < k) {
            return head;
        }
        length = 1;
        cur = head;
        ListNode pre = null;
        ListNode start = null;
        ListNode next = null;
        while (cur != null) {
            if (length == k) {
                start = pre == null ? head : pre.next;
                head = pre == null ? cur : head;
                resign2(pre, start, cur, next);
                pre = start;
                length = 1;
            }
            length++;
            cur = next;
        }


        return head;
    }

    public void resign2(ListNode left, ListNode start, ListNode end, ListNode right) {
        ListNode pre = start;
        ListNode cur = start.next;
        ListNode next = null;
        while (cur != right) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        if (left != null) {
            left.next = end;
        }
        start.next = right;

    }

    public ListNode reverse(ListNode begin, ListNode end) {
        ListNode cur = begin;
        ListNode next = end;
        ListNode nextNode;
        while (cur != end) {
            nextNode = cur.next;
            cur.next = next;
            next = cur;
            cur = nextNode;
        }
        return cur;

    }
}
