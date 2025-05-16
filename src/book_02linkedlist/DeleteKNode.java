package book_02linkedlist;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-02-06 16:44
 */
public class DeleteKNode {

    class Node {
        int value;
        Node next;
        Node last;

        Node(int value) {
            this.value = value;
        }

        Node(Node node, int value, Node last) {
            this.value = value;
            this.next = next;
            this.last = last;
        }
    }

    /**
     * 实现一个函数可以删除单链表倒数第K个节点
     * 实现一个函数可以删除双链表倒数第K个节点
     *
     */

    /**
     * 遍历链表，
     */

    public Node deleteKNode(Node head, int k) {

        Node cur = head;
        while (cur != null) {
            k--;
            cur = cur.next;
        }
        if (k > 0) {
            return head;
        }

        if (k == 0) {
            return head.next;
        }

        cur = head;
        /**
         *
         * 总共有N个节点 N-K
         * 单链表
         */
//        while (cur != null) {
//            ++k;
//            if (k == 0) {
//                cur.next = cur.next.next;
//                break;
//            }
//        }
        if (k < 0) {
            while (++k < 0) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }

        /**
         * 双链表
         */
        while (cur != null) {
            ++k;
            if (k == 0) {
//                cur.next = cur.next.next;
//                cur.next.last = cur;
                Node newNext = cur.next.next;
                cur.next = newNext;
                if (newNext != null) {
                    newNext.last = cur;
                }
                break;
            }
        }
        return head;

    }

}
