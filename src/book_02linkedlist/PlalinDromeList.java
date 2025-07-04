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

    public boolean isPlainDrome(Code02_DeleteKNode.ListNode head) {

        boolean res = true;

        /**
         *找到中点
         * 翻转后部分节点
         * 恢复链表
         */
        if (head.next == null) {
            return true;
        }

        Code02_DeleteKNode.ListNode cur = head;
        Code02_DeleteKNode.ListNode next = head.next.next;

        while (next != null) {
            cur = cur.next;
            next = next.next.next;
        }

        // 此时cur在当前中间节点 偶数的话 在中间节点的上一个节点
        // 翻转cur之后的链表

        Code02_DeleteKNode.ListNode next1 = cur.next;
        while (cur.next != null) {
            Code02_DeleteKNode.ListNode next2 = next1.next;
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
        Code02_DeleteKNode.ListNode pre = null;
        while (next1.next != cur) {
            Code02_DeleteKNode.ListNode next2 = next1.next;
            cur.next = pre;
            pre = cur;
            cur = next2;
        }

        return res;
    }

    /**
     * 判断链表是否为回文
     */

    public boolean isPlainDromeRightRight(Code02_DeleteKNode.ListNode head) {

        if (head == null || head.next == null) {
            return true;
        }
        // 中间节点
        Code02_DeleteKNode.ListNode n1 = head;
        Code02_DeleteKNode.ListNode n2 = head;
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }
        // 右半区的最后以一个节点
        n2 = n1.next;
        n1.next = null;
        Code02_DeleteKNode.ListNode n3 = null;
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

    /**
     * 测试运行成功
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome1(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode n1 = head;
        ListNode n2 = head;
        // 1 0 0

        while (n1.next != null && n2.next != null && n2.next.next != null) {

            n1 = n1.next;
            n2 = n2.next.next;

        }

        // 此时n1走到了中点  n2走到了最后一步
        ListNode n3 = n1;
        n1 = n1.next;
        n3.next = null;
        // n1走到最后一步
        while (n1 != null) {
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;

        }

        boolean res = true;
        n2 = head;
        n1 = n3;
        while (n2 != null) {
            if (n2.val != n3.val) {
                res = false;
                break;
            }
            n2 = n2.next;
            n3 = n3.next;
        }

        // 1  2  2 1
        // 恢复链表

        n2 = n1.next;
        n3.next = null;
        // 1 2 3 4
        while (n2 != null) {
            n1 = n2.next;
            n2.next = n3;
            n3 = n2;
            n2 = n1;

        }
        return res;

    }

    /**
     * https://leetcode.cn/problems/palindrome-linked-list/description/
     * 判断是否是回文链表
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null) {
            return true;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 翻转后半部分链表
        ListNode listNode = reverseList(slow);
        ListNode cur1 = head;
        ListNode cur2 = listNode;
        while (cur1 != null && cur2 != null) {
            if (cur1.val != cur2.val) {
                return false;
            }
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        // 恢复链表
        ListNode listNode1 = reverseList(listNode);
        slow.next = listNode1;
        return true;
    }

    /**
     * 1 2 2 1
     *
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(0);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(0);
        ListNode n6 = new ListNode(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        isPalindrome(n1);
    }
}
