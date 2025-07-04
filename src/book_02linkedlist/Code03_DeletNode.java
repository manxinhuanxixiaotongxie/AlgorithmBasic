package book_02linkedlist;

/**
 * @Description 删除a/b处的节点和中间节点
 * @Author Scurry
 * @Date 2023-02-07 16:51
 */
public class Code03_DeletNode {


    /***
     * 删除链表的中间节点和a/b处的节点
     *
     * 1.删除中间节点
     *
     * 快慢指针
     *
     */
    public Code02_DeleteKNode.ListNode deleteMidNode(Code02_DeleteKNode.ListNode head) {
        if (head == null || head.next == null) return head;
        if (head.next.next == null) return head.next;
        Code02_DeleteKNode.ListNode fastIndex = head.next.next;
        Code02_DeleteKNode.ListNode slowIndex = head;
        while (fastIndex.next != null && fastIndex.next.next != null) {
            fastIndex = fastIndex.next.next;
            slowIndex = slowIndex.next;
        }
        slowIndex.next = slowIndex.next.next;
        return head;

    }

    /**
     * 删除a/b处的节点
     *
     * @param head
     * @param a
     * @param b
     * @return
     */
    public Code02_DeleteKNode.ListNode deleteABNode(Code02_DeleteKNode.ListNode head, int a, int b) {
        if (head == null || head.next == null) return head;

        /**
         *
         *
         * 假设a==1 b == 2
         * 链表的长度是7 那么要删除的节点是1/2 * 7向上取整就是要删除的节点
         *
         */
        if (a < 1 || a > b) return head;

        Code02_DeleteKNode.ListNode cur = head;
        // 链表的长度
        int n = 0;
        while (cur != null) {
            n++;
            cur = cur.next;
        }

        n = (int) Math.ceil((double) (a * n) / (double) b);

        if (n == 1) {
            head = head.next;
        }
        if (n > 1) {
            cur = head;
            while (--n != 1) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }

        return head;
    }
}
