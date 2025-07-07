package book_02linkedlist;

/**
 * @Description 判断一个链表是否为回文结构
 * @Author Scurry
 * @Date 2023-04-11 17:42
 */
public class Code07_PlainDromeList {


    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(1);
//        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
//        node3.next = node4;
        System.out.println(isPalindrome(node1));


    }


    /**
     * 1.使用辅助结构-栈
     * <p>
     * 2.翻转链表
     *
     * @param head
     * @return
     */

    public static boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 此时slow来到了上中点的位置

        ListNode cur = slow;
        ListNode next;
        ListNode pre = null;
        // 翻转后面的链表
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 此时pre来到了最后一个节点
        cur = head;
        ListNode tail = pre;
        while (cur != null) {
            if (cur.val != pre.val) {
                return false;
            }
            cur = cur.next;
            pre = pre.next;
        }
        // 恢复链表
        while (tail != null) {
            next = tail.next;
            tail.next = cur;
            cur = tail;
            tail = next;
        }
        return true;
    }

}
