package traingcamp.camp003.code04;

import leetcode.ListNode;

import java.util.Queue;

/**
 * 双向链表节点结构和二叉树节点结构是一样的，如果你把last认为是left，next认为是next的话。
 * 给定一个搜索二叉树的头节点head，请转化成一条有序的双向链表，并返回链表的头节点。
 */
public class Code02 {

    public ListNode convert(ListNode node) {
        if (node == null) {
            return null;
        }
        Queue<ListNode> queen = new java.util.LinkedList<>();
        process(node, queen);
        // 中序遍历之后的队列
        ListNode pre = new ListNode(-1);

        while (!queen.isEmpty()) {
            ListNode cur = queen.poll();
            // 链接上下节点
            pre.next = cur;
            cur.last = pre;
            pre = cur;
        }

        return pre.next;
    }

    private void process(ListNode node, Queue<ListNode> queen) {
        if (node == null) {
            return;
        }
        process(node.last, queen);
        queen.add(node);
        process(node.next, queen);
    }

    public ListNode convert2(ListNode node) {
        if (node == null) {
            return null;
        }
        Queue<ListNode> queen = new java.util.LinkedList<>();
        process(node, queen);
        // 中序遍历之后的队列
        ListNode pre = new ListNode(-1);

        while (!queen.isEmpty()) {
            ListNode cur = queen.poll();
            // 链接上下节点
            pre.next = cur;
            cur.last = pre;
            pre = cur;
        }

        return pre.next;
    }

    private Info process(ListNode root) {
        if (root == null) {
            return new Info(null, null);
        }
        Info left = process(root.last);
        Info right = process(root.next);
        if (left.end != null) {
            left.end.next = root;
        }
        root.last = left.end;
        root.next = right.start;
        if (right.start != null) {
            right.start.last = root;
        }
        return new Info(left.start != null ? left.start : root, right.end != null ? right.end : root);
    }


    class Info {
        ListNode start;
        ListNode end;

        public Info(ListNode start, ListNode end) {
            this.start = start;
            this.end = end;
        }
    }
}
