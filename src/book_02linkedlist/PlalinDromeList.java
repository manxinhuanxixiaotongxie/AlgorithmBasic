package book_02linkedlist;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-03-31 16:42
 */
public class PlalinDromeList {

    /**
     * 判断链表是否为回文
     */

    public boolean isPlainDrome(DeleteKNode.Node head) {

        boolean res = true;

        /**
         *找到中点
         * 翻转后部分节点
         * 恢复链表
         */
        if (head.next == null) {
            return true;
        }

        DeleteKNode.Node cur = head;
        DeleteKNode.Node next = head.next.next;

        while (next != null) {
            cur = cur.next;
            next = next.next.next;
        }

        // 此时cur在当前中间节点 偶数的话 在中间节点的上一个节点
        // 翻转cur之后的链表

        DeleteKNode.Node next1 = cur.next;
        while (cur.next != null) {
            DeleteKNode.Node next2 = next1.next;
            next1.next = cur;
            cur = next1;
            next1 = next2;
        }
        cur = head;
        while (cur.next != null) {
            if (cur.next != next1.next) {
                res = false;
                break;
            }
            cur = cur.next;
            next1 = next1.next;
        }
        // 恢复链表
        DeleteKNode.Node pre = null;
        while (next1.next != cur) {
            DeleteKNode.Node next2 = next1.next;
            cur.next = pre;
            pre = cur;
            cur = next2;
        }

        return res;
    }

    /**
     * 判断链表是否为回文
     */

    public boolean isPlainDromeRightRight(DeleteKNode.Node head) {

        if (head == null || head.next == null) {
            return true;
        }
        // 中间节点
        DeleteKNode.Node n1 = head;
        DeleteKNode.Node n2 = head;
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }
        // 右半区的最后以一个节点
        n2 = n1.next;
        n1.next = null;
        DeleteKNode.Node n3 = null;
        // 翻转右半区
        while (n2.next != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }

        // 最后一个节点
        n3 = n1;
        n2 = head;
        boolean res = true;
        // 左半区与右半区进行比较
        while (n1 != null && n2 != null) {
            if (n1.value != n2.value) {
                res = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }

        // 恢复链表
        n1 = n3.next;
        n3.next = null;
        while (n1 != null) {
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;

    }
}
