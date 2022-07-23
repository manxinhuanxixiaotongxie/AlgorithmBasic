/**
 * @desc:
 * @author: Scurry
 * @date: 2022/7/21 22:36
 */

package code09;

/**
 * @desc:
 * @author: Scurry
 * @date: 2022/7/21 22:36
 */

import java.util.HashMap;
import java.util.Map;

/**
 * random指针是单链表节点结构中新增的指针，random可能指向链表中的任意一个节点，也可能指向null。
 * 给定一个由Node节点类型的无环单链表的头节点head，
 * 请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点
 * 要求：时间复杂度O(N)，额外空间复杂度O(1)
 */
public class Code04_CopyListWithRandom {

    static class Node {
        private int value;
        private Node next;
        private Node random;

        Node(int value) {
            this.value = value;
        }
    }

    /**
     * 第一种实现方式：使用HashMap
     */

    public static Node copyList1(Node head) {

        Node cur = head;
        Map<Node,Node> map = new HashMap<>();

        while (cur != null) {
            map.put(cur,new Node(cur.value));
            cur = cur.next;
        }
        /**
         * 处理random指针
         */
        cur = head;
        while (cur != null) {
            map.get(cur).random = cur.random;
            map.get(cur).next = cur.next;
            cur = cur.next;
        }
        return map.get(head);

    }

    /**
     * 复制一份新节点 嵌套进去原有的链表
     * 然后将两个链表进行拆分
     * @return
     */
    public static Node copyList2(Node head) {

        Node cur = head;
        Node curRight = null;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            curRight = new Node(cur.value);
            cur.next = curRight;
            curRight.next = next;
            cur = next;

        }

        // 处理random 拆分链表
        cur = head;
        /**
         * 这样写有问题
         * 当前节点的下一个指针指向了原有的位置
         * 这时候已经无法找到复制节点
         */
//        while (cur != null) {
//            next = cur.next.next;
//            cur.next.random = cur.random;
//            cur.next = next;
//            cur.next.next = next.next;
//            cur = next;
//        }

        // 处理random指针
        while (cur != null) {
            next = cur.next.next;
            cur.next.random = cur.random;
            cur = next;

        }

        cur = head;
        Node res = cur.next;

        // 拆分链表
        while (cur != null) {
            next = cur.next.next;
            cur.next.next = next.next;
            cur.next = next;
            cur = next;
        }
        return res;
    }
}
