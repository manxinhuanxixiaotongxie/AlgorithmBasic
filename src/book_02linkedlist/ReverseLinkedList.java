package book_02linkedlist;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-02-08 17:24
 */
public class ReverseLinkedList {

    /**
     * 翻转单向链表
     * 翻转双向链表
     */

    public DeleteKNode.Node reverseOneWayLinkedList(DeleteKNode.Node head) {
        DeleteKNode.Node cur = head;
        DeleteKNode.Node pre = null;
        while (cur.next != null) {
            DeleteKNode.Node next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        // 返回新节点
        return cur;
    }

    public DeleteKNode.Node reverseTwoWayLinkedList(DeleteKNode.Node head) {

        DeleteKNode.Node cur = head;
        DeleteKNode.Node pre = null;

        while (cur != null) {
            DeleteKNode.Node next = cur.next;
            cur.next = pre;
            cur.last = next;
            pre = cur;
            cur = next;
        }
        return cur;
    }
}
