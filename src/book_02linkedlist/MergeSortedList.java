package book_02linkedlist;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-04-19 18:39
 */
public class MergeSortedList {
    /**
     * 合并两个有序链表
     */

    public ListNode mergeSortedList(ListNode head1, ListNode head2) {

        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }

        // 1 6 8
        // 3 5 7 9
        // 保证从最小的开始
        ListNode head = head1.val<head2.val?head1:head2;
        ListNode cur1 = head== head1?head1:head2;
        ListNode cur2 = head== head1?head2:head1;
        ListNode next;
        ListNode pre= null;


        while (cur1 != null && cur2 != null) {
            if (cur1.val < cur2.val) {
                pre = cur1;
                cur1 = cur1.next;
            } else {
                // pre怎么初始化
                pre.next = cur2;
                next = cur2.next;
                cur2.next = cur1;
                pre = cur2;
                cur2 = next;
            }
        }
        // 链表1或者的链表2走到尽头
        pre.next = cur1 == null?cur2:cur1;


        return head;
    }
}
