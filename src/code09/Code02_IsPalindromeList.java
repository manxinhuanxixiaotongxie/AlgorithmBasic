package code09;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-07-19 14:38
 */

import java.util.Stack;

/**
 * 给定一个单链表头结点 哦按段该链表是否为回文结构
 * 1.笔试用：链表入栈 出栈对比
 * 2.面试用：找到上中点或者中点
 */
public class Code02_IsPalindromeList {

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Node head = null;
        printLinkedList(head);
        System.out.print(isPalindromeList1(head) + " | ");
        System.out.print(isPalindromeList2(head) + " | ");
        System.out.println(isPalindromeList3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindromeList1(head) + " | ");
        System.out.print(isPalindromeList2(head) + " | ");
        System.out.println(isPalindromeList3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(isPalindromeList1(head) + " | ");
        System.out.print(isPalindromeList2(head) + " | ");
        System.out.println(isPalindromeList3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindromeList1(head) + " | ");
        System.out.print(isPalindromeList2(head) + " | ");
        System.out.println(isPalindromeList3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(isPalindromeList1(head) + " | ");
        System.out.print(isPalindromeList2(head) + " | ");
        System.out.println(isPalindromeList3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindromeList1(head) + " | ");
        System.out.print(isPalindromeList2(head) + " | ");
        System.out.println(isPalindromeList3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindromeList1(head) + " | ");
        System.out.print(isPalindromeList2(head) + " | ");
        System.out.println(isPalindromeList3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindromeList1(head) + " | ");
        System.out.print(isPalindromeList2(head) + " | ");
        System.out.println(isPalindromeList3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindromeList1(head) + " | ");
        System.out.print(isPalindromeList2(head) + " | ");
        System.out.println(isPalindromeList3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

    }

    public static class Node {
        private int value;
        private Node next;

        Node(int value) {
            this.value = value;
        }
    }

    /**
     * 使用栈方式  时间复杂度O(N) 空间复杂度 O(N)
     *
     * @param head
     * @return
     */
    public static boolean isPalindromeList1(Node head) {

        if (head == null) {
            return true;
        }

        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            if (cur.value != stack.pop().value) {
                return false;
            }
            cur = cur.next;
        }
        return true;

    }

    /**
     * 使用栈方式  后半部分入栈
     * 时间复杂度O(N) 空间复杂度O(N/2)
     *
     * @param head
     * @return
     */
    public static boolean isPalindromeList2(Node head) {

        if (head == null) {
            return true;
        }

        Node left = head;
        Node right = head;
        while (right.next != null && right.next.next != null) {
            left = left.next;
            right = right.next.next;
        }

        Stack<Node> stack = new Stack<>();
        //慢指针来到中间位置 上中点
        while (left.next != null) {
            stack.push(left.next);
            left = left.next;
        }
        Node cur = head;
        while (!stack.isEmpty()) {
            if (cur.value != stack.pop().value) {
                return false;
            }
            cur = cur.next;
        }
        return true;

    }

    /**
     * 不使用栈方式  快慢指针
     * 1.快慢指针来到中点位置
     * 2.翻转后面的链表 依次对比
     * 3.回复链表
     * 时间复杂度O(N) 空间复杂度O(1)
     *
     * @param head
     * @return
     */
    public static boolean isPalindromeList3(Node head) {

        if (head == null || head.next == null) {
            return true;
        }

        /**
         * N N N N N N
         */
        Node left = head;
        Node right = head;
        while (right.next != null && right.next.next != null) {
            left = left.next;
            right = right.next.next;
        }

        // 翻转后半部分链表
        Node cur = left;
        Node next;
        Node pre = null;
        // 1 2 3 2 1
        while (cur != null) {
            /**
             * 不能提前打断下下个节点的联系
             */
//            next = cur.next;
//            cur.next = pre;
//            next.next = cur;
//            pre = cur;
//            cur = next;
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;

        }

        Node curNew = head;
        Node curLeft = pre;
        while (curLeft != null && curNew != null) {

            if (curLeft.value != curNew.value) {
                return false;
            }
            curLeft = curLeft.next;
            curNew = curNew.next;
        }

        // 恢复链表
        Node next2 = null;
        while (pre != null) {
            Node next1 = pre.next;
//            pre.next = next2;
//             next2 = pre ;
//            pre = next1;
            Node next3 = pre.next;
            pre.next = next2;
            next2 = pre;
            pre = next3;

        }

        return true;

    }
}
