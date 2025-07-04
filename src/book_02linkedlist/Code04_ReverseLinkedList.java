package book_02linkedlist;

/**
 * @Description 翻转单向链表和双向链表
 * @Author Scurry
 * @Date 2023-02-08 17:24
 */
public class Code04_ReverseLinkedList {

    /**
     * 翻转单向链表
     *
     */

    public Code02_DeleteKNode.ListNode reverseOneWayLinkedList(Code02_DeleteKNode.ListNode head) {
        Code02_DeleteKNode.ListNode cur = head;
        Code02_DeleteKNode.ListNode pre = null;
        Code02_DeleteKNode.ListNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        // 返回新节点
        return pre;
    }

    /**
     * 翻转双向链表
     *
     * @param head
     * @return
     */
    public Code02_DeleteKNode.ListNode reverseTwoWayLinkedList(Code02_DeleteKNode.ListNode head) {

        Code02_DeleteKNode.ListNode cur = head;
        Code02_DeleteKNode.ListNode pre = null;

        while (cur != null) {
            Code02_DeleteKNode.ListNode next = cur.next;
            cur.next = pre;
            cur.last = next;
            pre = cur;
            cur = next;
        }
        return cur;
    }
}
