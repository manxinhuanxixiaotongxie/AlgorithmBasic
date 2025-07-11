package leetcode.practice;

import leetcode.ListNode;

public class Code025 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k < 2) {
            return head;
        }
        int length = 0;
        ListNode cur = head;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        if (length <= k) {
            // 如果长度小于等于k
            // 整个链表翻转
            cur = head;
            ListNode pre = null;
            ListNode next = null;
            while (cur != null) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;
        } else {
            // 翻转链表  每K个一组
            ListNode newHead = null;
            ListNode next = null;
            // 开始节点的位置
            ListNode begin = head;

            cur = head;
            int index = 0;
            // 设置链接链表的关键节点
            // 上一个节点结尾的位置
            ListNode preEnd = null;
            while (cur != null) {
                next = cur.next;
                if (++index == k) {
                    // 这个节点就是要翻转的最后一个节点
                    // 翻转从的begin到当前位置的链表
                    ListNode pre = null;

                    if (newHead == null) {
                        newHead = cur;
                    }
                    ListNode curBegin = begin;
                    while (begin != next) {
                        ListNode temp = begin.next;
                        begin.next = pre;
                        pre = begin;
                        begin = temp;
                    }

                    index = 0;
                    if (preEnd != null) {
                        preEnd.next = cur;
                    }
                    curBegin.next = next;
                    preEnd = curBegin;
                }

                cur = next;
            }
            return newHead;
        }
    }

    public ListNode reverseKGroup2(ListNode head, int k) {
        if (head == null || k < 2) {
            return head;
        }
        // k个一组翻转链表
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        // 如果长度小于k 不进行任何的操作
        if (len < k) return head;
        int index = 0;
        cur = head;
        // 上一组结束的点
        ListNode pre = null;
        // 下一组开始的点
        ListNode next = null;
        ListNode newHead = null;
        while (cur != null) {
            next = cur.next;
            // k个一组
            if (++index == k) {
                // 翻转从pre到cur的链表
                if (pre == null) {
                    ListNode revserse = revserse(head, cur);
                    newHead = revserse;
                    pre = head;
                    pre.next = next;
                } else {
                    // next已经有了 原来的pre.next节点作为新的pre
                    ListNode curPre = pre;
                    pre = curPre.next;
                    ListNode revserse = revserse(curPre.next, cur);
                    // 重新链接链表
                    curPre.next = revserse;
                    pre.next = next;
                }
                index = 0;
            }
            cur = next;
        }

        return newHead;

    }

    public ListNode revserse(ListNode begin, ListNode end) {
        // 从pre到next翻转链表 返回新头部
        ListNode cur = begin;
        ListNode next = null;
        ListNode pre = null;
        while (cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        cur.next = pre;
        return cur;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode cur = head;
        cur.next = new ListNode(2);
        cur = cur.next;
        cur.next = new ListNode(3);
        cur = cur.next;
        cur.next = new ListNode(4);
        cur = cur.next;
        cur.next = new ListNode(5);
        cur = cur.next;
//        cur.next = new ListNode(6);
//        ListNode head = new ListNode(1);
//        ListNode cur = head;
//        cur.next = new ListNode(2);
//        cur = cur.next;
//        cur.next = new ListNode(3);
//        cur = cur.next;
        Code025 code025 = new Code025();
        ListNode newHead = code025.reverseKGroup2(head, 2);
        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }
}
