package book_02linkedlist;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-04-19 10:39
 */
public class SelectionSort {
    /**
     * 给定一个无序的单链表的head，实现单链表的选择排序
     * 要求：额外空间复杂度O（1）
     */
    public ListNode selectionSort(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode cur = head;
        ListNode pre = null;
        ListNode tail = null;
        ListNode smallPre;
        ListNode small = null;
        while (cur != null) {
            // 获取往后最小node节点
            small = cur;
            smallPre = getSmallListNode(cur);
            if (smallPre != null) {
                small = smallPre.next;
                smallPre.next = small.next;
            }

            if (tail == null) {
                head = small;
            } else {
                tail.next = small;
            }
            tail = small;


            cur = cur == small ? cur.next : cur;

        }

        return head;
    }

    public ListNode getSmallListNode(ListNode head) {

        ListNode cur = head;
        ListNode small = head;
        ListNode smallPre = null;
        ListNode pre = null;
        while (cur != null) {
            if (small.val > cur.val) {
                smallPre = pre;
                small = cur;
            }
            pre = cur;
            cur = cur.next;

        }

        return smallPre;
    }
}
