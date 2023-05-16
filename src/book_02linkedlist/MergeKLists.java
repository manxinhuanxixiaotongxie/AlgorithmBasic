package book_02linkedlist;

/**
 * @Description
 * @Author Scurry
 * @see MergeSortedList
 * @Date 2023-05-13 16:36
 */
public class MergeKLists {

    /**
     * 剑指 Offer II 078. 合并排序链表
     * 合并排序链表
     * 给定一个链表数组
     * 每个链表都是升序排序
     * 合并所有链表
     * @param lists
     * @return
     */

    public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode head = null;
        for (int i =0;i<lists.length;i++) {
            ListNode list = lists[i];
            if (head == null) {
                head = list;
            }
            if (list.val < head.val) {
                head = list;
            }
        }

        ListNode pre = head;
        ListNode cur = head;

        for (int i = 0; i < lists.length; i++) {
            while (lists[i] != null) {
                ListNode next = null;
                if (lists[i].val <= cur.val) {
                    next = lists[i].next;
                    pre.next = lists[i];
                    pre = lists[i];
                    lists[i].next = cur;
                    lists[i] = next;
                }else {
                    continue;
                }

            }

        }



        return head;

    }
}
