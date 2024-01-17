package systemimprove.code08;

import java.util.HashMap;
import java.util.Map;

/**
 * 一种特殊的单链表节点类描述如下
 * class Node {
 * int value;
 * Node next;
 * Node rand;
 * Node(int val) { value = val; }
 * }
 * rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向null。
 * 给定一个由Node节点类型组成的无环单链表的头节点 head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点。
 * 【要求】
 * 时间复杂度O(N)，额外空间复杂度O(1)
 * <p>
 * 1.Map<Node,Node></>
 */
public class Code04_CopyRandomList {

    /**
     * 使用map
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        Node cur = head;

        while (cur != null) {
            map.put(cur,new Node(cur.val));
            cur = cur.next;
        }
        Node neaHead = map.get(head);
        // 0 - 2
        // 01  02
        //Map o 01 2 01
        cur = head;
        while (cur != null) {
            Node newNode = map.get(cur);
            newNode.random = map.get(cur.random);
            newNode.next = map.get(cur.next);
            cur = cur.next;
        }
        return neaHead;

    }

    /**
     * [[7,null],[13,0],[11,4],[10,2],[1,0]]
     *
     * @param head
     * @return
     */
    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        // 将所有的节点全部都复制一遍
        Node next = null;
        while (cur != null) {
            next = cur.next;
            Node newNode = new Node(cur.val);
            cur.next = newNode;
            newNode.next = next;
            cur = next;
        }
        // 设置random指针
        cur = head;
        while (cur != null) {
            cur.next.random = cur.random == null ? null : cur.random.next;
            cur = cur.next.next;
        }

        // 拆分链表
        // 0 0 0 0 0 0
        Node newHead = head.next;
        Node cur1 = newHead;
        Node next2 = null;
        cur = head;
        // [[7,null] ,[7,null],[13,0],[13,0],[11,4],[11,4],[10,2],[10,2],[1,0],[1,0]]
        // [[1,1],[1,1],[2,1],[2,1]]
        //
        while (cur != null) {
            // 原链表的下一个节点
            next = cur.next.next;
            next2 =next != null?next.next:null;
            cur.next = next;
            cur1.next = next2;
            cur = next;
            cur1 = next2;

        }

        return head;
    }

    public Node copyRandomList3(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        // 将所有的节点全部都复制一遍
        Node next = null;
        while (cur != null) {
            next = cur.next;
            Node newNode = new Node(cur.val);
            cur.next = newNode;
            newNode.next = next;
            cur = next;
        }
        // 设置random指针
        cur = head;
        while (cur != null) {
            cur.next.random = cur.random == null ? null : cur.random.next;
            cur = cur.next.next;
        }

        // 拆分链表
        // 0 0 0 0 0 0
        Node newHead = head.next;
        Node cur1 = newHead;
        Node next2 = null;
        cur = head;
        // [[7,null] ,[7,null],[13,0],[13,0],[11,4],[11,4],[10,2],[10,2],[1,0],[1,0]]
        // [[1,1],[1,1],[2,1],[2,1]]

        Node copy = null;
//        // next方向上，把新老链表分离
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            cur.next = next;
            copy.next = next != null ? next.next : null;
            cur = next;

        }

        return newHead;
    }

    /**
     * [[7,null],[13,0],[11,4],[10,2],[1,0]]
     *
     * @param args
     */
    public static void main(String[] args) {
        Node node1 = new Node(7);
        Node node2 = new Node(13);
        Node node3 = new Node(11);
        Node node4 = new Node(10);
        Node node5 = new Node(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        node1.random = null;
        node2.random = node1;
        node3.random = node5;
        node4.random = node3;
        node5.random = node1;

        Code04_CopyRandomList test = new Code04_CopyRandomList();
        Node node6 = test.copyRandomList3(node1);
        Node node = test.copyRandomList2(node1);
        while (node6 != null) {
            if (node.val != node6.val) {
                System.out.println("error");
            }
            node = node.next;
            node6 = node6.next;
        }
        System.out.println(node);
    }
}
