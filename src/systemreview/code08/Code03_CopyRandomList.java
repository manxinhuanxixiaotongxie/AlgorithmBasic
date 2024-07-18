package systemreview.code08;

import code09.Code04_CopyListWithRandom;

import java.util.HashMap;
import java.util.Map;

/**
 * random指针是单链表节点结构中新增的指针，random可能指向链表中的任意一个节点，也可能指向null。
 * 给定一个由Node节点类型的无环单链表的头节点head，
 * 请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点
 * 要求：时间复杂度O(N)，额外空间复杂度O(1)
 */
public class Code03_CopyRandomList {


    /**
     * 第一种实现方式：使用HashMap
     */

    public static Node copyList1(Node head) {
        if (head == null) {
            return head;
        }
        // 使用一个map map记录的是老节点到新节点的映射
        Map<Node, Node> nodeMap = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            nodeMap.put(cur, new Node(cur.value));
            cur = cur.next;
        }

        // 得到map之后开始设置random指针
        cur = head;
        while (cur != null) {
            nodeMap.get(cur).random = nodeMap.get(cur.random);
            nodeMap.get(cur).next = nodeMap.get(cur.next);
            cur = cur.next;
        }
        return nodeMap.get(head);
    }

    public static Node copyList2(Node head) {
        if (head == null) {
            return head;
        }
        // 在链表原地进行调整 返回新的节点
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            Node node = new Node(cur.value);
            node.next = next;
            cur.next = node;
            cur = next;
        }
        cur = head;
        while (cur != null) {
            Node next = cur.next.next;
            cur.next.random = cur.random == null ? null : cur.random.next;
            cur = next;
        }
        // 剥离建工之后的链表
        cur = head;
        Node newHead = cur.next;
        while (cur != null) {
            Node newNode = cur.next;
            Node next = cur.next.next;
            cur.next = next;
            newNode.next = next == null ? null : next.next;
            cur = next;

        }
        return newHead;
    }
}


class Node {
    int value;
    Node next;
    Node random;

    Node(int value) {
        this.value = value;
    }
}