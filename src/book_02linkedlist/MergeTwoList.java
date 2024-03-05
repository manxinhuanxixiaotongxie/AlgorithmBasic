package book_02linkedlist;

/**
 * @Description lettcode 175
 * @Author Scurry
 * @see MergeSortedList
 * @Date 2023-05-23 17:35
 */
public class MergeTwoList {

    /**
     * 升序
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        if (l1 == null && l2 == null) {
//            return null;
//        }
//        if (l1 == null) {
//            return l2;
//        }
//        if (l2 == null) {
//            return l1;
//        }

        if (l1 == null || l2 == null) {
            return l1 == null?l2:l1;
        }
        ListNode head = l1.val < l2.val?l1:l2;
        ListNode cur1 = head == l1 ? l1:l2;
        ListNode cur2 = head == l1 ? l2:l1;

        ListNode next = null;
        ListNode pre = null;
        while (cur1 != null && cur2 != null) {
            if (pre == null) {
                pre = head;
                cur1 = cur1.next;
            }else {
                if (cur2.val < cur1.val) {
                    next = cur2.next;
                    pre.next = cur2;
                    cur2.next = cur1;
                    pre = cur2;
                    cur2 = next;
                }else {
                    cur1 = cur1.next;
                    pre = pre.next;
                }
            }

        }
        // 还要处理尾巴
        pre.next = cur1 == null?cur2:cur1;

        return head;
    }

    /***
     * lettcode 21题测试通过
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }
        ListNode head = list1.val <= list2.val ? list1 : list2;
        // 小头
        ListNode cur = head == list1 ? list1 : list2;
        // 大头
        ListNode cur1 = head == list1 ? list2 : list1;

        ListNode next = null;
        ListNode pre = null;
        while (cur != null && cur1 != null) {
            if (cur.val > cur1.val) {
                pre.next = cur1;
                pre = cur1;
                next = cur1.next;
                cur1.next = cur;
                cur1 = next;
            } else {
                next = cur.next;
                pre = cur;
                cur = next;
            }
        }
        // 有一个先到
        if (cur1 != null) {
            pre.next = cur1;
        }

        return head;
    }
}
