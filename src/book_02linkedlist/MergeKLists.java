package book_02linkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-05-13 16:36
 * @see MergeSortedList
 */
public class MergeKLists {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;

        ListNode l4 = new ListNode(1);
        ListNode l5 = new ListNode(3);
        ListNode l6 = new ListNode(4);
        l4.next = l5;
        l5.next = l6;

        ListNode l7 = new ListNode(2);
        ListNode l8 = new ListNode(6);
        l7.next = l8;
        ListNode[] listNodes = new ListNode[]{l1, l4, l7};
        mergeKLists1(listNodes);

    }

    /**
     * 剑指 Offer II 078. 合并排序链表
     * 合并排序链表
     * 给定一个链表数组
     * 每个链表都是升序排序
     * 合并所有链表
     * <p>
     * 使用堆
     *
     * @param lists
     * @return
     */

    public static ListNode mergeKLists1(ListNode[] lists) {

        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> myHeap = new PriorityQueue<>(new MyComparator());

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                myHeap.add(lists[i]);
            }
        }
        ListNode newHead = myHeap.peek();
        ListNode head = newHead;

        ListNode cur = head;
        /**
         * 这也是对的
         */
//        while (!myHeap.isEmpty()) {
//            ListNode minNode = myHeap.poll();
//            if (minNode.next != null) {
//                myHeap.add(minNode.next);
//            }
//            cur.next = myHeap.peek();
//            cur = cur.next;
//        }

        while (!myHeap.isEmpty()) {
            if (cur.next != null) {
                myHeap.add(cur.next);
            }
            ListNode mindNode = myHeap.poll();
            cur.next = mindNode;
            cur = mindNode;

        }

        return head;

    }

//    public static ListNode mergeKLists2(ListNode[] lists) {
//
//        if (lists == null || lists.length == 0) {
//            return null;
//        }
//
//
//
//        return head;
//
//    }

    public static class MyComparator implements Comparator<ListNode> {

        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    }
}
