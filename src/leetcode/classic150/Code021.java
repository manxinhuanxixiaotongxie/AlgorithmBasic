package leetcode.classic150;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 */
public class Code021 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        ListNode cur1 = list1;
        ListNode cur2 = list2;
        while (cur1 != null && cur2 != null) {
            ListNode next;
            if (cur1.val <= cur2.val) {
                next = cur1.next;
                cur.next = cur1;
                cur = cur1;
                cur1 = next;
            }else {
                next = cur2.next;
                cur.next = cur2;
                cur = cur2;
                cur2 = next;
            }
        }

        if (cur1 != null) {
            cur.next = cur1;
        }
        if (cur2 != null) {
            cur.next = cur2;
        }

        return dummy.next;
    }
}
