package leetcode.classic150;

/**
 * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
 *
 */
public class Code082 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            // 判断 下一个节点是否与当前节点相同
            if (cur.next != null && cur.val == cur.next.val) {
                while (cur.next != null && cur.val == cur.next.val) {
                    // 向后调整链表指针
                    cur = cur.next;
                }
                cur = cur.next;
                continue;
            }
            pre.next = cur;
            pre = cur;
            ListNode next = cur.next;
            cur.next = null;
            cur = next;
        }
        return dummy.next;
    }

    static void main() {
        Code082 code = new Code082();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        ListNode res = code.deleteDuplicates(head);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}
