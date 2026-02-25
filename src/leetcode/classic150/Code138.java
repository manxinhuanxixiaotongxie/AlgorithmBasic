package leetcode.classic150;

import com.sun.source.tree.WhileLoopTree;

import java.util.HashMap;
import java.util.Map;

public class Code138 {
    /**
     * 使用辅助数据结构
     *
     * @param head
     * @return
     */
    public Node copyRandomList1(Node head) {
        // 使用辅助数据结构 key是原节点 value是是复制出来的新节点
        Map<Node,Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }

        return  map.get(head);
    }

    /**
     * 不使用辅助结构
     * 在原链表基础上进行复制 组合成一个长链表 然后再进行链表的拆分 注意要将原始给定的链表进行恢复
     * @param head
     * @return
     */
    public Node copyRandomList2(Node head) {
        // 在原始链表的基础上进行复制 组合成一个长链表
        Node cur = head;
        Node next = null;
        while (cur != null) {
            // 下一个节点
            next = cur.next;
            // 开始复制新节点
            Node node = new Node(cur.val);
            // 链接新节点
            cur.next = node;
            node.next = next;
            cur = next;
        }
        // 开始链接random指针
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            cur.next.random = cur.random == null ? null : cur.random.next;
            cur = next;
        }
        // 进行链表的拆分 注意要将原始给定的链表进行恢复
        cur = head;
        Node newHead = head == null ? null : head.next;
        Node cur2 = newHead;
        while (cur != null) {
            next = cur.next.next;
            cur.next = next;
            cur2.next = next == null ? null : next.next;
            cur2 = cur2.next;
            cur = next;
        }
        return  newHead;
    }
}
