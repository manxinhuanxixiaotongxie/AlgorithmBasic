package book_02linkedlist;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-02-07 16:51
 */
public class DeletNode {


    /***
     * 删除链表的中间节点和a/b处的节点
     *
     * 1.删除中间节点
     * 2.给定链表head  a b值实现删除位位于a/b处的节点
     */

    public DeleteKNode.Node deleteMidNode(DeleteKNode.Node head) {
        DeleteKNode.Node fastIndex = head;
        DeleteKNode.Node slowIndex = head;
        DeleteKNode.Node cur= head;
        while (fastIndex.next!= null && fastIndex.next.next != null) {
            fastIndex = fastIndex.next.next;
            cur = slowIndex;
            slowIndex = slowIndex.next;
        }
        cur.next = slowIndex.next;
        return head;

    }

    /**
     * 删除a/b处的节点
     * @param head
     * @param a
     * @param b
     * @return
     */
    public DeleteKNode.Node deleteABNode(DeleteKNode.Node head,int a, int b) {

        /**
         *
         *
         * 1/6   长度是7  1/7   2/7   3/7     7/7
         *
         * 如何要求出要删除节点位置
         * 双边同时乘以链表的长度
         * 向上取整
         *
         *
         */

        if (a<0 || a/b >1) {
            return head;
        }
//        int index=a/b;
//
//        int n = 1;
//        DeleteKNode.Node cur = head;
//        while (++n != index) {
//            cur = cur.next;
//        }
//        cur.next = cur.next.next;
        DeleteKNode.Node cur = head;
        int n = 0;
        while (cur!= null) {
            n++;
            cur = cur.next;
        }

        double index = a/b;
        // 应该删除的节点
        n = (int) Math.ceil((double) (n * index));

        if (index > n) {
            return head;
        }

        cur = head;

        while (--n != 1) {
            cur = cur.next;
        }
        cur.next = cur.next.next;

        return head;
    }
}
