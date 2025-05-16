package code09;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-07-19 10:52
 */

/**
 * 快速排序时间常数好
 * 堆排序空间省
 * 归并排序稳定
 * 时间复杂度o(n*logn) 空间复杂度o(n)不存在
 */

/**
 * 链表问题：
 * 解题方法论：
 * 1.笔试 一切为了时间复杂度
 * 2.面试 时间复杂度放在第一位 一定要找到空间最省的方法
 */

/**
 * 找到链表中点的位置
 * 快慢指针 快指针一次走两步  慢指针一次走一步
 */
public class Code01_LinkedListMid {

    public static class Node {
        private int value;
        private Node next;

        Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 返回上中点
     * 如果是偶数返回上中点
     * 奇数返回中点
     * 0 0 0 0 0 0
     *
     * @param head
     * @return
     */
    public static Node midOrUpMidNode(Node head) {

        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 返回下中点
     * 如果是偶数返回下中点
     * 奇数返回中点
     * 0 0 0 0 0 0
     *
     * @param head
     * @return
     */
    public static Node midOrDownMidNode(Node head) {

        if (head == null || head.next == null) {
            return head;
        }
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 上中点的前一个节点
     *
     * @return
     */
    public static Node midOrUpMidPreNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 下中点的前一个节点
     *
     * @param head
     * @return
     */
    public static Node midOrDownMidPreNode(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        if (head.next.next == null) {
            return head;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
